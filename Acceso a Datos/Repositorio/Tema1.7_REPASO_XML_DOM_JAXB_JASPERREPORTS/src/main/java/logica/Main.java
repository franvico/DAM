package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;


public class Main {
	
	static String rutaXML = "biblioteca.xml";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException, JAXBException {		
		
		Scanner scan = new Scanner(System.in);
		
		boolean exit = false;
		while(!exit) {
			
			System.out.println("\n----APLICACIÓN DE BIBLIOTECA----\n");
			System.out.println("SELECCIONE UN OPCIÓN\n");
			System.out.println("1. Burcar si hay ejemplares de un libro dado su título (XML_DOM)");
			System.out.println("2. Mostrar número de usuarios con libros reservados (XML_DOM)");
			System.out.println("3. Añadir una reserva nueva y modificar libros disponibles(XML_DOM)");
			System.out.println("4. Comprobar que un libro no tiene más reservas que ejemplares (XML_JAXB)");
			System.out.println("5. Eliminar una reserva por libro y nombre de usuario (XML_JAXB)");
			System.out.println("6. Añadir una reserva nueva (XML_JAXB)"); // no implementada
			System.out.println("7. Pasar XML a Json (JSON)");
			System.out.println("8. Pasar Json a XML (JSON)");
			System.out.println("9. Pasar Json a XML Formateado (JSON)");
			System.out.println("10. Buscar libro por título (JSON)"); // no implementada
			System.out.println("11. Añadir nuevo libro (JSON)");
			System.out.println("12. Eliminar libro por título (JSON)");
			System.out.println("13. Generar PDF con los datos de un libro dado su título (JASPERREPORTS)"); // no implementada
			System.out.println("14. Salir");
			
			int opcion = scan.nextInt();
			scan.nextLine();
			
			switch(opcion) {
				case 1 :
					System.out.println("Introduzca el título del libro");
					String tituloBuscado = scan.nextLine();
					System.out.println(Funciones_XML.buscarEjemplaresLibro_XML_DOM(tituloBuscado).get("mensaje"));
					// Funciones_XML.buscarEjemplaresLibro_XML_DOM_forma2(tituloBuscado);
					break;
				case 2 :
					Funciones_XML.mostrarUsuariosConLibrosReservados();
					break;
				case 3 :
					Funciones_XML.añadirUnaReservaNueva();
					break;
				case 4 :
					Funciones_JAXB.comprobarQueUnLibroNoTieneMasReservasQueEjemplares();
					break;
				case 5 :
					Funciones_JAXB.eliminarReservaPorTituloYUsuario();
					break;
				case 6 :
					System.out.println("FUNCIÓN NO IMPLEMENTADA");
					break;
				case 7 :
					Funciones_JSON.convertir_XML_A_JSON();
					break;
				case 8 :
					Funciones_JSON.convertir_JSON_A_XML();
					break;
				case 9 :
					Funciones_JSON.convertir_JSON_A_XML_formateado();
					break;
				case 10 :
					System.out.println("FUNCIÓN NO IMPLEMENTADA");
					break;
				case 11 :
					Funciones_JSON.añadirNuevoLibro();
					break;
				case 12 :
					Funciones_JSON.eliminarLibroPorTitulo();
					break;
				case 13 :
					System.out.println("FUNCIÓN NO IMPLEMENTADA");
					break;
				default:
                    System.out.println("Opción no válida.");
			}
				
		}

	}

}
