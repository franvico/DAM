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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestiondebandas.database.Instrumento

class InstrumentosFragmentRegistro : Fragment() {

    private val instrumentosViewModel: InstrumentosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.instrumentos_fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerTipo: Spinner = view.findViewById(R.id.spinnerTipo)
        val inputMarca: EditText = view.findViewById(R.id.inputMarca)
        val inputModelo: EditText = view.findViewById(R.id.inputModelo)
        val inputNumeroDeSerie: EditText = view.findViewById(R.id.inputNumeroDeSerie)
        val inputDNIMusico: EditText = view.findViewById(R.id.inputDNIMusico)
        val botonGuardar: Button = view.findViewById(R.id.botonRegistrarInstrumento)

        configurarSpinnerTipo(spinnerTipo)

        botonGuardar.setOnClickListener {
            val tipo = spinnerTipo.selectedItem.toString()
            val marca = inputMarca.text.toString()
            val modelo = inputModelo.text.toString()
            val numSerie = inputNumeroDeSerie.text.toString().toIntOrNull()
            val dniMusico = inputDNIMusico.text.toString().toIntOrNull()

            if (tipo.isNotEmpty() && marca.isNotEmpty() && modelo.isNotEmpty() && numSerie != null && dniMusico != null) {
                val instrumento = Instrumento(
                    num_serie = numSerie,
                    tipo = tipo,
                    marca = marca,
                    modelo = modelo,
                    dni_musico = dniMusico
                )

                instrumentosViewModel.registrarInstrumento(
                    instrumento,
                    onSuccess = {
                        Toast.makeText(requireContext(), "Instrumento guardado", Toast.LENGTH_SHORT).show()
                        // Cerrar la actividad actual
                        requireActivity().finish()

                        // Crear un Intent para abrir la actividad "InstrumentosActivity"
                        val intent = Intent(requireContext(), InstrumentosActivity::class.java)

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

    private fun configurarSpinnerTipo(spinnerTipo : Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.tipos_instrumentos,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter
    }
}