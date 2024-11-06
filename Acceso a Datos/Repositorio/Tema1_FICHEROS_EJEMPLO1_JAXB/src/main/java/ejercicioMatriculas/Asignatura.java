package ejercicioMatriculas;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"precio", "nombre"})
public class Asignatura {
	
	private double precio;
	private String nombre;
	
	public Asignatura() {
		super();
	}

	public Asignatura(double precio, String nombre) {
		super();
		this.precio = precio;
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	

}
