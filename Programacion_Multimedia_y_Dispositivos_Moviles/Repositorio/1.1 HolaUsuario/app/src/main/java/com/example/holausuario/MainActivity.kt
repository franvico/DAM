package com.example.holausuario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.holausuario.ui.theme.HolaUsuarioTheme

class MainActivity : ComponentActivity() {
    private lateinit var txtNombre : EditText
    private lateinit var btnAceptar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        txtNombre = findViewById(R.id.txtNombre)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnAceptar.setOnClickListener{
            // creación del intent que conexiona ambos diseños (buscar qué es un intent)
            var intent = Intent(this@MainActivity,SaludoActivity::class.java)
            intent.putExtra("NOMBRE", txtNombre.text.toString())
            startActivity(intent)
        }


    }
}
