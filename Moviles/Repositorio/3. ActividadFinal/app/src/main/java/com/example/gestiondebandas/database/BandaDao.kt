package com.example.gestiondebandas.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface BandaDao {

    @Insert
    fun addBanda(banda: Banda)

    @Query("SELECT * FROM bandas")
    fun getBandas() : List<Banda>

    @Query("SELECT * FROM bandas WHERE nif = :nif")
    fun getBandaPorNif(nif: Int): Banda?

    @Query("SELECT * FROM bandas WHERE ciudad = :ciudad")
    fun getBandasPorCiudad(ciudad : String) : List<Banda>

    // Método para actualizar una banda en la base de datos (devuelve el número de filas modificadas)
    @Update
    fun actualizarBanda(banda: Banda): Int

    // Método para eliminar una banda de la base de datos (devuelve el número de filas eliminadas)
    @Delete
    fun eliminarBanda(banda: Banda): Int

    @Transaction
    @Query("SELECT * FROM bandas")
    fun getBandasConMusicos() : List<BandaConMusicos>

    @Transaction
    @Query("SELECT * FROM bandas WHERE nif = :nif")
    fun getBandaConMusicos(nif : Int) : BandaConMusicos
}