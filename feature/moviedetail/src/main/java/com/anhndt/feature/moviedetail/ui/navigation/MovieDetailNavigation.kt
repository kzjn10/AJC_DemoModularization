/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anhndt.feature.moviedetail.ui.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.anhndt.common.navigation.NavRoute


fun NavController.navigateToMovieDetail(navOptions: NavOptions? = null) {
    this.navigate(NavRoute.MovieDetail.path, navOptions)
}

fun NavGraphBuilder.movieDetailScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = NavRoute.MovieDetail.withArgsFormat(
            NavRoute.MovieDetail.movieId
        ),
        arguments = listOf(
            navArgument(NavRoute.MovieDetail.movieId) {
                type = NavType.StringType
            },
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        MovieDetailRoute(
            movieId = args?.getString(NavRoute.MovieDetail.movieId) ?: "",
            onBackClick = onBackClick,
        )
    }
}

fun NavController.backToHome(navOptions: NavOptions? = null) {
    this.navigate(NavRoute.Home.path, navOptions)
}
