package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity //clase-tabla
@Table(name="accesos") 
public class Acceso {
	
	@Id //para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar automaticamente
	private int codAcceso;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@ManyToOne //muchos a uno
	@JoinColumn(name = "codEstacion", nullable = false)
	private Estacion estacion;

	public Acceso(int codAcceso, String descripcion, Estacion estacion) {
		super();
		this.codAcceso = codAcceso;
		this.descripcion = descripcion;
		this.estacion = estacion;
	}
	
	public Acceso() {
		
	}

	public int getCodAcceso() {
		return codAcceso;
	}

	public void setCodAcceso(int codAcceso) {
		this.codAcceso = codAcceso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	@Override
	public String toString() {
		return "Acceso [codAcceso=" + codAcceso + ", descripcion=" + descripcion + ", estacion=" + estacion + "]";
	}
	
	

}
