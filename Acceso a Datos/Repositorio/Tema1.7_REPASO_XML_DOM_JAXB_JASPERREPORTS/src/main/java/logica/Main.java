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
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Main {
	
	static String rutaXML = "biblioteca.xml";

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
			scan.nextLine();
			
			switch(opcion) {
				case 1 :
					System.out.println("Introduzca el título del libro");
					String tituloBuscado = scan.nextLine(); 
					buscarEjemplaresLibro_XML_DOM(tituloBuscado);
//					buscarEjemplaresLibro_XML_DOM_forma2(tituloBuscado);
					break;
				case 2 :
					mostrarUsuariosConLibrosReservados();
					break;
				default:
                    System.out.println("Opción no válida.");
			}
				
		}

	}
	
	// El problema aquí es que encuentra todos los nodos libro porque estos no están recogidos
	// en un nodo como <listaLibros> o <libros>
	// para solucionarlo he comparado la ruta de cada <libro> encontrado y solo la he evaluado
	// si coincide con los libros que están justo dentro de <biblioteca>
	public static void buscarEjemplaresLibro_XML_DOM(String tituloBuscado) throws ParserConfigurationException, SAXException, IOException {
		
		String respuesta = "El libro introducido no existe";
		
		Document doc = leerFicheroXML();
		
		Element nodoBiblioteca = doc.getDocumentElement();
		NodeList listaLibros = nodoBiblioteca.getElementsByTagName("libro");
        for (int i = 0; i < listaLibros.getLength(); i++) {
            Node libro = listaLibros.item(i);
            if (libro.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoLibro = (Element) libro;
                if (getNodePath(elementoLibro).equals("/#document/biblioteca/libro")) {                	
                	String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();
                	if(titulo.equals(tituloBuscado)) {
                		int ejemplares = Integer.parseInt(elementoLibro.getElementsByTagName("ejemplares_disponibles").item(0).getTextContent());
                		if(ejemplares > 0) {
                			respuesta = "Hay " + ejemplares + " disponibles para ese título";
                		}
                		else {
                			respuesta = "No hay ejemplares dispobibles para ese título";
                		}
                	}                        	
                }
            }
        }
        
        System.out.println(respuesta);
        
		
	}
	
	public static void mostrarUsuariosConLibrosReservados() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("\nESTOS SON LOS USUARIOS QUE TIENEN ALGÚN LIBRO RESERVADO\n");
		
		Document doc = leerFicheroXML();
		
		Node nodoUsuarios = doc.getElementsByTagName("usuarios").item(0);
		
		if (nodoUsuarios.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoUsuarios = (Element)nodoUsuarios;
			
			NodeList usuarios = elementoUsuarios.getElementsByTagName("usuario");
			
			for(int i = 0; i < usuarios.getLength(); i++) {
				Node nodoUsuario = usuarios.item(i);
				if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoUsuario = (Element)nodoUsuario;
					
					ArrayList<String> librosReservados = new ArrayList<String>();
					
					if((elementoUsuario.getElementsByTagName("libros_reservados").getLength() > 0) && (elementoUsuario.getElementsByTagName("libros_reservados").item(0).getFirstChild().getNodeType() != Node.ELEMENT_NODE)) {
						System.out.println(elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent());
					}
					
				}
			}
			
		}
		
		
	}
	
	public static Document leerFicheroXML() throws ParserConfigurationException, SAXException, IOException {
		File archivo = new File(rutaXML);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (archivo.exists()) {
            return builder.parse(archivo);
        } else {
            // Si no existe, crear un nuevo archivo XML
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("biblioteca");
            doc.appendChild(rootElement);
            return doc;
        }
	}
	
	private static String getNodePath(Node node) {
        StringBuilder path = new StringBuilder();
        while (node != null) {
            path.insert(0, "/" + node.getNodeName());
            node = node.getParentNode();
        }
        return path.toString();
    }
	
	
	// forma 2 de este método
	// tendría modificar el XML y que envolver los nodos <libro> en la etiqueta <listaLibros>
	public static void buscarEjemplaresLibro_XML_DOM_forma2(String tituloBuscado) throws ParserConfigurationException, SAXException, IOException {
			
		String respuesta = "El libro introducido no existe";
		
		Document doc = leerFicheroXML();
		        
		// Obtener el nodo 'listaLibros'							  // Obtener el primer nodo <listaLibros>
        Node listaLibrosNode = doc.getElementsByTagName("listaLibros").item(0);

        if (listaLibrosNode.getNodeType() == Node.ELEMENT_NODE) {
            Element listaLibrosElement = (Element) listaLibrosNode;

            // Obtener todos los nodos <libro> dentro de <listaLibros>
            NodeList listaLibros = listaLibrosElement.getElementsByTagName("libro");

            // Recorrer la lista de libros
            for (int i = 0; i < listaLibros.getLength(); i++) {
                Node libroNode = listaLibros.item(i);

                if (libroNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element libroElement = (Element) libroNode;

                    // Obtener los datos del libro
                    String titulo = libroElement.getElementsByTagName("titulo").item(0).getTextContent();
                    String autor = libroElement.getElementsByTagName("autor").item(0).getTextContent();
                    String ejemplares = libroElement.getElementsByTagName("ejemplares_disponibles").item(0).getTextContent();

                    // Imprimir la información del libro
                    System.out.println("Título: " + titulo);
                    System.out.println("Autor: " + autor);
                    System.out.println("Ejemplares disponibles: " + ejemplares);
                    System.out.println("-----");
                }
            }
        }	        
			
	}

}
