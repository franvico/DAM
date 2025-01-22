import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a252recycleviewimagenes.Pelicula
import com.example.a252recycleviewimagenes.R

//Creamos el adaptador con las propiedades “datos” y un escuchador.
class AdaptadorPeliculas(private val datos: List<Pelicula>,
                         private val clickListener : (Pelicula) -> Unit) :
    RecyclerView.Adapter<AdaptadorPeliculas.PeliculasViewHolder>() {
    // El .Adapter necesita que le especifiquemos el view holder a usar <>.

    /* Creamos el view holder*/
    class PeliculasViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val imageView = item.findViewById(R.id.imageView) as ImageView
        val lblTitulo = item.findViewById(R.id.lblTitulo) as TextView
        val lblSubtitulo = item.findViewById(R.id.lblFecha) as TextView
        fun bindPelicula(pelicula: Pelicula){
            lblTitulo.text = pelicula.titulo
            lblSubtitulo.text = pelicula.fecha
            imageView.setImageResource(pelicula.imagenResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_pelicula, parent, false) as LinearLayout
        return PeliculasViewHolder(item)
    }
    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        val pelicula = datos[position]
        holder.bindPelicula(pelicula)

        holder.item.setOnClickListener{clickListener(pelicula)};

    }
    override fun getItemCount() = datos.size

}
