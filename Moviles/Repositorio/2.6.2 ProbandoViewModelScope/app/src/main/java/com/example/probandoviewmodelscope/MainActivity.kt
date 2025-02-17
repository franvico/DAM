package com.example.probandoviewmodelscope
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
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
import com.example.probandoviewmodelscope.ui.theme.ProbandoViewModelScopeTheme
class MainActivity : AppCompatActivity() {
    // Kotlin delegate (by viewModels())
    // Inicializa el viewModel
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val bHilo = findViewById<Button>(R.id.btnHilo)
        val bInmediato = findViewById<Button>(R.id.btnInmediato)
        val texto = findViewById<TextView>(R.id.lblResultado)
        bHilo.setOnClickListener {
            // Observa los cambios en el LiveData
            viewModel.text.observe(this) { newText ->
                texto.text = newText
            }
            viewModel.performBackgroundTask()
        }
        bInmediato.setOnClickListener {
            texto.text = "Tarea inmediata finalizada"
        }
    }
}