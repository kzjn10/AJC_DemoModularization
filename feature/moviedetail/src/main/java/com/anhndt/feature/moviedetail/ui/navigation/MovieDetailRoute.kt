package com.anhndt.feature.moviedetail.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anhndt.feature.moviedetail.ui.MovieDetailScreen

@Composable
fun MovieDetailRoute(
    modifier: Modifier = Modifier,
    movieId: String,
    onBackClick: () -> Unit,
) {
    MovieDetailScreen(
        modifier = modifier,
        movieId = movieId,
        onBackClick = onBackClick,
    )
}