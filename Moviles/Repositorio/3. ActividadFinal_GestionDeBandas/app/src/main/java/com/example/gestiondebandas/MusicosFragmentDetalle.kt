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
import com.example.gestiondebandas.database.MusicoConInstrumento

class MusicosFragmentDetalle : Fragment() {

    private var musico : MusicoConInstrumento? = null
    private val musicosViewModel: MusicosViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retorno la vista
        return inflater.inflate(R.layout.musicos_fragment_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera el musico del Bundle
        musico = arguments?.getSerializable("musico") as MusicoConInstrumento?

        // Monto los datos del musico en los componentes del activity
        musico?.let {
            // Muestra los datos en el UI
            view.findViewById<TextView>(R.id.labelDNI).text = "DNI"
            view.findViewById<TextView>(R.id.valorDNI).text = it.musico.dni.toString()
            view.findViewById<TextView>(R.id.labelNombre).text = getString(R.string.musicos_detalle_label_nombre)
            view.findViewById<EditText>(R.id.valorNombre).setText(it.musico.nombre)
            view.findViewById<TextView>(R.id.labelApellido).text = getString(R.string.musicos_detalle_label_apellido)
            view.findViewById<EditText>(R.id.valorApellido).setText(it.musico.apellido)
            view.findViewById<TextView>(R.id.labelTipoInstrumento).text = getString(R.string.musicos_detalle_label_instrumento)
            view.findViewById<TextView>(R.id.valorTipoInstrumento).text = it.instrumento?.tipo ?: getString(R.string.musicos_detalle_mesaje_sin_instrumento)
            view.findViewById<TextView>(R.id.labelNIFBanda).text = getString(R.string.musicos_detalle_label_nif_banda)
            view.findViewById<EditText>(R.id.valorNIFBanda).setText(it.musico.nif_banda.toString())
        }

        // BOTONES
        // Declaro los botones modificar y eliminar musico
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonModificarMusico = view.findViewById<Button>(R.id.botonModificarMusico)
        val botonEliminarMusico = view.findViewById<Button>(R.id.botonEliminarMusico)
        // Doy funcionalidad a los botones
        botonModificarMusico.setOnClickListener {
            val nuevoNombreMusico = view.findViewById<EditText>(R.id.valorNombre).text.toString().trim()
            val nuevoApellidoMusico = view.findViewById<EditText>(R.id.valorApellido).text.toString().trim()
            val nuevoNIFBanda = view.findViewById<EditText>(R.id.valorNIFBanda).text.toString().trim().toIntOrNull()

            if (nuevoNombreMusico.isEmpty() || nuevoApellidoMusico.isEmpty() || nuevoNIFBanda == null) {
                Toast.makeText(context, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            musico?.let {
                if (it.musico.nombre != nuevoNombreMusico || it.musico.apellido != nuevoApellidoMusico || it.musico.nif_banda != nuevoNIFBanda) {
                    it.musico.nombre = nuevoNombreMusico.toString()
                    it.musico.apellido = nuevoApellidoMusico.toString()
                    it.musico.nif_banda = nuevoNIFBanda
                    // Actualizar el musico en la base de datos
                    musicosViewModel.actualizarMusico(it.musico, onSuccess = {
                        // Mostrar mensaje de éxito
                        Toast.makeText(requireContext(),"Musico actualizado", Toast.LENGTH_SHORT).show()
                        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            // Si estamos en horizontal, recargamos las musicos para que se actualicen en MusicosFragmentListado
                            musicosViewModel.obtenerMusicosConInstrumento()
                        }
                    }, onError = { errorMsg ->
                        println("Error al actualizar: $errorMsg")
                        Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_LONG).show()
                    })
                } else {
                    Toast.makeText(requireContext(), "Actualiza algún campo para modificar", Toast.LENGTH_SHORT).show()
                }
            }
        }
        botonEliminarMusico.setOnClickListener {
            musico?.let {
                musicosViewModel.eliminarMusico(it.musico, onSuccess = {
                    Toast.makeText(requireContext(),"Musico eliminado", Toast.LENGTH_SHORT).show()
                    // Redirigir a MusicosActivity si la eliminación fue exitosa
                    val intent = Intent(requireContext(), MusicosActivity::class.java)
                    startActivity(intent)
                    activity?.finish() // Finaliza esta actividad para volver a MusicosActivity
                }, onError = { errorMsg ->
                    Toast.makeText(requireContext(), "Error al eliminar: $errorMsg", Toast.LENGTH_LONG).show()
                })
            }
        }
    }
}