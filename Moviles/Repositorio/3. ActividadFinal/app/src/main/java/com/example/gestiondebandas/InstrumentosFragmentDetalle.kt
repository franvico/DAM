package com.example.gestiondebandas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.gestiondebandas.database.Instrumento

class InstrumentosFragmentDetalle : Fragment() {

    private var instrumento : Instrumento? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        // Retorno la vista
        return inflater.inflate(R.layout.instrumentos_fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recupera el instrumento del Bundle

        instrumento = arguments?.getSerializable("instrumento") as Instrumento?

        // Monto los datos del instrumento en los componentes del activity
        instrumento?.let {
            // Muestra los datos en el UI
            view.findViewById<TextView>(R.id.valorTipo).text = it.tipo
            view.findViewById<TextView>(R.id.valorMarca).text = it.marca
            view.findViewById<TextView>(R.id.valorModelo).text = it.modelo
            view.findViewById<TextView>(R.id.valorNumeroDeSerie).text = it.num_serie.toString()
            view.findViewById<TextView>(R.id.valorDNIMusico).text = it.dni_musico.toString()
        }
    }

}