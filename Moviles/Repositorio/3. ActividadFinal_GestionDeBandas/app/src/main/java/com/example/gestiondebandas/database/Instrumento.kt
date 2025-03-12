package com.example.gestiondebandas.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "instrumentos",
    indices = [Index(value = ["dni_musico"], unique = true)] // el atributo dni_musico debe ser único para que se cumpla una relación 1:1 entre músico e instrumento
)
data class Instrumento(
    @PrimaryKey
    var num_serie : Int,
    var tipo : String,
    var marca : String,
    var modelo : String,
    var dni_musico : Int
) : Serializable