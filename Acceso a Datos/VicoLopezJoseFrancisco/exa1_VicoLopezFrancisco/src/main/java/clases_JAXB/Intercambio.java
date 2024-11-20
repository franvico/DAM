package clases_JAXB;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id_intercambio", "id_usuario_emisor", "id_usuario_receptor", "id_juego"})
public class Intercambio {
	
	private int id_intercambio;
	private int id_usuario_emisor;
	private int id_usuario_receptor;
	private int id_juego;
	
	public Intercambio(int id_intercambio, int id_usuario_emisor, int id_usuario_receptor, int id_juego) {
		super();
		this.id_intercambio = id_intercambio;
		this.id_usuario_emisor = id_usuario_emisor;
		this.id_usuario_receptor = id_usuario_receptor;
		this.id_juego = id_juego;
	}

	public Intercambio() {
		super();
	}

	@XmlElement(name = "ID_Intercambio")
	public int getId_intercambio() {
		return id_intercambio;
	}

	public void setId_intercambio(int id_intercambio) {
		this.id_intercambio = id_intercambio;
	}

	@XmlElement(name = "ID_Usuario_Emisor")
	public int getId_usuario_emisor() {
		return id_usuario_emisor;
	}

	public void setId_usuario_emisor(int id_usuario_emisor) {
		this.id_usuario_emisor = id_usuario_emisor;
	}

	@XmlElement(name = "ID_Usuario_Receptor")
	public int getId_usuario_receptor() {
		return id_usuario_receptor;
	}

	public void setId_usuario_receptor(int id_usuario_receptor) {
		this.id_usuario_receptor = id_usuario_receptor;
	}

	@XmlElement(name = "ID_Juego")
	public int getId_juego() {
		return id_juego;
	}

	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	
	
	
	

}
