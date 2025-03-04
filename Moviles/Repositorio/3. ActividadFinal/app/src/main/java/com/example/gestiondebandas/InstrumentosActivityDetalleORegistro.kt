package com.example.gestiondebandas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gestiondebandas.database.Instrumento

class InstrumentosActivityDetalleORegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instrumentos_activity_detalle_o_registro)

        // Obtener el tipo de fragmento desde el Intent
        val fragmentType = intent.getStringExtra("fragment_type")

        // Cargar el fragmento adecuado según el tipo
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (fragmentType == "detalle") {
            // Obtengo el instrumento que se pasa desde la otra activity y lo transformo en un bundle para poder pasárselo al fragment como argument
            val instrumento = intent.getSerializableExtra("instrumento") as? Instrumento
            val bundle = Bundle()
            bundle.putSerializable("instrumento", instrumento)
            // Cargar el fragmento de detalle
            val fragmentDetalle = InstrumentosFragmentDetalle()
            fragmentDetalle.arguments = bundle
            // Reemplazo el fragment
            fragmentTransaction.replace(R.id.fragmentContainerViewInstrumentosDetalleORegistro, fragmentDetalle)
        } else if (fragmentType == "registro") {
            // Cargar el fragmento de registro
            val fragmentRegistro = InstrumentosFragmentRegistro()
            fragmentTransaction.replace(R.id.fragmentContainerViewInstrumentosDetalleORegistro, fragmentRegistro)
        }

        // Asegurarse de que el fragmento se agregue correctamente
        fragmentTransaction.commit()

    }
}