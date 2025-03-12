package com.example.gestiondebandas

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
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
            view.findViewById<TextView>(R.id.labelNombre).text = getString(R.string.bandas_detalle_label_nombre)
            view.findViewById<EditText>(R.id.valorNombre).setText(it.banda.nombre)
            view.findViewById<TextView>(R.id.labelCiudad).text = getString(R.string.bandas_detalle_spinner_ciudad)
            view.findViewById<TextView>(R.id.labelSpinnerMusicos).text =  getString(R.string.bandas_detalle_spiner_musicos)

            // Añadir Spinner de ciudades
            val spinnerCiudad = view.findViewById<Spinner>(R.id.spinnerCiudadBanda)
            configurarSpinnerCiudad(spinnerCiudad)

            // Establecer el valor por defecto de la ciudad al de la ciudad de la banda
            val listaCiudades = resources.getStringArray(R.array.ciudades)
            val posicionCiudad = listaCiudades.indexOf(it.banda.ciudad)
            if (posicionCiudad >= 0) {
                spinnerCiudad.setSelection(posicionCiudad)
            }

            // Añadir músicos al spinner de bandas
            val spinnerMusicos = view.findViewById<Spinner>(R.id.spinnerMusicosBanda)
            val listaMusicos = it.musicos.map { musico ->
                "${musico.nombre} ${musico.apellido} - ${musico.dni}"
            }
            val listaFinal = if (listaMusicos.isEmpty()) {
                listOf("No hay músicos en esta banda")
            } else {
                listaMusicos
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, listaFinal)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMusicos.adapter = adapter
        }

        // BOTONES
        // Declaro los botones modificar y eliminar banda
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonModificarBanda = view.findViewById<Button>(R.id.botonModificarBanda)
        val botonEliminarBanda = view.findViewById<Button>(R.id.botonEliminarBanda)

        // Doy funcionalidad a los botones
        botonModificarBanda.setOnClickListener {
            val nuevoNombreBanda = view.findViewById<EditText>(R.id.valorNombre).text.toString().trim()
            val nuevaCiudadBanda = view.findViewById<Spinner>(R.id.spinnerCiudadBanda).selectedItem.toString().trim()

            if (nuevoNombreBanda.isEmpty() || nuevaCiudadBanda.isEmpty()) {
                Toast.makeText(context, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            banda?.let {
                if (it.banda.nombre != nuevoNombreBanda || it.banda.ciudad != nuevaCiudadBanda) {
                    it.banda.nombre = nuevoNombreBanda
                    it.banda.ciudad = nuevaCiudadBanda
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
                else {
                    Toast.makeText(requireContext(), "Actualiza algún campo para modificar", Toast.LENGTH_SHORT).show()
                }
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

    // Configuración del Spinner para las ciudades
    private fun configurarSpinnerCiudad(spinnerCiudad: Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ciudades,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCiudad.adapter = adapter
    }
}