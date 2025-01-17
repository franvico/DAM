package com.example.fragmets

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
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
import androidx.core.app.PendingIntentCompat.getActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import java.security.Provider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //enableEdgeToEdge()

        val frgListado = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as FragmentListadoActivity
        frgListado?.setCorreosListener {
            val frgDetalle: Fragment? = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as? FragmentDetalleActivity

            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                val i = Intent(this@MainActivity, DetalleActivity::class.java)
                i.putExtra("DETALLE", it.texto)
                startActivity(i)
            }
            else if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                (frgDetalle as FragmentDetalleActivity).mostrarDetalle(it.texto)
            }
        }

    }
}
