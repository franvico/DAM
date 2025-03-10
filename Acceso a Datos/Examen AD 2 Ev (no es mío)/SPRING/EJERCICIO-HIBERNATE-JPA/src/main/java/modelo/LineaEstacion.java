package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity //clase-tabla
@Table(name="lineaestacion")
public class LineaEstacion {
	
	@EmbeddedId
    private LineaEstacionPK id; // Clave primaria compuesta
	
	@Column(name="orden")
	private int orden;
	
	@ManyToOne
	@JoinColumn(name = "codLinea", insertable = false, updatable = false)
	private Linea linea;
	
	@ManyToOne
	@JoinColumn(name = "codEstacion", insertable = false, updatable = false)
	private Estacion estacion;

	public LineaEstacion(LineaEstacionPK id, int orden, Linea linea, Estacion estacion) {
		super();
		this.id = id;
		this.orden = orden;
		this.linea = linea;
		this.estacion = estacion;
	}
	
	public LineaEstacion() {
		
	}

	public LineaEstacionPK getId() {
		return id;
	}

	public void setId(LineaEstacionPK id) {
		this.id = id;
	}

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	@Override
	public String toString() {
		return "LineaEstacion [id=" + id + ", orden=" + orden + ", linea=" + linea + ", estacion=" + estacion + "]";
	}
	
	/*@Override
	public String toString() {
	    return "LineaEstacion [orden=" + orden + ", linea=" + (linea != null ? linea.getNombre() : "null") +
	           ", estacion=" + (estacion != null ? estacion.getNombre() : "null") + "]";
	}*/


}
