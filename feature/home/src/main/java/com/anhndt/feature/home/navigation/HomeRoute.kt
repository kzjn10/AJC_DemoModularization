package com.anhndt.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anhndt.feature.home.HomeScreen
import com.anhndt.model.Movie

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navigateToMovieDetail: (Movie) -> Unit
) {
    HomeScreen(
        modifier = modifier,
        navigateToMovieDetail = navigateToMovieDetail
    )
}