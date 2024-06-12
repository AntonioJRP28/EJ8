package com.example.ej8.navigation

sealed class AppScreens(val route: String){
    object AddMovieScreen: AppScreens("add_movie_screen")
    object MainScreen: AppScreens("main_screen")
    object WatchMoviesScreen: AppScreens("watch_movies_screen")
    object SearchMovieScreen: AppScreens("search_movie_screen")
    object MovieScreen: AppScreens("movie_screen")
}
