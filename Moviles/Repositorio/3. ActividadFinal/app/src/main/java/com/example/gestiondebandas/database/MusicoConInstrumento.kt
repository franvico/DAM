package com.example.gestiondebandas.database

import androidx.room.Embedded
import androidx.room.Relation

data class MusicoConInstrumento(
    @Embedded val musico: Musico,
    @Relation(
        parentColumn = "dni",
        entityColumn = "dni_musico"
    )
    val instrumento: Instrumento
)
