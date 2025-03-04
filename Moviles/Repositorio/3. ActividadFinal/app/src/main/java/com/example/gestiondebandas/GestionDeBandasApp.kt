package com.example.gestiondebandas

import android.app.Application
import androidx.room.Room
import com.example.gestiondebandas.database.GestionDeBandasDatabase

class GestionDeBandasApp : Application() {
    companion object {
        lateinit var database: GestionDeBandasDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, GestionDeBandasDatabase::class.java, "gestiondebandas-db").fallbackToDestructiveMigration().build()
    }
}