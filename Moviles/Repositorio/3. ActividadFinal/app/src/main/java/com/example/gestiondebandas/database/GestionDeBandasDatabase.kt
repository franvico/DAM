package com.example.gestiondebandas.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Instrumento::class, Musico::class, Banda::class], version = 2)
abstract class GestionDeBandasDatabase : RoomDatabase() {
    abstract fun instrumentoDao() : InstrumentoDao
    abstract fun musicoDao() : MusicoDao
    abstract fun bandaDao() : BandaDao
}
