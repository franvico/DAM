package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BandaDao {

    @Insert
    fun addBanda(banda: Banda)

    @Query("SELECT * FROM bandas")
    fun getAllBandas() : List<Banda>

    @Query("SELECT * FROM bandas WHERE nif = :nif")
    fun getBandaPorNif(nif: Int): Banda?

    @Query("SELECT * FROM bandas WHERE ciudad = :ciudad")
    fun getBandasPorCiudad(ciudad : String) : List<Banda>
}