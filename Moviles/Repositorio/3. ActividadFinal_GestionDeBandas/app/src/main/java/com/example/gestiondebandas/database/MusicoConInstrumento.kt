package com.example.gestiondebandas.database

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class MusicoConInstrumento(
    @Embedded val musico: Musico,
    @Relation(
        parentColumn = "dni",
        entityColumn = "dni_musico"
    )
    val instrumento: Instrumento?
) : Serializable
