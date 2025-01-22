import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a24recyclerview.R
import com.example.a24recyclerview.Titular

class AdaptadorTitulares(private val datos: Array<Titular>,
                         private val clickListener : (Titular) -> Unit) :
    RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder>() {
    // El .Adapter necesita que le especifiquemos el view holder a usar <>.

    /* Creamos el view holder*/
    class TitularesViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val lblTitulo = item.findViewById(R.id.lblTitulo) as TextView
        val lblSubtitulo = item.findViewById(R.id.lblSubtitulo) as TextView
        fun bindTitular(titular: Titular){
            lblTitulo.text = titular.titulo
            lblSubtitulo.text = titular.subtitulo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitularesViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_titular, parent, false) as LinearLayout
        return TitularesViewHolder(item)
    }
    override fun onBindViewHolder(holder: TitularesViewHolder, position: Int) {
        val titular = datos[position]
        holder.bindTitular(titular)

        holder.item.setOnClickListener{clickListener(titular)}

    }
    override fun getItemCount() = datos.size

}
