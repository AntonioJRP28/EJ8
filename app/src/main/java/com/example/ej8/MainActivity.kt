package com.example.ej8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ej8.navigation.Navigation
import com.example.ej8.ui.theme.EJ8Theme
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EJ8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    Navigation()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun test(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.red_button_color))
                .padding(16.dp)
                .weight(1f),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.blue_button_color))

                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = "hola", onValueChange = {})
        }
        Row(
            modifier = Modifier
                //.fillMaxWidth()
                .background(colorResource(id = R.color.red_button_color))
                .padding(16.dp)
                .weight(1f),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {

        }
    }
}