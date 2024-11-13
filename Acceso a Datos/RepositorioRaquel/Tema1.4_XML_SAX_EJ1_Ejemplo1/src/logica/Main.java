package logica;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import handler.PedidosHandler;
import entidadesPedidos.Pedido;

public class Main {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        
        
        PedidosHandler handler = new PedidosHandler();
        
        saxParser.parse("pedidos3.xml", handler);
        
        System.out.println(handler.getPedidos().size());
        
        for(Pedido ped : handler.getPedidos()) {
        	System.out.println("Id cliente:" + ped.getCliente().getId());
        	System.out.println("Número de productos: "+ped.getListaProductos().size());
        	
        	
        }

	}

}
