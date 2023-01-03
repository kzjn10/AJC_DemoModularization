package com.anhndt.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.anhndt.common.navigation.NavRoute
import com.anhndt.model.Movie


fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(NavRoute.Home.path, navOptions)
}

fun NavGraphBuilder.homeScreen(navigateToMovieDetail: (Movie) -> Unit) {
    composable(route = NavRoute.Home.path) {
        HomeRoute(
            navigateToMovieDetail = navigateToMovieDetail
        )
    }
}