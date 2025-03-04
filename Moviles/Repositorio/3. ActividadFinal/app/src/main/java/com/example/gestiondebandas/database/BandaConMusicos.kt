package com.example.gestiondebandas.database

import androidx.room.Embedded
import androidx.room.Relation

data class BandaConMusicos(
    @Embedded val banda : Banda,
    @Relation(
        parentColumn = "nif",
        entityColumn = "nif_banda"
    )
    val musicos : List<Musico>
)
