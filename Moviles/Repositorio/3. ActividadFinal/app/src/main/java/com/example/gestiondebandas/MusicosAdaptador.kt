package com.example.gestiondebandas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondebandas.database.MusicoConInstrumento

class MusicosAdaptador (
    private var musicos : List<MusicoConInstrumento>,
    private val clickListener : (MusicoConInstrumento) -> Unit
) : RecyclerView.Adapter<MusicosAdaptador.MusicosViewHolder>(){

    class MusicosViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val labelNombre = item.findViewById(R.id.labelNombre) as TextView
        val labelDNI = item.findViewById(R.id.labelDNI) as TextView
        fun bindMusico(musico: MusicoConInstrumento){
            labelNombre.text = musico.musico.nombre
            labelDNI.text = musico.musico.dni.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicosViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.musicos_listitem, parent, false) as LinearLayout
        return MusicosViewHolder(item)
    }
    override fun onBindViewHolder(holder: MusicosViewHolder, position: Int) {
        val musico = musicos[position]
        holder.bindMusico(musico)

        holder.item.setOnClickListener{clickListener(musico)};

    }
    override fun getItemCount() = musicos.size

    fun actualizarMusicos(musicos: List<MusicoConInstrumento>) {
        this.musicos = musicos
        notifyDataSetChanged()  // Notificar al RecyclerView que la lista ha cambiado
    }
}