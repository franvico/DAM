package com.example.gestiondebandas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondebandas.database.Banda
import com.example.gestiondebandas.database.BandaConMusicos

class BandasAdaptador (
    private var bandas : List<BandaConMusicos>,
    private val clickListener : (BandaConMusicos) -> Unit
) : RecyclerView.Adapter<BandasAdaptador.BandasViewHolder>(){

    class BandasViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val labelNombre = item.findViewById(R.id.labelNombre) as TextView
        val labelCiudad = item.findViewById(R.id.labelCiudad) as TextView
        fun bindBanda(banda: BandaConMusicos){
            labelNombre.text = banda.banda.nombre
            labelCiudad.text = banda.banda.ciudad
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandasViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.bandas_listitem, parent, false) as LinearLayout
        return BandasViewHolder(item)
    }
    override fun onBindViewHolder(holder: BandasViewHolder, position: Int) {
        val banda = bandas[position]
        holder.bindBanda(banda)

        holder.item.setOnClickListener{clickListener(banda)};

    }
    override fun getItemCount() = bandas.size

    fun actualizarBandas(bandas: List<BandaConMusicos>) {
        this.bandas = bandas
        notifyDataSetChanged()  // Notificar al RecyclerView que la lista ha cambiado
    }
}