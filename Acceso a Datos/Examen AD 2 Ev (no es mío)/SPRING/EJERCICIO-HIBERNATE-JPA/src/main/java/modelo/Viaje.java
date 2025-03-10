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
@Table(name="viajes")
public class Viaje {
	
	@Id //para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codViaje;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne //muchos a uno
	@JoinColumn(name = "estacionOrigen", nullable = false) //JOIN
	private Estacion estacionOrigen;
	
	@ManyToOne //muchos a uno
	@JoinColumn(name = "estacionDestino", nullable = false) //JOIN
	private Estacion estacionDestino;

	public Viaje(int codEstacion, String nombre, Estacion estacion, Estacion estacion_destino) {
		super();
		this.codViaje = codEstacion;
		this.nombre = nombre;
		this.estacionOrigen = estacion;
		this.estacionDestino = estacion_destino;
	}
	
	public Viaje() {
		
	}

	public int getCodEstacion() {
		return codViaje;
	}

	public void setCodEstacion(int codEstacion) {
		this.codViaje = codEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Estacion getEstacion() {
		return estacionOrigen;
	}

	public void setEstacion(Estacion estacion) {
		this.estacionOrigen = estacion;
	}

	public Estacion getEstacion_destino() {
		return estacionDestino;
	}

	public void setEstacion_destino(Estacion estacion_destino) {
		this.estacionDestino = estacion_destino;
	}

	@Override
	public String toString() {
		return "Viaje [codEstacion=" + codViaje + ", nombre=" + nombre + ", estacion=" + estacionOrigen
				+ ", estacion_destino=" + estacionDestino + "]";
	}
	
	

}
