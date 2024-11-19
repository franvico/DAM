package logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import org.xml.sax.SAXException;


public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		
		Scanner scan = new Scanner(System.in);
		
		boolean exit = false;
		while(!exit) {
			
			System.out.println("\n----APLICACIÓN DE BIBLIOTECA----\n");
			System.out.println("SELECCIONE UN OPCIÓN\n");
			System.out.println("1. Burcar si hay ejemplares de un libro dado su título (XML_DOM)");
			System.out.println("2. Mostrar número de usuarios con libros reservados (XML_DOM)");
			System.out.println("3. Añadir una reserva nueva (XML_DOM)");
			System.out.println("4. Comprobar que un libro no tiene más reservas que ejemplares (XML_JAXB)");
			System.out.println("5. Eliminar una reserva por libro y nombre de usuario (XML_JAXB)");
			System.out.println("6. Añadir una reserva nueva (XML_JAXB)");
			System.out.println("7. Generar PDF con los datos de un libro dado su título (XML_JASPERREPORTS)");
			System.out.println("8. Salir");
			// añadir opciones (u otro proyecto) con json
			
			int opcion = scan.nextInt();
			
			switch(opcion) {
				case 1 : buscarEjemplaresLibro_XML_DOM();
				break;
			}
				
		}

	}
	public static void buscarEjemplaresLibro_XML_DOM() throws ParserConfigurationException, SAXException, IOException {
		
		Document doc = leerFicheroXML();
	}
	
	public static Document leerFicheroXML() throws ParserConfigurationException, SAXException, IOException {
		File archivo = new File("biblioteca.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (archivo.exists()) {
            return (javax.swing.text.Document) builder.parse(archivo);
        } else {
            // Si no existe, crear un nuevo archivo XML
            Document doc = (javax.swing.text.Document) builder.newDocument();
            Element rootElement = ((org.w3c.dom.Document) doc).createElement("biblioteca");
            ((Node) doc).appendChild(rootElement);
            return doc;
        }
	}

}
