package com.example.gestiondebandas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class InstrumentosFragmentListado : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instrumentos_fragment_listado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonNuevoInstrumentos = view.findViewById<Button>(R.id.botonNuevoInstrumentos)
        val botonDetalleInstrumento = view.findViewById<Button>(R.id.botonDetalleInstrumento)

        botonDetalleInstrumento.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentContainerViewInstrumentosDetalleORegistro,
                    InstrumentosFragmentDetalle()
                )
                transaction.commit()
            } else{
                val intent = Intent(activity, InstrumentosActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "detalle") // Pasa el tipo de fragmento
                startActivity(intent)
            }
        }

        botonNuevoInstrumentos.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentContainerViewInstrumentosDetalleORegistro,
                    InstrumentosFragmentRegistro()
                )
                transaction.commit()
            } else {
                val intent = Intent(activity, InstrumentosActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "registro") // Pasa el tipo de fragmento
                startActivity(intent)
            }
        }
    }

}