package com.example.gestiondebandas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gestiondebandas.database.BandaConMusicos

class BandasActivityDetalleORegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bandas_activity_detalle_o_registro)

        // Obtener el tipo de fragmento desde el Intent
        val fragmentType = intent.getStringExtra("fragment_type")

        // Cargar el fragmento adecuado según el tipo
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (fragmentType == "detalle") {
            // Obtengo la banda que se pasa desde la otra activity y la transformo en un bundle para poder pasársela al fragment como argument
            val banda = intent.getSerializableExtra("banda") as? BandaConMusicos
            val bundle = Bundle()
            bundle.putSerializable("banda", banda)
            // Cargar el fragmento de detalle
            val fragmentDetalle = BandasFragmentDetalle()
            fragmentDetalle.arguments = bundle
            // Reemplazo el fragment
            fragmentTransaction.replace(R.id.fragmentContainerViewBandasDetalleORegistro, fragmentDetalle)
        } else if (fragmentType == "registro") {
            // Cargar el fragmento de registro
            val fragmentRegistro = BandasFragmentRegistro()
            fragmentTransaction.replace(R.id.fragmentContainerViewBandasDetalleORegistro, fragmentRegistro)
        }

        // Asegurarse de que el fragmento se agregue correctamente
        fragmentTransaction.commit()
    }
}