package com.example.a252recycleviewimagenes

import AdaptadorPeliculas
import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a252recycleviewimagenes.ui.theme._252RecycleViewImagenesTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Código para cargar el ReyclerView
        var recView: RecyclerView = findViewById(R.id.vRecycler)

        val peliculas = listOf(
            Pelicula("Avengers: Infinity War","2018",R.drawable.avengers, getString(R.string.sinopsis_avengers)),
            Pelicula("Spider-Man: Homecoming","2018",R.drawable.spiderman, getString(R.string.sinopsis_spiderman)),
            Pelicula("Padmaavat","2017",R.drawable.padmaavat, getString(R.string.sinopsis_padmaavat)),
            Pelicula("Black Panther","2018",R.drawable.blackpanther, getString(R.string.sinopsis_blackpanther)),
            Pelicula("Rampage","2018",R.drawable.rampage, getString(R.string.sinopsis_rampage))
        )

        val adaptadorRec = AdaptadorPeliculas(peliculas){
            val intent = Intent(this@MainActivity, DetallePeliculaActivity::class.java)
            intent.putExtra("pelicula", it)  // Pasar el objeto Pelicula
            startActivity(intent)
        }
        /*
        {…}  Es una lambda que representa el clickListener que configuraste en la implementación de AdaptadorTitulares.
        Esta lambda se ejecutará cada vez que un usuario haga clic en un elemento del RecyclerView.
        Dentro de la lambda, it se refiere al elemento de tipo Titular que fue clicado.
        */
        recView.setHasFixedSize(true)
        recView.layoutManager =
                //LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            GridLayoutManager(this, 1)
        recView.adapter = adaptadorRec

    }
}