package com.example.ej8.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ej8.R
import com.example.ej8.model.Pelicula
import com.example.ej8.utils.Singleton
import com.example.ej8.utils.Singleton.listaPeliculas
import kotlinx.coroutines.tasks.await

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(navController: NavController){
    Scaffold {
        MovieScreenContent(navController)
    }
}

@Composable
fun MovieScreenContent(navController: NavController) {
    var pelicula by remember { mutableStateOf(listaPeliculas.find { it.code == Singleton.code }) }

    Column( modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Card(
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))
        ) {
            Row(modifier = Modifier.padding(20.dp)) {
                pelicula?.let { safePelicula ->
                    Image(
                        painter = rememberAsyncImagePainter(safePelicula.imagen),
                        contentDescription = "Imagen",
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(125.dp)
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        Arrangement.Center
                    ) {
                        Text(
                            text = safePelicula.code,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = safePelicula.titulo,
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = "Director: " + safePelicula.director,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Año: " + safePelicula.anio,
                            color = Color.Black,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "Duracion: " + safePelicula.duracion,
                            color = Color.Black,
                            fontSize = 15.sp
                        )
                    }
                }

            }
        }
    }
}

