package entidadesPedidos;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "nif"}) // esta etiqueta me permite, entre otras cosas, elegir el order en el que las etiquetas se crean en el XML
public class Cliente {
	
	private String nombre;
	private String nif;
	private int id; // lo tengo que anotar como atributo, usando @XmlAttribute
	
	// CONSTRUCTORES
	public Cliente(String nombre, String nif) {
		super();
		this.nombre = nombre;
		this.nif = nif;
	}
	
	public Cliente() {
		super();
	}
	
	// GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	

}
