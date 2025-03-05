package com.example.gestiondebandas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gestiondebandas.database.Instrumento

class InstrumentosAdaptador(
    private var instrumentos : List<Instrumento>,
    private val clickListener : (Instrumento) -> Unit
) : RecyclerView.Adapter<InstrumentosAdaptador.InstrumentosViewHolder>(){

    class InstrumentosViewHolder(val item : View) : RecyclerView.ViewHolder(item) {
        val labelTipo = item.findViewById(R.id.labelTipo) as TextView
        val labelNumeroDeSerie = item.findViewById(R.id.labelNumeroDeSerie) as TextView
        fun bindIntrumento(instrumento: Instrumento){
            labelTipo.text = instrumento.tipo
            labelNumeroDeSerie.text = instrumento.num_serie.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentosViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.instrumentos_listitem, parent, false) as LinearLayout
        return InstrumentosViewHolder(item)
    }
    override fun onBindViewHolder(holder: InstrumentosViewHolder, position: Int) {
        val instrumento = instrumentos[position]
        holder.bindIntrumento(instrumento)

        holder.item.setOnClickListener{clickListener(instrumento)};

    }
    override fun getItemCount() = instrumentos.size

    fun actualizarInstrumentos(instrumentos: List<Instrumento>) {
        this.instrumentos = instrumentos
        notifyDataSetChanged()  // Notificar al RecyclerView que la lista ha cambiado
    }

}