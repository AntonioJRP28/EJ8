package com.example.ej8.model

data class Pelicula(
    val code: String,
    val imagen: String,
    val titulo: String,
    val director: String,
    val anio: String,
    val duracion: String
){
    // Constructor sin argumentos (necesario para Firestore)
    constructor() : this("", "", "", "", "", "")
}

/*
val ListaPeliculas = listOf(
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/john_wick_chapter_two-435532369-large.jpg",
            titulo = "John Wick: Pacto de sangre",
            director = "Chad Stahelski",
            anio = "2017",
            duracion = "122"
        ),
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/the_hunger_games_mockingjay_part_i-441255048-large.jpg",
            titulo = "Los juegos del hambre",
            director = "Gary Ross",
            anio = "2012",
            duracion = "143"
        ),
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/divergent-164628487-large.jpg",
            titulo = "Divergente",
            director = "Neil Burger",
            anio = "2014",
            duracion = "140"
        ),
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/pacific_rim-524748435-large.jpg",
            titulo = "Pacific Rim",
            director = "Guillermo del Toro",
            anio = "2013",
            duracion = "131"
        ),
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/dune-209834814-large.jpg",
            titulo = "Dune",
            director = "Denis Villeneuve",
            anio = "2021",
            duracion = "155"
        ),
        Pelicula.crearPelicula(
            imagen = "https://pics.filmaffinity.com/i_am_legend-580002333-large.jpg",
            titulo = "Soy leyenda",
            director = "Francis Lawrence",
            anio = "2007",
            duracion = "100"
        ),
    )
*/