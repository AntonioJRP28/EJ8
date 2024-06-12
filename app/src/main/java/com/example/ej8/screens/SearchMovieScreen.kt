package com.example.ej8.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.ej8.R
import com.example.ej8.navigation.AppScreens
import com.example.ej8.utils.Singleton
import com.example.ej8.utils.Singleton.listaPeliculas

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieScreen(navController: NavController){
    Scaffold {
        SearchMovieScreenContent(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMovieScreenContent(navController: NavController) {
    var code by remember { mutableStateOf("") }

    var errorNotificated = remember { mutableStateOf(false) }
    var errorMesage by remember { mutableStateOf("") }

    Column( modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.secundary_background_color)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .weight(1f),
            horizontalArrangement = Arrangement.Center
        ){
            if (errorNotificated.value){
                Text(text = errorMesage,
                    color = colorResource(id = R.color.error_notification_color),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(colorResource(id = R.color.secundary_background_color)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            TextField(
                value = code,
                onValueChange = { code = it },
                label = { Text("Codigo") },
                modifier = Modifier
                    .width(150.dp)
                    .padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red_button_color)),
                    onClick = { navController.popBackStack() },
                ) {
                    Text(text = "Cancelar")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_button_color)),
                    onClick = { if (CheckCode(code)) {
                        Singleton.code = code
                        navController.navigate(AppScreens.MovieScreen.route)
                    }else {
                        errorNotificated.value = true
                        errorMesage = "Codigo vacio o no existente.\nPor favor ingrese un codigo valido."
                    }
                              },
                ) {
                    Text(text = "Buscar")
                }
            }
        }
        Row( modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
        ){

        }
    }
}

fun CheckCode(code: String): Boolean {
    return !code.isBlank() && listaPeliculas.any { pelicula -> pelicula.code == code }
}
