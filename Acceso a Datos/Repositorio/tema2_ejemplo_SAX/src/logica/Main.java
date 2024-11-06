package logica;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import entidadesPedidos.Pedido;
import handler.HandlerPedidos;

public class Main {
	
	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		
		HandlerPedidos handler = new HandlerPedidos();
		
		saxParser.parse("pedidos2.xml", handler);
		
		for(Pedido ped : handler.getPedidos()) {
			System.out.println("Cliente: " + ped.getCliente().getNombre());
			System.out.println("Productos: " + ped.getProductos().size());
		}
		
	}

}
