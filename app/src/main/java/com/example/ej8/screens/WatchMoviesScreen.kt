package com.example.ej8.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ej8.model.Pelicula
import com.example.ej8.utils.Singleton

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchMoviesScreen(navController: NavController){
    Scaffold {
        WatchMoviesScreenContent(navController)
    }
}

@Composable
fun WatchMoviesScreenContent(navController: NavController) {

    
    val peliculas = remember { Singleton.listaPeliculas }
    Log.d("MiTag", "Refresco lista")
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            peliculas
        ) {
            Tarjeta(pelicula = it)
        }
    }
}

@Composable
fun Tarjeta(pelicula: Pelicula){
    Card(
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(modifier = Modifier.padding(20.dp)) {
            Image(painter = rememberAsyncImagePainter(pelicula.imagen), contentDescription = "Imagen",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .padding(8.dp)
                    .size(125.dp))

            Column(modifier = Modifier.weight(1f),
                Arrangement.Center) {
                Text(
                    text = pelicula.code,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = pelicula.titulo,
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Director: "+pelicula.director,
                    color = Color.Black,
                    fontSize = 15.sp
                )
                Text(
                    text = "Año: "+pelicula.anio,
                    color = Color.Black,
                    fontSize = 15.sp
                )

                Text(
                    text = "Duracion: "+pelicula.duracion,
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

        }
    }
}


