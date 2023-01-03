package com.anhndt.feature.home

import android.util.Log
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anhndt.common.extensions.floorMod
import com.anhndt.model.Movie
import com.anhndt.systemdesign.component.MfaOutlineButton
import com.anhndt.systemdesign.extensions.backdropGradientBackground
import com.google.accompanist.pager.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToMovieDetail: (Movie) -> Unit
) {

    val homeState: GetHomeDataState by viewModel.getHomeData.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .backdropGradientBackground(), contentAlignment = Alignment.Center
    ) {
        when (homeState) {
            GetHomeDataState.Loading -> {
                LoadingUI()
            }
            is GetHomeDataState.Success -> {
                MoviesUI(
                    movies = (homeState as GetHomeDataState.Success).movies,
                    viewModel = viewModel,
                    navigateToMovieDetail = navigateToMovieDetail,
                )
            }
            is GetHomeDataState.Error -> {
                ErrorUI()
            }
        }
    }
}

@Composable
fun LoadingUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {

    }
}

@Composable
fun ErrorUI(modifier: Modifier = Modifier) {
    Text("Error")
}

@Composable
fun MoviesUI(
    modifier: Modifier = Modifier,
    movies: List<Movie>? = listOf(),
    viewModel: HomeViewModel,
    navigateToMovieDetail: (Movie) -> Unit
) {
    Column {
        /// Fake topappbar
        TopAppBarUI(modifier = modifier.statusBarsPadding())
        /// Tablayout
        TabLayoutUI()
        /// Carousel
        Box(modifier = modifier.weight(1f)) {
            movies?.let { movies ->
                if (movies.isNotEmpty())
                    CarouselUI(
                        movies = movies,
                        viewModel = viewModel,
                        navigateToMovieDetail = navigateToMovieDetail
                    ) else
                    LoadingUI()
            } ?: run {
                Text(text = "Empty")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUI(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = null
                )
            }

        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ },
                content = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) })
        }
    )
}

@Composable
fun TabLayoutUI() {
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalPagerApi::class)
@Composable
fun CarouselUI(
    modifier: Modifier = Modifier, movies: List<Movie>, viewModel: HomeViewModel,
    navigateToMovieDetail: (Movie) -> Unit
) {

    val pageCount: Int by viewModel.carouselPageCount.collectAsStateWithLifecycle()
    val carouselPageIndex: Int by viewModel.carouselPageIndex.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(initialPage = carouselPageIndex)

    fun pageMapper(index: Int): Int {
        return (index - carouselPageIndex).floorMod(pageCount)
    }

    Column(modifier = modifier) {
        HorizontalPager(
            count = Int.MAX_VALUE,
            state = pagerState,
            itemSpacing = 4.dp,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier
                .weight(1f)
        ) { index ->
            val page = pageMapper(index)
            val movie = movies[page]
            PageItemUI(
                movie = movie,
                modifier = modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.75f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .aspectRatio(0.675f),
                navigateToMovieDetail = navigateToMovieDetail
            )

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            pageCount = pageCount,
            pageIndexMapping = ::pageMapper,
            activeColor = MaterialTheme.colorScheme.tertiary
                ,
        )
    }

    val loopState = remember {
        mutableStateOf(true)
    }


    var underDragging by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        pagerState.interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> underDragging = true
                is PressInteraction.Release -> underDragging = false
                is PressInteraction.Cancel -> underDragging = false
                is DragInteraction.Start -> underDragging = true
                is DragInteraction.Stop -> underDragging = false
                is DragInteraction.Cancel -> underDragging = false
            }
        }
    }

    val looping = loopState.value
    if (underDragging.not() && looping) {
        LaunchedEffect(key1 = underDragging) {
            try {
                while (true) {
                    delay(3000L)
                    val current = pagerState.currentPage
                    val currentPos = pageMapper(current)
                    val nextPage = current + 1
                    if (underDragging.not()) {
                        val toPage = nextPage.takeIf { nextPage < pagerState.pageCount }
                            ?: (currentPos + carouselPageIndex + 1)
                        if (toPage > current) {
                            pagerState.animateScrollToPage(toPage)
                        } else {
                            pagerState.scrollToPage(toPage)
                        }
                    }
                }
            } catch (e: CancellationException) {
                Log.i("page", "Launched paging cancelled")
            }
        }
    }
}


@Composable
fun PageItemUI(
    modifier: Modifier = Modifier,
    movie: Movie,
    navigateToMovieDetail: (Movie) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/original${movie.posterPath}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .weight(1f)
                    .padding(12.dp)
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                error = painterResource(id = R.drawable.ic_launcher_background)
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 24.sp,
                    color = Color.White
                ),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            MfaOutlineButton(onClick = { navigateToMovieDetail(movie) }) {
                Text(text = "Buy ticket")
            }
        }
    }

}




