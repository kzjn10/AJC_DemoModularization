package com.anhndt.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.anhndt.movieapp.navigation.NavGraph
import com.anhndt.systemdesign.theme.MovieTheme

@Composable
fun MovieApp (){
    MovieTheme {
        val navController = rememberNavController()
        NavGraph(navController = navController)
    }
}