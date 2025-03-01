package com.example.gestiondebandas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class InstrumentosActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instrumentos_activity)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerViewInstrumentosActivity, InstrumentosFragmentListado())
//                .commit()
//
//            // Solo cargar el segundo fragmento si estamos en horizontal
//            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerViewInstrumentosDetalleORegistro, InstrumentosFragmentDetalle())
//                    .commit()
//            }
//        }
    }
}