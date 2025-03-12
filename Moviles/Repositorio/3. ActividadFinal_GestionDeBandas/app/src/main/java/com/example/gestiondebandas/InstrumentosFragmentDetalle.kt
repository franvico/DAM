package com.example.gestiondebandas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestiondebandas.database.Instrumento
import org.json.JSONObject
import java.io.InputStream

class InstrumentosFragmentDetalle : Fragment() {

    private var instrumento : Instrumento? = null
    private val instrumentosViewModel: InstrumentosViewModel by viewModels()

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
            view.findViewById<TextView>(R.id.labelTipo).text = getString(R.string.instrumentos_detalle_label_tipo)
            view.findViewById<TextView>(R.id.valorTipo).text = it.tipo
            view.findViewById<TextView>(R.id.labelMarca).text = getString(R.string.instrumentos_detalle_label_marca)
            view.findViewById<TextView>(R.id.valorMarca).text = it.marca
            view.findViewById<TextView>(R.id.labelModelo).text = getString(R.string.instrumentos_detalle_label_modelo)
            view.findViewById<TextView>(R.id.valorModelo).text = it.modelo
            view.findViewById<TextView>(R.id.labelNumeroDeSerie).text = getString(R.string.instrumentos_detalle_label_num_serie)
            view.findViewById<TextView>(R.id.valorNumeroDeSerie).text = it.num_serie.toString()
            view.findViewById<TextView>(R.id.labelDNIMusico).text = getString(R.string.instrumentos_detalle_label_dni_musico)
            view.findViewById<EditText>(R.id.valorDNIMusico).setText(it.dni_musico.toString())

            // Obtener las correspondencias entre tipo de instrumento y nombre de imagen
            val tipoAImagen = obtenerCorrespondencias()
            // Obtener el nombre de la imagen correspondiente al tipo de instrumento
            val nombreImagen = tipoAImagen[it.tipo]
            // Establecer la imagen en el ImageView
            val imagenInstrumento = view.findViewById<ImageView>(R.id.imagenInstrumento)
            imagenInstrumento.setImageResource(resources.getIdentifier(nombreImagen, "drawable", context?.packageName))
        }

        // BOTONES
        // Declaro los botones modificar DNI músico y eliminar instrumento
        // a diferencia de en una AppCompatActivity, en un fragment se recogen los id de los elementos una vez se haya inflado el fragment
        val botonModificarMusico = view.findViewById<Button>(R.id.botonModificarMusico)
        val botonEliminarInstrumento = view.findViewById<Button>(R.id.botonEliminarInstrumento)
        // Doy funcionalidad a los botones
        botonModificarMusico.setOnClickListener {
            val nuevoDniMusico = view.findViewById<EditText>(R.id.valorDNIMusico).text.toString().trim().toIntOrNull()

            if (nuevoDniMusico == null) {
                Toast.makeText(context, "No puede haber campos vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            instrumento?.let {
                if (it.dni_musico != nuevoDniMusico) {
                    it.dni_musico = nuevoDniMusico
                    // Actualizar el instrumento en la base de datos
                    instrumentosViewModel.actualizarInstrumento(it, onSuccess = {
                        // Mostrar mensaje de éxito
                        Toast.makeText(
                            requireContext(),
                            "Instrumento actualizado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }, onError = { errorMsg ->
                        println("Error al eliminar: $errorMsg")
                        Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_LONG)
                            .show()
                    })
                }else {
                    Toast.makeText(requireContext(), "Actualiza el DNI del músico para modificar", Toast.LENGTH_SHORT).show()
                }
            }
        }
        botonEliminarInstrumento.setOnClickListener {
            instrumento?.let {
                instrumentosViewModel.eliminarInstrumento(it, onSuccess = {
                    Toast.makeText(requireContext(),"Instrumento eliminado",Toast.LENGTH_SHORT).show()
                    // Redirigir a InstrumentosActivity si la eliminación fue exitosa
                    val intent = Intent(requireContext(), InstrumentosActivity::class.java)
                    startActivity(intent)
                    activity?.finish() // Finaliza esta actividad para volver a InstrumentosActivity
                }, onError = { errorMsg ->
                    Toast.makeText(requireContext(), "Error al eliminar: $errorMsg", Toast.LENGTH_LONG).show()
                })
            }
        }
    }

    // Cargar las correspondencias entre los tipos de instrumentos y las imágenes desde el archivo JSON
    private fun obtenerCorrespondencias(): Map<String, String> {
        val jsonString = cargarJsonDesdeRaw(R.raw.imagenes_instrumento)
        val jsonObject = JSONObject(jsonString)
        val correspondencias = mutableMapOf<String, String>()

        // Iterar a través de las claves (tipos de instrumentos)
        jsonObject.keys().forEach { key ->
            val imageName = jsonObject.getString(key)
            correspondencias[key] = imageName
        }
        return correspondencias
    }

    // Cargar el archivo JSON desde los recursos raw
    private fun cargarJsonDesdeRaw(resourceId: Int): String {
        val inputStream: InputStream = resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }

}