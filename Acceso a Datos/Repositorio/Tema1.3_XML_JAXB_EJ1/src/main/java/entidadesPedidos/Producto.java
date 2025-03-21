package entidadesPedidos;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "precio", "cantidad"}) // esta etiqueta me permite, entre otras cosas, elegir el order en el que las etiquetas se crean en el XML
public class Producto {
	
	private String nombre; // no se corresponde con la etiqueta del XML, así que hay que mapearla
	private double precio;
	private int cantidad;
	
	public Producto(String nombre, double precio, int cantidad) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	public Producto() {
		super();
	}
	
	@XmlElement(name = "descipcion")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	

}
