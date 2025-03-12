package com.example.gestiondebandas.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "musicos")
data class Musico (
    @PrimaryKey
    var dni : Int,
    var nombre : String,
    var apellido : String,
    var nif_banda : Int
) : Serializable