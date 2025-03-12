package com.example.gestiondebandas.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bandas")
data class Banda (
    @PrimaryKey()
    var nif : Int,
    var nombre : String,
    var ciudad : String
) : Serializable