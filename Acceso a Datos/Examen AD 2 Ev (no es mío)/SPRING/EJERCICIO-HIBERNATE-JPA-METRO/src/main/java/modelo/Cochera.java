package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cocheras")
public class Cochera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCochera;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "direccion")
	private String direccion;
	
	public Cochera(int codCochera, String nombre, String direccion) {
		super();
		this.codCochera = codCochera;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Cochera() {
		
	}

	public int getCodCochera() {
		return codCochera;
	}

	public void setCodCochera(int codCochera) {
		this.codCochera = codCochera;
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

	@Override
	public String toString() {
		return "Cochera [codCochera=" + codCochera + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	

}
