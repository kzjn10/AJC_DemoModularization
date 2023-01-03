package com.anhndt.feature.moviedetail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.anhndt.feature.moviedetail.BuildConfig
import com.anhndt.feature.moviedetail.R
import com.anhndt.model.MovieDetail
import com.anhndt.systemdesign.component.CenterLoading
import com.anhndt.systemdesign.component.InfoChip
import com.anhndt.systemdesign.component.PointInfo
import com.anhndt.systemdesign.component.SectionInfo
import com.anhndt.systemdesign.extensions.backdropGradientBackground
import com.anhndt.systemdesign.extensions.contentPadding
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun MovieDetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    movieId: String,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    movieDetailViewModel.getMovieInfo(movieId)
    val movieDetailState by movieDetailViewModel.getMovieData.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .backdropGradientBackground()
        ) {
            when (movieDetailState) {
                GetMovieDataState.Loading -> {
                    CenterLoading()

                }
                is GetMovieDataState.Success -> {
                    (movieDetailState as GetMovieDataState.Success).movie?.let { movie ->
                        MainContent(
                            data = movie
                        )
                    } ?: run {

                    }
                }
                is GetMovieDataState.Error -> {
                    Text(text = (movieDetailState as GetMovieDataState.Error).message ?: "")
                }
            }
        }
    }

}

@Composable
fun MainContent(modifier: Modifier = Modifier, data: MovieDetail) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            ConstraintLayout(
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(1.35f)
                    .padding(bottom = 20.dp)
            ) {
                val (backdrop, poster) = createRefs()
                AsyncImage(
                    model = "${BuildConfig.IMAGE_PATH}${data.backdropPath}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .constrainAs(backdrop) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .aspectRatio(1.78f)
                )

                AsyncImage(
                    model = "${BuildConfig.IMAGE_PATH}${data.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .width(150.dp)
                        .constrainAs(poster) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .aspectRatio(0.667f)
                        .shadow(10.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    alignment = Alignment.BottomCenter,
                )
            }
        }
        item {
            Text(
                text = data.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .contentPadding()
                    .padding(bottom = 20.dp)
            )
        }
        item {
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                PointInfo(
                    value = data.voteAverage.toString(),
                    title = stringResource(id = R.string.average_vote)
                )
                PointInfo(
                    value = data.voteCount.toString(),
                    title = stringResource(id = R.string.vote_count)
                )
            }
            Spacer(modifier = modifier.height(32.dp))
        }
        item {
            SectionInfo(title = stringResource(id = R.string.overview))
            Text(
                text = data.overview,
                textAlign = TextAlign.Justify,
                modifier = modifier.contentPadding(),
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
        item {
            SectionInfo(title = stringResource(id = R.string.genres))
            FlowRow(
                modifier = modifier.contentPadding(),
                mainAxisSpacing = 16.dp,
                crossAxisSpacing = 8.dp,
            ) {
                data.genres?.forEach { genre ->
                    InfoChip(
                        label = genre.name
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

