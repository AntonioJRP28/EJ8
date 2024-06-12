package com.example.ej8.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ej8.R
import com.example.ej8.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold() {
        MainScreenContent(navController)
    }
}

@Composable
fun MainScreenContent(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.secundary_background_color)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Button(
            onClick = {navController.navigate(route = AppScreens.AddMovieScreen.route)},
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_button_color)),
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = "Añadir Pelicula")
        }

        Button(
            onClick = {navController.navigate(route = AppScreens.WatchMoviesScreen.route)},
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.yellow_button_color)),
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = "Ver Peliculas")
        }

        Button(
            onClick = {navController.navigate(route = AppScreens.SearchMovieScreen.route)},
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.orange_button_color)),
            modifier = Modifier
                .width(200.dp)
        ) {
            Text(text = "Buscar Pelicula")
        }
    }
}