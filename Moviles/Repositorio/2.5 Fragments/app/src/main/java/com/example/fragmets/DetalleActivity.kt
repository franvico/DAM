package com.example.fragmets

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        //val detalle = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as FragmentDetalle
        //intent.getStringExtra("DETALLE")?.let { (detalle as FragmentDetalle).mostrarDetalle(it) }
        val detalle = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? FragmentDetalleActivity


        if (detalle != null) {
            //(detalle as FragmentDetalle)?.mostrarDetalle(intent.getStringExtra("DETALLE").toString())
            val datos = intent.getStringExtra("DETALLE").orEmpty()
            detalle.mostrarDetalle(datos)
        } else {
            Log.e("DetalleActivity", "El fragmento FragmentDetalle no se encontró.")
        }

        //val detalle: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as FragmentDetalle
        //if (detalle != null)
        //  (detalle as FragmentDetalle).mostrarDetalle(intent.getStringExtra("DETALLE").toString())

    }
}
