package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface InstrumentoDao {

    @Insert
    fun addInstrumento(instrumento: Instrumento)

    @Query("SELECT * FROM instrumentos")
    fun getInstrumentos(): List<Instrumento>

    @Query("SELECT * FROM instrumentos WHERE dni_musico = :dni_musico")
    fun getIntrumentoPorDniMusico(dni_musico : Int): Instrumento

    @Transaction
    @Query("SELECT * FROM instrumentos")
    fun getInstrumentosConMusicos() : List<InstrumentoConMusico>

    @Transaction
    @Query("SELECT * FROM instrumentos WHERE num_serie = :numSerie")
    fun getInstrumentoConMusico(numSerie : Int) : InstrumentoConMusico
}