package com.anhndt.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.anhndt.common.navigation.NavRoute
import com.anhndt.feature.home.navigation.homeScreen
import com.anhndt.feature.moviedetail.ui.navigation.movieDetailScreen
import com.anhndt.feature.settings.navigation.settingsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.path,
        modifier = modifier
    ) {
        homeScreen(navigateToMovieDetail = { movie ->
            navController.navigate(
                NavRoute.MovieDetail.withArgs(
                    movie.id.toString()
                )
            )
        })
        movieDetailScreen(
            onBackClick = { navController.popBackStack() }
        )
        settingsScreen()
    }
}