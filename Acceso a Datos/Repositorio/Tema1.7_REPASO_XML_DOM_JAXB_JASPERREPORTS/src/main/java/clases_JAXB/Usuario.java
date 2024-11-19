package clases_JAXB;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "librosReservados"})
public class Usuario {

	private String nombre;
	private ArrayList<String> librosReservados = new ArrayList<String>();
	
	public Usuario(String nombre, ArrayList<String> librosReservados) {
		this.nombre = nombre;
		this.librosReservados = librosReservados;
	}
	public Usuario() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElementWrapper(name = "libros_reservados") // con esto le indico que al crear el XML envuelva todas las etiquetas <producto> en otra llamada <libros_reservados>
	@XmlElement(name = "libro")
	public ArrayList<String> getLibrosReservados() {
		return librosReservados;
	}
	public void setLibrosReservados(ArrayList<String> librosReservados) {
		this.librosReservados = librosReservados;
	}
	
	
	
}
