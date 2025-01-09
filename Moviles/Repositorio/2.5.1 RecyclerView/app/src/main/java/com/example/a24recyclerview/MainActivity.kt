package com.example.a24recyclerview

import AdaptadorTitulares
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a24recyclerview.ui.theme._24RecyclerViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Código para cargar el ReyclerView
        var recView: RecyclerView = findViewById(R.id.vRecycler)
        val datosRec =
            Array(51) { i -> Titular("Titulo $i", "Subtítulo Item $i") }
        val adaptadorRec = AdaptadorTitulares(datosRec){
            Log.i("DemoRecView", "Pulsado el elemento: ${it.titulo}")
        }
        /*
        {…}  Es una lambda que representa el clickListener que configuraste en la implementación de AdaptadorTitulares.
        Esta lambda se ejecutará cada vez que un usuario haga clic en un elemento del RecyclerView.
        Dentro de la lambda, it se refiere al elemento de tipo Titular que fue clicado.
        */
        recView.setHasFixedSize(true)
        recView.layoutManager =
                //LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            GridLayoutManager(this, 3)
        recView.adapter = adaptadorRec
    }
}