package com.example.gestiondebandas.database

import androidx.room.Embedded
import androidx.room.Relation

data class InstrumentoConMusico(
    @Embedded val instrumento: Instrumento,
    @Relation(
        parentColumn = "dni_musico",
        entityColumn = "dni"
    )
    val musico: Musico
)
