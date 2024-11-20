package clases_JAXB;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id_juego", "titulo", "consola", "estado"})
public class Juego {
	
	private int id_juego;
	private String titulo;
	private String consola;
	private String estado;
	
	public Juego(int id_juego, String titulo, String consola, String estado) {
		super();
		this.id_juego = id_juego;
		this.titulo = titulo;
		this.consola = consola;
		this.estado = estado;
	}

	public Juego() {
		super();
	}

	@XmlElement(name = "ID_Juego")
	public int getId_juego() {
		return id_juego;
	}

	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getConsola() {
		return consola;
	}

	public void setConsola(String consola) {
		this.consola = consola;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

}
