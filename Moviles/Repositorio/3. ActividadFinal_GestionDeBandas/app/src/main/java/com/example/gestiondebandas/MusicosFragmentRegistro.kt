package com.example.gestiondebandas

import android.content.Intent
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
import androidx.fragment.app.viewModels
import com.example.gestiondebandas.database.Banda
import com.example.gestiondebandas.database.Musico

class MusicosFragmentRegistro : Fragment() {

    private val musicosViewModel: MusicosViewModel by viewModels()
    private val bandasViewModel: BandasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.musicos_fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputDNI: EditText = view.findViewById(R.id.inputDNI)
        val inputNombre: EditText = view.findViewById(R.id.inputNombre)
        val inputApellido: EditText = view.findViewById(R.id.inputApellido)
        val spinnerBandas: Spinner = view.findViewById(R.id.spinnerBandas)
        val botonGuardar: Button = view.findViewById(R.id.botonRegistrarMusico)

        configurarSpinnerBandas(spinnerBandas)

        botonGuardar.setOnClickListener {
            val dni = inputDNI.text.toString().toIntOrNull()
            val nombre = inputNombre.text.toString()
            val apellido = inputApellido.text.toString()
            val ciudad = spinnerBandas.selectedItem.toString()
            val bandaSeleccionada = spinnerBandas.selectedItem as Banda

            if (dni != null && nombre.isNotEmpty() && apellido.isNotEmpty()) {
                val musico = Musico(
                    dni = dni,
                    nombre = nombre,
                    apellido = apellido,
                    nif_banda = bandaSeleccionada.nif
                )

                musicosViewModel.registrarMusico(
                    musico,
                    onSuccess = {
                        Toast.makeText(requireContext(), "Musico guardado", Toast.LENGTH_SHORT).show()
                        // Cerrar la actividad actual
                        requireActivity().finish()

                        // Crear un Intent para abrir la actividad "MusicosActivity"
                        val intent = Intent(requireContext(), MusicosActivity::class.java)

                        // Iniciar la actividad
                        startActivity(intent)
                    },
                    onError = { errorMsg ->
                        Toast.makeText(requireContext(), "Error: $errorMsg", Toast.LENGTH_LONG).show()
                    }
                )
            } else {
                Toast.makeText(requireContext(), "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun configurarSpinnerBandas(spinnerBandas : Spinner) {
        // aquí añadir al spinner tantas opciones como registros de bandas haya
        // mostrar su nombre y al seleccionarlo pillar si nif
        // Obtener la lista de bandas desde el BandasViewModel
        // Ejecutamos la consulta en un hilo secundario para no bloquear el hilo principal
        Thread {
            try {
                val bandas = GestionDeBandasApp.database.bandaDao().getBandas()

                // Crear un adaptador que muestre el "Nombre Banda - NIF"
                val adapter = object : ArrayAdapter<Banda>(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    bandas
                ) {
                    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val view = super.getView(position, convertView, parent)
                        val banda = getItem(position)
                        if (banda != null) {
                            (view as TextView).text = "${banda.nif} - ${banda.nombre}" // Formateamos el texto
                        }
                        return view
                    }

                    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                        val view = super.getDropDownView(position, convertView, parent)
                        val banda = getItem(position)
                        if (banda != null) {
                            (view as TextView).text = "${banda.nombre} - ${banda.nif}" // Formateamos el texto
                        }
                        return view
                    }
                }

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                // Actualizar el adaptador en el hilo principal
                requireActivity().runOnUiThread {
                    spinnerBandas.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}