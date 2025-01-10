package com.example.a252recycleviewimagenes

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a252recycleviewimagenes.Pelicula
import com.example.a252recycleviewimagenes.R

class DetallePeliculaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_pelicula)

        // Obtener los datos pasados desde el MainActivity
        val pelicula = intent.getSerializableExtra("pelicula") as Pelicula

        // Mostrar el título y la sinopsis en los TextViews
        val imagenPelicula = findViewById<ImageView>(R.id.imageView)
        val tituloPelicula = findViewById<TextView>(R.id.lblTitulo)
        val sinopsisPelicula = findViewById<TextView>(R.id.lblSinopsis)

        imagenPelicula.setImageResource(pelicula.imagenResId)
        tituloPelicula.text = pelicula.titulo
        sinopsisPelicula.text = pelicula.sinopsis
    }
}