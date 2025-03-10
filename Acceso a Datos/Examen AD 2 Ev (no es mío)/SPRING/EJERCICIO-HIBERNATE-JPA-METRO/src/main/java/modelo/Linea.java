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
@Table(name="lineas")
public class Linea {
	
	@Id //para indicar que es clave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) //generar automaticamente
	private int codLinea;
	
	@Column(name = "nombre")
	private String nombre;
	
	//fetch para cuando se crea una lista la cual no hay que mapear
	@OneToMany(mappedBy = "linea", fetch = FetchType.EAGER) //el nombre que tiene la instancia
	private List<Tren> trenes;

	public Linea(int codLinea, String nombre, List<Tren> trenes) {
		super();
		this.codLinea = codLinea;
		this.nombre = nombre;
		this.trenes = trenes;
	}

	public Linea() {
		
	}

	public int getCodLinea() {
		return codLinea;
	}

	public void setCodLinea(int codLinea) {
		this.codLinea = codLinea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tren> getTrenes() {
		return trenes;
	}

	public void setTrenes(List<Tren> trenes) {
		this.trenes = trenes;
	}

	@Override
	public String toString() {
		return "Linea [codLinea=" + codLinea + ", nombre=" + nombre + "]";
	}
	
	/*@Override
	public String toString() {
	    return "Linea [codLinea=" + codLinea + ", nombre=" + nombre + "]";
	}*/


}
