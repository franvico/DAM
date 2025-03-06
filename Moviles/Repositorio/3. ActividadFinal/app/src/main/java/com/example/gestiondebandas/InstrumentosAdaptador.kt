package com.example.gestiondebandas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondebandas.database.Instrumento
import org.json.JSONObject
import java.io.InputStream

class InstrumentosAdaptador(
    private var instrumentos : List<Instrumento>,
    private val clickListener : (Instrumento) -> Unit
) : RecyclerView.Adapter<InstrumentosAdaptador.InstrumentosViewHolder>(){

    class InstrumentosViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val labelTipo = item.findViewById(R.id.labelTipo) as TextView
        val labelNumeroDeSerie = item.findViewById(R.id.labelNumeroDeSerie) as TextView
        val imagenInstrumento = item.findViewById<ImageView>(R.id.imagenInstrumento) as ImageView

        fun bindIntrumento(instrumento: Instrumento, context: Context){
            labelTipo.text = instrumento.tipo
            labelNumeroDeSerie.text = instrumento.num_serie.toString()

            val tipoAImagen = obtenerCorrespondencias(context)
            val nombreImagen = tipoAImagen[instrumento.tipo]
            val resourceId = context.resources.getIdentifier(nombreImagen, "drawable", context.packageName)
            imagenInstrumento.setImageResource(resourceId)
        }

        // Cargar las correspondencias entre los tipos de instrumentos y las imágenes desde el archivo JSON
        private fun obtenerCorrespondencias(context: Context): Map<String, String> {
            val jsonString = cargarJsonDesdeRaw(context, R.raw.imagenes_instrumento)
            val jsonObject = JSONObject(jsonString)
            val correspondencias = mutableMapOf<String, String>()

            // Iterar a través de las claves (tipos de instrumentos)
            jsonObject.keys().forEach { key ->
                val imageName = jsonObject.getString(key)
                correspondencias[key] = imageName
            }
            return correspondencias
        }

        // Cargar el archivo JSON desde los recursos raw
        private fun cargarJsonDesdeRaw(context: Context, resourceId: Int): String {
            val inputStream: InputStream = context.resources.openRawResource(resourceId)
            return inputStream.bufferedReader().use { it.readText() }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentosViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.instrumentos_listitem, parent, false) as LinearLayout
        return InstrumentosViewHolder(item)
    }
    override fun onBindViewHolder(holder: InstrumentosViewHolder, position: Int) {
        val instrumento = instrumentos[position]
        holder.bindIntrumento(instrumento, holder.itemView.context)

        holder.item.setOnClickListener{clickListener(instrumento)};

    }
    override fun getItemCount() = instrumentos.size

    fun actualizarInstrumentos(instrumentos: List<Instrumento>) {
        this.instrumentos = instrumentos
        notifyDataSetChanged()  // Notificar al RecyclerView que la lista ha cambiado
    }



}