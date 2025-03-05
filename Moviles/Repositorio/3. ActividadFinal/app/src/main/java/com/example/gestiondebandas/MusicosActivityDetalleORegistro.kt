package com.example.gestiondebandas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gestiondebandas.database.MusicoConInstrumento

class MusicosActivityDetalleORegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.musicos_activity_detalle_o_registro)

        // Obtener el tipo de fragmento desde el Intent
        val fragmentType = intent.getStringExtra("fragment_type")

        // Cargar el fragmento adecuado según el tipo
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (fragmentType == "detalle") {
            // Obtengo el músico que se pasa desde la otra activity y lo transformo en un bundle para poder pasárselo al fragment como argument
            val musico = intent.getSerializableExtra("musico") as? MusicoConInstrumento
            val bundle = Bundle()
            bundle.putSerializable("musico", musico)
            // Cargar el fragmento de detalle
            val fragmentDetalle = MusicosFragmentDetalle()
            fragmentDetalle.arguments = bundle
            // Reemplazo el fragment
            fragmentTransaction.replace(R.id.fragmentContainerViewMusicosDetalleORegistro, fragmentDetalle)
        } else if (fragmentType == "registro") {
            // Cargar el fragmento de registro
            val fragmentRegistro = MusicosFragmentRegistro()
            fragmentTransaction.replace(R.id.fragmentContainerViewMusicosDetalleORegistro, fragmentRegistro)
        }

        // Asegurarse de que el fragmento se agregue correctamente
        fragmentTransaction.commit()
    }
}