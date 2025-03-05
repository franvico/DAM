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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BandasFragmentListado : Fragment() {

    // Obtener el ViewModel -> Para obtener un viewmodel en un fragment tengo que importar la librería implementation("androidx.fragment:fragment-ktx:1.8.3")
    private val bandasViewModel: BandasViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptador: BandasAdaptador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bandas_fragment_listado, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuramos el RecyclerView
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewListadoBandas)
        adaptador = BandasAdaptador(emptyList()) { banda ->
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                // Transformo el banda en un bundle para pasarselo al fragment como argumento
                val bundle = Bundle()
                bundle.putSerializable("banda", banda)
                // paso el bundle al fragment
                val bandasFragmentDetalle = BandasFragmentDetalle()
                bandasFragmentDetalle.arguments = bundle
                // reemplazo el fragment
                transaction.replace(
                    R.id.fragmentContainerViewBandasDetalleORegistro,
                    bandasFragmentDetalle
                )
                transaction.commit()
            } else{
                val intent = Intent(activity, BandasActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "detalle") // Pasa el tipo de fragmento
                intent.putExtra("banda", banda)
                startActivity(intent)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adaptador

        // Obtengo los bandas y los añado al recyclerView
        bandasViewModel.obtenerBandasConMusicos()

        // Observar el LiveData de los bandas
        bandasViewModel.bandasConMusicos.observe(viewLifecycleOwner) { bandas ->
            // Aquí es donde recibimos la lista de bandas que se carga desde la base de datos
            adaptador.actualizarBandas(bandas)
        }

        // Declaro el botón crear nueva banda
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonNuevoBandas = view.findViewById<Button>(R.id.botonNuevaBanda)
        // Doy funcionalidad al botón
        botonNuevoBandas.setOnClickListener {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Estamos en horizontal, reemplazamos el fragment en el contenedor adecuado
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(
                    R.id.fragmentContainerViewBandasDetalleORegistro,
                    BandasFragmentRegistro()
                )
                transaction.commit()
            } else {
                val intent = Intent(activity, BandasActivityDetalleORegistro::class.java)
                intent.putExtra("fragment_type", "registro") // Pasa el tipo de fragmento
                startActivity(intent)
            }
        }
    }
    fun actualizarListado() {
        bandasViewModel.obtenerBandasConMusicos()  // Aquí obtienes las bandas desde el ViewModel
        bandasViewModel.bandasConMusicos.observe(viewLifecycleOwner) { bandasConMusicos ->
            // Actualizas el adaptador con los nuevos datos
            (recyclerView.adapter as BandasAdaptador).actualizarBandas(bandasConMusicos)
        }
    }
    override fun onResume() {
        super.onResume()
        // Recargar la lista de bandas cada vez que el fragmento vuelva a estar visible (por ejemplo al volver hacia atrás a esta vista)
        bandasViewModel.obtenerBandasConMusicos()
    }
}