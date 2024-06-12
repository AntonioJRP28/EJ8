package com.example.ej8.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ej8.model.Pelicula
import com.google.firebase.firestore.FirebaseFirestore

object Singleton {

    fun instanciaFirestore() = FirebaseFirestore.getInstance()

    fun movieList() = instanciaFirestore().collection("peliculas")

    val listaPeliculas: MutableList<Pelicula> = mutableListOf()

    init {
        movieList().addSnapshotListener { querySnapshot, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            listaPeliculas.clear()
            for (document in querySnapshot?.documents ?: emptyList()) {
                val pelicula = document.toObject(Pelicula::class.java)
                pelicula?.let { listaPeliculas.add(it) }
            }
        }
    }

    var code = ""

}