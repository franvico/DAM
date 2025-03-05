package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface InstrumentoDao {

    @Insert
    fun addInstrumento(instrumento: Instrumento)

    @Query("SELECT * FROM instrumentos")
    fun getInstrumentos(): List<Instrumento>

    @Query("SELECT * FROM instrumentos WHERE dni_musico = :dni_musico")
    fun getInstrumentoPorDniMusico(dni_musico : Int): Instrumento

    // Método para actualizar un instrumento en la base de datos (devuelve el número de filas modificadas)
    @Update
    fun actualizarInstrumento(instrumento: Instrumento): Int

    // Método para eliminar un instrumento de la base de datos (devuelve el número de filas eliminadas)
    @Delete
    fun eliminarInstrumento(instrumento: Instrumento): Int

    @Transaction
    @Query("SELECT * FROM instrumentos")
    fun getInstrumentosConMusicos() : List<InstrumentoConMusico>

    @Transaction
    @Query("SELECT * FROM instrumentos WHERE num_serie = :numSerie")
    fun getInstrumentoConMusico(numSerie : Int) : InstrumentoConMusico
}