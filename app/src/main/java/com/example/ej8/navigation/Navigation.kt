package com.example.ej8.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ej8.screens.MainScreen
import com.example.ej8.screens.WatchMoviesScreen
import com.example.ej8.screens.AddMovieScreen
import com.example.ej8.screens.MovieScreen
import com.example.ej8.screens.SearchMovieScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = AppScreens.AddMovieScreen.route){
            AddMovieScreen(navController)
        }
        composable(route = AppScreens.WatchMoviesScreen.route){
            WatchMoviesScreen(navController)
        }
        composable(route = AppScreens.SearchMovieScreen.route){
            SearchMovieScreen(navController)
        }
        composable(route = AppScreens.MovieScreen.route){
            MovieScreen(navController)
        }
    }
}