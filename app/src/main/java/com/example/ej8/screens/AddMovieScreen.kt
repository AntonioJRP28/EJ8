package com.example.ej8.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.example.ej8.R
import com.example.ej8.model.Pelicula
import com.example.ej8.navigation.AppScreens
import com.example.ej8.utils.Singleton
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreen(navController: NavController){
    Scaffold {
        AddMovieScreenContent(navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMovieScreenContent(
    navController: NavController,
) {
    var code by remember { mutableStateOf(generateShortUUID()) }
    var titulo by remember { mutableStateOf("") }
    var urlImage by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var timeDuration by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf("") }

    var errorNotificated = remember { mutableStateOf(false) }
    var errorMesage by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val currentYear = LocalDate.now().year
    val years = (1900..currentYear).toList()

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
                onValueChange = {  },
                label = { Text("Codigo") },
                readOnly = (true),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Titulo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )

            TextField(
                value = urlImage,
                onValueChange = { urlImage = it },
                label = { Text("URL de la Imagen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = director,
                onValueChange = { director = it },
                label = { Text("Director") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row {
                TextField(
                    value = selectedYear,
                    onValueChange = { selectedYear = it },
                    label = { Text("Año de salida") },
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { expanded = true },
                    trailingIcon = {
                        IconButton(onClick = { expanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown Arrow")
                        }
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    properties = PopupProperties(focusable = false),
                    modifier = Modifier
                        .widthIn(min = 150.dp, max = 300.dp)
                        .heightIn(max = 200.dp)
                ) {
                    years.forEach { year ->
                        DropdownMenuItem(
                            text = { Text(year.toString()) },
                            onClick = {
                                selectedYear = year.toString()
                                expanded = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.width(10.dp))

                TextField(
                    value = timeDuration,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            timeDuration = it
                        }
                    },
                    label = { Text("Duración (minutos)", fontSize = 15.sp ) },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.red_button_color)),
                    onClick = { navController.popBackStack()},
                ) {
                    Text(text = "Cancelar")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.blue_button_color)),
                    onClick = { if (CheckDates(urlImage,titulo,director,selectedYear,timeDuration)
                        ){
                        val pelicula = Pelicula(code, urlImage, titulo, director, selectedYear, timeDuration)
                        Singleton.movieList().document(pelicula.code).set(pelicula)
                        navController.navigate(AppScreens.MainScreen.route)
                    }else{
                        errorNotificated.value = true
                        errorMesage = "1 o mas campos vacios.\nPor favor complete los campos."
                    }
                              },
                ) {
                    Text(text = "Aceptar")
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

fun CheckDates(urlImage: String, titulo: String, director: String, age: String, duracion: String): Boolean {
    if (urlImage.isBlank() || titulo.isBlank() || director.isBlank() || age.isBlank() || duracion.isBlank()) {
        //"Algun campo esta vacío."
        return false
    }else {
        return true
    }
}

fun generateShortUUID(): String {
    val uuid = UUID.randomUUID()
    return uuid.toString().take(13)
}