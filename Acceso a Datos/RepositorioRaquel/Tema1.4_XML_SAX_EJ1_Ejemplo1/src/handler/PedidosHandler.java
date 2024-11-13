package handler;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entidadesPedidos.Cliente;
import entidadesPedidos.Pedido;
import entidadesPedidos.Producto;

public class PedidosHandler extends DefaultHandler{
	
	private List<Pedido> pedidos = new LinkedList<>();
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	private Pedido ped = new Pedido();
	private Cliente cli = new Cliente();
	private Producto prod = new Producto();
	private boolean nombre, nif, descripcion, precio, cantidad;
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
		if(qName.equals("cliente")) {
			
			if(attributes != null) {
				cli.setId(Integer.parseInt(attributes.getValue("id")));
			}
		}
		
		if (qName.equals("nombre")) {
            nombre = true;
        }
        if (qName.equals("nif")) {
            nif = true;
        }
        if (qName.equals("descripcion")) {
            descripcion = true;
        }
        if (qName.equals("precio")) {
            precio = true;
        }
        if (qName.equals("cantidad")) {
            cantidad = true;
        }
        
 
		
    }
 
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
    	if (nombre) {
            cli.setNombre(new String(ch, start, length));
            nombre = false;
        }
    	if (nif) {
            cli.setNif(new String(ch, start, length));
            nif = false;
        }
    	if (descripcion) {
            prod.setNombre(new String(ch, start, length));
            descripcion = false;
        }
    	if (precio) {
            prod.setPrecio(Double.parseDouble(new String(ch, start, length)));
            precio = false;
        }
    	if (cantidad) {
            prod.setCantidad(Integer.parseInt(new String(ch, start, length)));
            cantidad = false;
        }
    	
    }
 
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
    	if (qName.equals("producto")) {
            ped.getListaProductos().add(prod);
            prod = new Producto();
        }
    	if (qName.equals("cliente")) {
            ped.setCliente(cli);
            cli = new Cliente();
        }
    	if (qName.equals("pedido")) {
            pedidos.add(ped);
            ped = new Pedido();
        }
    }
 

}
