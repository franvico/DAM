package clases_JAXB;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"titulo", "autor", "ejemplaresDisponibles"}) 
public class Libro {
	
	private String titulo;
	private String autor;
	private int ejemplaresDisponibles;
	
	public Libro(String titulo, String autor, int ejemplaresDisponibles) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}

	public Libro() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	@XmlElement(name = "ejemplares_disponibles")
	public int getEjemplaresDisponibles() {
		return ejemplaresDisponibles;
	}

	public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}
	
	

}
