package clases_JAXB;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id_usuario", "nombre"})
public class Usuario {
	
	private int id_usuario;
	private String nombre;
	
	public Usuario(int id_usuario, String nombre) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
	}

	public Usuario() {
		super();
	}
	
	@XmlElement(name = "ID_Usuario")
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
