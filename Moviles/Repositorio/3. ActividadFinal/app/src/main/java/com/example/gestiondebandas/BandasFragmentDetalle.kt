package com.example.gestiondebandas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.gestiondebandas.database.BandaConMusicos

class BandasFragmentDetalle : Fragment() {

    private var banda : BandaConMusicos? = null
    private val bandasViewModel: BandasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retorno la vista
        return inflater.inflate(R.layout.bandas_fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera el banda del Bundle
        banda = arguments?.getSerializable("banda") as BandaConMusicos?

        // Monto los datos del banda en los componentes del activity
        banda?.let {
            // Muestra los datos en el UI
            view.findViewById<TextView>(R.id.labelNIF).text = "NIF"
            view.findViewById<TextView>(R.id.valorNIF).text = it.banda.nif.toString()
            view.findViewById<TextView>(R.id.labelNombre).text = "Nombre"
            view.findViewById<EditText>(R.id.valorNombre).hint = it.banda.nombre
            view.findViewById<TextView>(R.id.labelCiudad).text = "Ciudad"
            view.findViewById<EditText>(R.id.valorCiudad).hint = it.banda.ciudad
        }

        // BOTONES
        // Declaro los botones modificar y eliminar banda
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonModificarBanda = view.findViewById<Button>(R.id.botonModificarBanda)
        val botonEliminarBanda = view.findViewById<Button>(R.id.botonEliminarBanda)
        // Doy funcionalidad a los botones
        botonModificarBanda.setOnClickListener {
            val nuevoNombreBanda = view.findViewById<EditText>(R.id.valorNombre).text
            val nuevoNombre = nuevoNombreBanda

            if (!nuevoNombre.equals("")) {
                banda?.let {
                    if (!it.banda.nombre.equals(nuevoNombre)) {
                        it.banda.nombre = nuevoNombre.toString()
                        // Actualizar el banda en la base de datos
                        bandasViewModel.actualizarBanda(it.banda, onSuccess = {
                            // Mostrar mensaje de éxito
                            Toast.makeText(requireContext(),"Banda actualizada", Toast.LENGTH_SHORT).show()
                            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                // Si estamos en horizontal, recargamos las bandas para que se actualicen en BandasFragmentListado
                                bandasViewModel.obtenerBandasConMusicos()
                            }
                        }, onError = { errorMsg ->
                            println("Error al eliminar: $errorMsg")
                            Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_LONG).show()
                        })
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Datos no válidos", Toast.LENGTH_SHORT).show()
            }
        }
        botonEliminarBanda.setOnClickListener {
            banda?.let {
                bandasViewModel.eliminarBanda(it.banda, onSuccess = {
                    Toast.makeText(requireContext(),"Banda eliminada", Toast.LENGTH_SHORT).show()
                    // Redirigir a BandasActivity si la eliminación fue exitosa
                    val intent = Intent(requireContext(), BandasActivity::class.java)
                    startActivity(intent)
                    activity?.finish() // Finaliza esta actividad para volver a BandasActivity
                }, onError = { errorMsg ->
                    Toast.makeText(requireContext(), "Error al eliminar: $errorMsg", Toast.LENGTH_LONG).show()
                })
            }
        }
    }
}