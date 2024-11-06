package logica;

import java.io.File;

import entidadesPedidos.Cliente;
import entidadesPedidos.Pedido;
import entidadesPedidos.Pedidos;
import entidadesPedidos.Producto;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

public class Main2 {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub
		
		Cliente cliente = new Cliente();
		cliente.setId(23);
		cliente.setNif("1224");
		cliente.setNombre("Julia");
		
		Producto p1 = new Producto();
		p1.setCantidad(3);
		p1.setNombre("ordenador");
		p1.setPrecio(1000);
		
		Producto p2 = new Producto();
		p2.setCantidad(4);
		p2.setNombre("pantalla");
		p2.setPrecio(10000);
		
		Pedido ped = new Pedido();
		ped.setCliente(cliente);
		ped.getProductos().add(p1);
		ped.getProductos().add(p2);
		
		Pedidos peds = new Pedidos();
		peds.getPedidos().add(ped);
		
		escribirFicheroXML(peds, new File("pedidos2.xml"));

	}
	
	
	private static void escribirFicheroXML(Pedidos pedidos, File fichero) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Pedidos.class);		
		Marshaller marshall = jaxbContext.createMarshaller();
		
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // para que saque una salida formateada
		marshall.marshal(pedidos, fichero);
		
	}

}
