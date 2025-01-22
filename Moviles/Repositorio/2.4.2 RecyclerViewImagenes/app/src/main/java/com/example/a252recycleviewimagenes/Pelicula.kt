package com.example.a252recycleviewimagenes

import java.io.Serializable

data class Pelicula(
    val titulo: String,
    val fecha: String,
    val imagenResId: Int,
    val sinopsis: String
): Serializable
