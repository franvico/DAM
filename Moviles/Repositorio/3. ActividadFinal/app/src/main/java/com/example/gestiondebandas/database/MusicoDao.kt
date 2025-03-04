package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface MusicoDao {
    @Insert
    fun addMusico(musico: Musico)

    @Query("SELECT * FROM musicos")
    fun getMusicos() : List<Musico>

    @Query("SELECT * FROM musicos WHERE dni = :dniMusico")
    fun getMusicoPorDni(dniMusico : Int) : Musico

    @Transaction
    @Query("SELECT * FROM musicos")
    fun getMusicosConInstrumento() : List<MusicoConInstrumento>

    @Transaction
    @Query("SELECT * FROM musicos WHERE dni = :dni")
    fun getMusicoConInstrumento(dni : Int) : MusicoConInstrumento
}