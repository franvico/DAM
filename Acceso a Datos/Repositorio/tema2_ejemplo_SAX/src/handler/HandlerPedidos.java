package handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entidadesPedidos.Cliente;
import entidadesPedidos.Pedido;
import entidadesPedidos.Producto;


// el manejador debe heredar de la clase DefaultHandler
public class HandlerPedidos extends DefaultHandler{
	
	// tres acciones básicas:
	// - cuando arranca un elemento / nodo    		-> <xx>		-> public void startElement
	// - cuando llega un nodo texto					-> 	texto	-> public void characters
	// - cuando está terminado un elemento / nodo	-> </xx>	-> public void endElement
	
	private Cliente cli = new Cliente();
	private Pedido ped = new Pedido();
	private Producto prod = new Producto();
	
	private List<Pedido> pedidos = new LinkedList<>();
	
	boolean enNombre, enNif, enDescripcion, enPrecio, enCantidad;
	String nombre, nif, descripcion, cantidad, precio;
	
	public List<Pedido> getPedidos(){
		return pedidos;
	}

	@Override
	public void startElement (String uri, String localName, String qName, Attributes attributes) throws SAXException {
	// qName es el nodo que acaba de arrancar
	// attributes son los atributos de ese nodo
		
		if(qName.equals("nombre"))
			enNombre = true;
		else if(qName.equals("nif"))
			enNif = true;
		else if(qName.equals("descripcion"))
			enDescripcion = true;
		else if(qName.equals("precio"))
			enPrecio = true;
		else if(qName.equals("cantidad"))
			enCantidad = true;
		else if(qName.equals("cliente")) {
			cli.setId(Integer.parseInt(attributes.getValue("id")));
		}
	}
	
	@Override
    public void characters (char ch[], int start, int length) throws SAXException {

		if(enNombre) {
			nombre = new String(ch, start, length);
			enNombre = false;
			cli.setNombre(nombre);
		}
		else if(enNif) {
			nif = new String(ch, start, length);
			enNif = false;
			cli.setNif(nif);
		}
		else if(enDescripcion) {
			descripcion = new String(ch, start, length);
			enDescripcion = false;
			prod.setNombre(descripcion);
		}
		else if(enPrecio) {
			precio = new String(ch, start, length);
			enPrecio = false;
			prod.setPrecio(Double.parseDouble(precio));
		}
		else if(enNombre) {
			cantidad = new String(ch, start, length);
			enCantidad = false;
			prod.setCantidad
			(Integer.parseInt(cantidad));
		}
    }
	
	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException {
		
		if(qName.equals("cliente")) {
			ped.setCliente(cli);
			cli = new Cliente();
		}
		if(qName.equals("producto")) {
			ped.getProductos().add(prod);
			prod = new Producto();
		}
		if(qName.equals("pedido")) {
			pedidos.add(ped);
			ped = new Pedido();
		}
	}
}
