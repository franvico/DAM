package com.example.gestiondebandas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MusicosFragmentListado : Fragment() {

    // Obtener el ViewModel -> Para obtener un viewmodel en un fragment tengo que importar la librería implementation("androidx.fragment:fragment-ktx:1.8.3")
    private val musicosViewModel: MusicosViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: MusicosAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.musicos_fragment_listado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuramos el RecyclerView
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewListadoMusicos)
        adaptador = MusicosAdaptador(emptyList()) { musico ->
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                // Transformo el musico en un bundle para pasarselo al fragment como argumento
                val bundle = Bundle()
                bundle.putSerializable("musico", musico)
                // paso el bundle al fragment
                val musicosFragmentDetalle = MusicosFragmentDetalle()
                musicosFragmentDetalle.arguments = bundle
                // reemplazo el fragment
                transaction.replace(
                    R.id.fragmentContainerViewMusicosDetalleORegistro,
                    musicosFragmentDetalle
                )
                transaction.commit()
            } else{
                val intent = Intent(activity, MusicosActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "detalle") // Pasa el tipo de fragmento
                intent.putExtra("musico", musico)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptador

        // Obtengo los musicos y los añado al recyclerView
        musicosViewModel.obtenerMusicosConInstrumento()

        // Observar el LiveData de los musicos
        musicosViewModel.musicosConMusicos.observe(viewLifecycleOwner) { musicos ->
            // Aquí es donde recibimos la lista de musicos que se carga desde la base de datos
            adaptador.actualizarMusicos(musicos)
        }

        // Declaro el botón crear nuevo musico
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonNuevoMusicos = view.findViewById<Button>(R.id.botonNuevoMusico)
        // Doy funcionalidad al botón
        botonNuevoMusicos.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentContainerViewMusicosDetalleORegistro,
                    MusicosFragmentRegistro()
                )
                transaction.commit()
            } else {
                val intent = Intent(activity, MusicosActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "registro") // Pasa el tipo de fragmento
                startActivity(intent)
            }
        }
    }
    fun actualizarListado() {
        musicosViewModel.obtenerMusicosConInstrumento()  // Aquí obtienes los musicos desde el ViewModel
        musicosViewModel.musicosConMusicos.observe(viewLifecycleOwner) { musicosConMusicos ->
            // Actualizas el adaptador con los nuevos datos
            (recyclerView.adapter as MusicosAdaptador).actualizarMusicos(musicosConMusicos)
        }
    }
    override fun onResume() {
        super.onResume()
        // Recargar la lista de musicos cada vez que el fragmento vuelva a estar visible (por ejemplo al volver hacia atrás a esta vista)
        musicosViewModel.obtenerMusicosConInstrumento()
    }
}