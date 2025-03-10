package modelo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //clase-tabla
@Table(name="estaciones")
public class Estacion {
	
	@Id //para indicar que es clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generar automaticamente
	private int codEstacion;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="numAccesos")
	private int numAccesos;
	
	@Column(name="numLineas")
	private int numLineas;
	
	@Column(name="numViajesDestino")
	private int numViajesDestino;
	
	@Column(name="numViajesOrigen")
	private int numViajesOrigen;
	
	@OneToMany(mappedBy = "estacion", fetch = FetchType.EAGER)
	private List<LineaEstacion> lineaEstaciones;
	
	@OneToMany(mappedBy="estacion", fetch = FetchType.EAGER)
	private List<Acceso> accesos;
	
	@OneToMany(mappedBy="estacionOrigen", fetch = FetchType.EAGER)
	private List<Viaje> viajesOrigen;
	
	@OneToMany(mappedBy="estacionDestino", fetch = FetchType.EAGER)
	private List<Viaje> viajesDestino;

	public Estacion(int idcodEstacion, String nombre, String direccion, int numAccesos, int numLineas,
			int numViajesDestino, int numViajesOrigen, List<LineaEstacion> lineaEstaciones, List<Acceso> accesos,
			List<Viaje> viajes, List<Viaje> viajes_2) {
		super();
		this.codEstacion = idcodEstacion;
		this.nombre = nombre;
		this.direccion = direccion;
		this.numAccesos = numAccesos;
		this.numLineas = numLineas;
		this.numViajesDestino = numViajesDestino;
		this.numViajesOrigen = numViajesOrigen;
		this.lineaEstaciones = lineaEstaciones;
		this.accesos = accesos;
		this.viajesOrigen = viajes;
		this.viajesDestino = viajes_2;
	}
	
	public Estacion() {
		
	}

	public int getIdcodEstacion() {
		return codEstacion;
	}

	public void setIdcodEstacion(int idcodEstacion) {
		this.codEstacion = idcodEstacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getNumAccesos() {
		return numAccesos;
	}

	public void setNumAccesos(int numAccesos) {
		this.numAccesos = numAccesos;
	}

	public int getNumLineas() {
		return numLineas;
	}

	public void setNumLineas(int numLineas) {
		this.numLineas = numLineas;
	}

	public int getNumViajesDestino() {
		return numViajesDestino;
	}

	public void setNumViajesDestino(int numViajesDestino) {
		this.numViajesDestino = numViajesDestino;
	}

	public int getNumViajesOrigen() {
		return numViajesOrigen;
	}

	public void setNumViajesOrigen(int numViajesOrigen) {
		this.numViajesOrigen = numViajesOrigen;
	}

	public List<LineaEstacion> getLineaEstaciones() {
		return lineaEstaciones;
	}

	public void setLineaEstaciones(List<LineaEstacion> lineaEstaciones) {
		this.lineaEstaciones = lineaEstaciones;
	}

	public List<Acceso> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Acceso> accesos) {
		this.accesos = accesos;
	}

	public List<Viaje> getViajes() {
		return viajesOrigen;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajesOrigen = viajes;
	}

	public List<Viaje> getViajes_2() {
		return viajesDestino;
	}

	public void setViajes_2(List<Viaje> viajes_2) {
		this.viajesDestino = viajes_2;
	}

	/*@Override
	public String toString() {
		return "Estacion [codEstacion=" + codEstacion + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", numAccesos=" + numAccesos + ", numLineas=" + numLineas + ", numViajesDestino=" + numViajesDestino
				+ ", numViajesOrigen=" + numViajesOrigen + ", lineaEstaciones=" + lineaEstaciones + ", accesos="
				+ accesos + ", viajesOrigen=" + viajesOrigen + ", viajesDestino=" + viajesDestino + "]";
	}*/
	
	@Override
	public String toString() {
	    return "Estacion [codEstacion=" + codEstacion + ", nombre=" + nombre + ", direccion=" + direccion
	            + ", numAccesos=" + numAccesos + ", numLineas=" + numLineas + ", numViajesDestino=" + numViajesDestino
	            + ", numViajesOrigen=" + numViajesOrigen + "]";
	}


}
