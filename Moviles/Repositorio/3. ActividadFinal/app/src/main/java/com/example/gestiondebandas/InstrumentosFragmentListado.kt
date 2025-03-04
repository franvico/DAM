package com.example.gestiondebandas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class InstrumentosFragmentListado : Fragment() {

    // Obtener el ViewModel -> Para obtener un viewmodel en un fragment tengo que importar la librería implementation("androidx.fragment:fragment-ktx:1.8.3")
    private val instrumentosViewModel: InstrumentosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instrumentos_fragment_listado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuramos el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewListadoInstrumentos)
        val adaptador = InstrumentosAdaptador(emptyList()) { instrumento ->
            // Aquí puedes manejar el clic en el instrumento
            println("Instrumento seleccionado: ${instrumento.tipo}, Nº Serie: ${instrumento.num_serie}")
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                // Transformo el instrumento en un bundle para pasarselo al fragment como argumento
                val bundle = Bundle()
                bundle.putSerializable("instrumento", instrumento)
                // paso el bundle al fragment
                val instrumentosFragmentDetalle = InstrumentosFragmentDetalle()
                instrumentosFragmentDetalle.arguments = bundle
                // reemplazo el fragment
                transaction.replace(
                    R.id.fragmentContainerViewInstrumentosDetalleORegistro,
                    instrumentosFragmentDetalle
                )
                transaction.commit()
            } else{
                val intent = Intent(activity, InstrumentosActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "detalle") // Pasa el tipo de fragmento
                intent.putExtra("instrumento", instrumento)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptador

        // Obtengo los instrumentos y los añado al recyclerView
        instrumentosViewModel.obtenerInstrumentos()

        // Observar el LiveData de los instrumentos
        instrumentosViewModel.instrumentos.observe(viewLifecycleOwner) { instrumentos ->
            // Aquí es donde recibimos la lista de instrumentos que se carga desde la base de datos
            adaptador.actualizarInstrumentos(instrumentos)
        }

        // Declaro el botón crear nuevo intrumento
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonNuevoInstrumentos = view.findViewById<Button>(R.id.botonNuevoInstrumentos)
        val botonDetalleInstrumento = view.findViewById<Button>(R.id.botonDetalleInstrumento)

        // Añado funcionalidad al botón de crear nuevo instrumento
//        botonDetalleInstrumento.setOnClickListener {
//            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
//                val transaction = parentFragmentManager.beginTransaction()
//                transaction.replace(
//                    R.id.fragmentContainerViewInstrumentosDetalleORegistro,
//                    InstrumentosFragmentDetalle()
//                )
//                transaction.commit()
//            } else{
//                val intent = Intent(activity, InstrumentosActivityDetalleORegistro::class.java)
//                intent.putExtra("fragment_type", "detalle") // Pasa el tipo de fragmento
//                startActivity(intent)
//            }
//        }

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