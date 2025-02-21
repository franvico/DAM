package com.example.bd1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bd1.Database.TaskEntity
//import com.example.bd1.ui.theme.Bd1Theme
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // Inicializa el viewModel
    //by viewModels() se usa para inicializar un ViewModel de forma eficiente y segura
    // dentro del ciclo de vida de la UI.
    private val viewModel: MainViewModel by viewModels()

    lateinit var nombre:TextInputEditText
    lateinit var apellido:TextInputEditText
    lateinit var btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Recoger elementos
        nombre = findViewById<TextInputEditText>(R.id.etNombre)
        apellido = findViewById<TextInputEditText>(R.id.etApellido)
        btn = findViewById<Button>(R.id.btnGuardar)

        btn.setOnClickListener {
            //Guardo info.
            val info:TaskEntity = TaskEntity(0, nombre.text.toString(),apellido.text.toString())
            viewModel.anyadirTarea(info)
        }
    }
}
