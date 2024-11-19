package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
import org.xml.sax.SAXException;

public class Funciones_XML {
	
	static String rutaXML = "biblioteca.xml";
	
	// FUNCIONALIDAD 1 //
	//////////////////////////////////////////////////////////////////////////////////////////
	
	// El problema aquí es que encuentra todos los nodos libro porque estos no están recogidos
	// en un nodo como <listaLibros> o <libros>
	// para solucionarlo he comparado la ruta de cada <libro> encontrado y solo la he evaluado
	// si coincide con los libros que están justo dentro de <biblioteca>
	public static HashMap buscarEjemplaresLibro_XML_DOM(String tituloBuscado) throws ParserConfigurationException, SAXException, IOException {
		
		HashMap<String, Object> respuesta = new HashMap<>();
		respuesta.put("cantidad", 0);
		respuesta.put("mensaje", "El libro introducido no existe");		
		
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
                			respuesta.put("cantidad", ejemplares);
                			respuesta.put("mensaje", "Hay " + ejemplares + " disponibles para ese título");                			
                		}
                		else {
                			respuesta.put("cantidad", 0);
                			respuesta.put("mensaje", "No hay ejemplares disponibles para ese título");
                		}
                	}                        	
                }
            }
        }
        return respuesta;  
	}
	
	private static String getNodePath(Node node) {
        StringBuilder path = new StringBuilder();
        while (node != null) {
            path.insert(0, "/" + node.getNodeName());
            node = node.getParentNode();
        }
        return path.toString();
    }
	
	// FUNCIONALIDAD 2 //
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public static void mostrarUsuariosConLibrosReservados() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("\nESTOS SON LOS USUARIOS QUE TIENEN ALGÚN LIBRO RESERVADO\n");
		
		ArrayList<String> usuariosConReserva = new ArrayList<String>();
		
		Document doc = leerFicheroXML();
		
		Node nodoUsuarios = doc.getElementsByTagName("usuarios").item(0);
		
		if (nodoUsuarios.getNodeType() == Node.ELEMENT_NODE) {
			Element elementoUsuarios = (Element)nodoUsuarios;
			
			NodeList usuarios = elementoUsuarios.getElementsByTagName("usuario");
			
			for(int i = 0; i < usuarios.getLength(); i++) {
				Node nodoUsuario = usuarios.item(i);
				if (nodoUsuario.getNodeType() == Node.ELEMENT_NODE) {
					Element elementoUsuario = (Element)nodoUsuario;
					
					// si existe la etiqueta <libros_reservados> (Ricardo no tiene)
					Node nodoLibrosReservados = elementoUsuario.getElementsByTagName("libros_reservados").item(0);
					if(nodoLibrosReservados != null) {
						// y si esa etiqueta contiene alguna etiqueta <libro>
						Element elementoLibrosReservados = (Element) nodoLibrosReservados;								
						NodeList librosReservados = elementoLibrosReservados.getElementsByTagName("libro");
						if(librosReservados.getLength() > 0) { // (Jorge tiene la etiqueta pero no tiene <libro>)
							usuariosConReserva.add(elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent());							
						}						
					}					
				}
			}			
		}
		if(usuariosConReserva.size() > 0) {
			System.out.println("Hay " + usuariosConReserva.size() + " usuarios con reserva de libros:");
			for(String usuario : usuariosConReserva) {
				System.out.println(usuario);
			}
		}
	}
	
	// FUNCIONALIDAD 3 //
	//////////////////////////////////////////////////////////////////////////////////////////
	
	// con esta función trabajamos el añadir nodos y además modificar otros
	public static void añadirUnaReservaNueva() throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("¿Qué libro desea reservar?");
		String tituloBuscado = scan.nextLine();
		
		Document doc = leerFicheroXML();
		
		HashMap hayEjemplares = buscarEjemplaresLibro_XML_DOM(tituloBuscado);
		if(hayEjemplares.get("cantidad").equals(0)) {
			System.out.println(hayEjemplares.get("mensaje"));
		}
		else {
			// creo la reserva
			// busco si ya existe ese usuario y si no lo creo
			System.out.println("Escriba su nombre de usuario");
			String nombreUsuario = scan.nextLine();
			Node nodoUsuario = buscarNodoUsuarioPorNombre(nombreUsuario, doc);
			if(nodoUsuario != null) {
				añadirReservaAUsuario(nodoUsuario, tituloBuscado, doc);
			}
			else {
				Node nuevoUsuario = doc.createElement("usuario");
				Node nuevoNombre = doc.createElement("nombre");
				nuevoNombre.appendChild(doc.createTextNode(nombreUsuario));
				nuevoUsuario.appendChild(nuevoNombre);
				
				Node nodoUsuarios = doc.getElementsByTagName("usuarios").item(0);
				nodoUsuarios.appendChild(nuevoUsuario);
				añadirReservaAUsuario(nuevoUsuario, tituloBuscado, doc);				
			}
		}
		
	}
	public static Node buscarNodoUsuarioPorNombre(String nombreUsuario, Document doc) throws ParserConfigurationException, SAXException, IOException {
		
		NodeList nodosUsuario = doc.getElementsByTagName("usuario");
		Node nodoUsuarioEncontrado = null;
		
		for(int i = 0; i < nodosUsuario.getLength(); i++) {
			Node nodoUsuario = nodosUsuario.item(i);
			Element elementoUsuario = (Element) nodoUsuario;
			String nombre = elementoUsuario.getElementsByTagName("nombre").item(0).getTextContent();
			if(nombre.equals(nombreUsuario)) {
				nodoUsuarioEncontrado = nodoUsuario; 
			}
			
		}
		return nodoUsuarioEncontrado;
	}
	
	public static void añadirReservaAUsuario(Node nodoUsuario, String titulo, Document doc) throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {
		Element nuevoLibro = doc.createElement("libro");
		nuevoLibro.appendChild(doc.createTextNode(titulo));
		
		// compruebo que el nodoUsuario tiene la etiqueta <libros_reservado> y si no la creo
		// ahí le añadiré el nuevoLibro
		NodeList librosReservados = ((Element)nodoUsuario).getElementsByTagName("libros_reservados");
		if(librosReservados.getLength() == 0){
			Element nuevosLibroReservados = doc.createElement("libros_reservados");
			nuevosLibroReservados.appendChild(nuevoLibro);
			nodoUsuario.appendChild(nuevosLibroReservados);
		} // si ya extistía, se lo añado
		else {
			librosReservados.item(0).appendChild(nuevoLibro);
		}
		
		modificarEjemplaresDisponibles(titulo, doc);
		escribirFicheroXML(doc);
		System.out.println("\nRESERVA REALIZADA CON ÉXITO\n");
	}
	
	public static void modificarEjemplaresDisponibles(String tituloBuscado, Document doc) {
		NodeList nodosLibro = doc.getElementsByTagName("libro");
		
		for(int i = 0; i < nodosLibro.getLength(); i++) {
			Node libro = nodosLibro.item(i);
            if (libro.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoLibro = (Element) libro;
                if (getNodePath(elementoLibro).equals("/#document/biblioteca/libro")) {                	
                	String titulo = elementoLibro.getElementsByTagName("titulo").item(0).getTextContent();
                	if(titulo.equals(tituloBuscado)) {
                		Node nodoEjemplaresDisponibles = elementoLibro.getElementsByTagName("ejemplares_disponibles").item(0);
                		int ejemplaresDisponibles = Integer.parseInt(nodoEjemplaresDisponibles.getFirstChild().getNodeValue()) - 1;
                		// elimino el nodo <ejemplares_disponibles> del nodo libro
                		elementoLibro.removeChild(nodoEjemplaresDisponibles);
                		// creo uno nuevo para añadirle el número de ejemplares actualizado
                		Node nuevoNodoEjemplaresDisponibles = doc.createElement("ejemplares_disponibles");
                		// añado el número actualizado
                		nuevoNodoEjemplaresDisponibles.appendChild(doc.createTextNode(ejemplaresDisponibles+""));
                		// este nodo se lo añado al nodo libro
                		elementoLibro.appendChild(nuevoNodoEjemplaresDisponibles);
                	}
                }
            }
		}
	}
	
	
	// OBTENER FICHERO XML //
	//////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	// ESCRIBIR FICHERO XML //
	//////////////////////////////////////////////////////////////////////////////////////////

	private static void escribirFicheroXML(Document doc) throws FileNotFoundException, IOException, TransformerFactoryConfigurationError, TransformerException {		
		File archivo = new File(rutaXML);
		
		DOMSource source = new DOMSource(doc);	     
		StreamResult result = new StreamResult(archivo);        
		     
		Transformer transformer;
		transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		     
		transformer.transform(source, result);
	}
	
	
	// FUNCIONALIDAD 1 (Opción 2) //
	//////////////////////////////////////////////////////////////////////////////////////////
	
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
