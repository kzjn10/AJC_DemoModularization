package com.anhndt.common.navigation

sealed class NavRoute(val path: String) {
    object MovieDetail : NavRoute("movie_detail") {
        const val movieId = "movieId"
        const val posterPath = "posterPath"
    }

    object Home : NavRoute("home")

    object Settings : NavRoute("settings")

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}