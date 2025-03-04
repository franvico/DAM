package com.example.gestiondebandas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gestiondebandas.database.Banda
import com.example.gestiondebandas.ui.theme.GestionDeBandasTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Hago una interacción con la base de datos para poder usar el AppInspector
        val nif = 1234
        val thread = Thread {
            val banda = GestionDeBandasApp.database.bandaDao().getBandaPorNif(nif)
            if (banda != null) {
                println("Banda encontrada: ${banda.nombre}, Ciudad: ${banda.ciudad}")
            } else {
                println("No se encontró una banda con nif: $nif")
            }
        }
        thread.start()

        // en un AppCompatActivity se pueden recoger los id de los componentes al crear la clase
        val botonGestionInstrumentos = findViewById<Button>(R.id.botonGestionInstrumentos)
        botonGestionInstrumentos.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                InstrumentosActivity::class.java
            )
            startActivity(intent)
        }
    }
}