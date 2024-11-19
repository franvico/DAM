package clases_JAXB;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Biblioteca {

	private ArrayList<Libro> libros = new ArrayList<Libro>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Biblioteca(ArrayList<Libro> libros, ArrayList<Usuario> usuarios) {
		super();
		this.libros = libros;
		this.usuarios = usuarios;
	}

	public Biblioteca() {
		super(); 
	}
	@XmlElement(name="libro")
	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public void setLibros(ArrayList<Libro> libros) {
		this.libros = libros;
	}
	@XmlElementWrapper(name = "usuarios") // con esto le indico que al crear el XML envuelva todas las etiquetas <producto> en otra llamada <usuarios>
	@XmlElement(name="usuario")
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
