package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface MusicoDao {
    @Insert
    fun addMusico(musico: Musico)

    @Query("SELECT * FROM musicos")
    fun getMusicos() : List<Musico>

    @Query("SELECT * FROM musicos WHERE dni = :dniMusico")
    fun getMusicoPorDni(dniMusico : Int) : Musico

    // Método para actualizar una músico en la base de datos (devuelve el número de filas modificadas)
    @Update
    fun actualizarMusico(musico: Musico): Int

    // Método para eliminar una músico de la base de datos (devuelve el número de filas eliminadas)
    @Delete
    fun eliminarMusico(musico: Musico): Int

    @Transaction
    @Query("SELECT * FROM musicos")
    fun getMusicosConInstrumento() : List<MusicoConInstrumento>

    @Transaction
    @Query("SELECT * FROM musicos WHERE dni = :dni")
    fun getMusicoConInstrumento(dni : Int) : MusicoConInstrumento
}