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
import com.example.gestiondebandas.database.Banda

class BandasFragmentRegistro : Fragment() {

    private val bandasViewModel: BandasViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bandas_fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputNIF: EditText = view.findViewById(R.id.inputNIF)
        val inputNombre: EditText = view.findViewById(R.id.inputNombre)
        val spinnerCiudad: Spinner = view.findViewById(R.id.spinnerCiudad)
        val botonGuardar: Button = view.findViewById(R.id.botonRegistrarBanda)

        configurarSpinnerCiudad(spinnerCiudad)

        botonGuardar.setOnClickListener {
            val nif = inputNIF.text.toString().toIntOrNull()
            val nombre = inputNombre.text.toString()
            val ciudad = spinnerCiudad.selectedItem.toString()

            if (nif != null && nombre.isNotEmpty() && ciudad.isNotEmpty()) {
                val banda = Banda(
                    nif = nif,
                    nombre = nombre,
                    ciudad = ciudad
                )

                bandasViewModel.registrarBanda(
                    banda,
                    onSuccess = {
                        Toast.makeText(requireContext(), "Banda guardada", Toast.LENGTH_SHORT).show()
                        // Cerrar la actividad actual
                        requireActivity().finish()

                        // Crear un Intent para abrir la actividad "BandasActivity"
                        val intent = Intent(requireContext(), BandasActivity::class.java)

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

    private fun configurarSpinnerCiudad(spinnerTipo : Spinner) {
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.ciudades,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter
    }
}