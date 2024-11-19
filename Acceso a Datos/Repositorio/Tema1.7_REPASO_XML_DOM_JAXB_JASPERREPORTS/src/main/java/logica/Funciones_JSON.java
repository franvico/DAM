package logica;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Funciones_JSON {
	
	static String rutaXML = "biblioteca.xml";
	static String rutaXML2 = "biblioteca2.xml";
	static String rutaXML2_fomateado = "biblioteca2_formateado.xml";
	static String rutaJSON = "biblioteca.json";

	// FUNCIONALIDAD 7 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void convertir_XML_A_JSON() throws IOException {
		
		//Obtengo el contenido del XML en formato String
		String contenidoXML = new String(Files.readAllBytes(Paths.get(rutaXML)));

		// Convertir XML a JSONObject
        JSONObject jsonObject = XML.toJSONObject(contenidoXML);

        // Convertir JSONObject a cadena JSON con formato
        String jsonString = jsonObject.toString(4); // 4 es la cantidad de espacios para la indentación
        
        // Guardar el JSON en un archivo
        Files.write(Paths.get(rutaJSON), jsonString.getBytes(), StandardOpenOption.CREATE);

        // Imprimir el JSON
        System.out.println("Archivo JSON generado con éxito");

	}
	
	// FUNCIONALIDAD 8 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void convertir_JSON_A_XML() throws IOException {
		// Leer el contenido del archivo JSON
        String contenidoJSON = new String(Files.readAllBytes(Paths.get(rutaJSON)));

        // Convertir la cadena JSON a JSONObject
        JSONObject jsonObject = new JSONObject(contenidoJSON);

        // Convertir JSONObject a XML
        String xmlString = XML.toString(jsonObject);

        // Agregar la declaración XML al principio del archivo (opcional)
        xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + xmlString;

        // Guardar el XML en un archivo
        Files.write(Paths.get(rutaXML2), xmlString.getBytes(), StandardOpenOption.CREATE);

        System.out.println("Archivo XML generado exitosamente");
	}
	
	// FUNCIONALIDAD 9 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void convertir_JSON_A_XML_formateado() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		// Paso 1:Leer el contenido del archivo JSON
        String contenidoJSON = new String(Files.readAllBytes(Paths.get(rutaJSON)));

        // Paso 2: Convertir la cadena JSON a JSONObject
        JSONObject jsonObject = new JSONObject(contenidoJSON);

        // Paso 3: Convertir JSONObject a XML
        String xmlString = XML.toString(jsonObject);

        // Paso 4: Convertir la cadena XML a un Document (para usar el Transformer)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new org.xml.sax.InputSource(new java.io.StringReader(xmlString)));

        // Paso 5: Crear el Transformer para formatear el XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes"); // Habilitar indentación
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); // Espacios de indentación

        // Paso 6: Aplicar el Transformer y guardar el XML formateado en el archivo
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rutaXML2_fomateado)));
        DOMSource source = new DOMSource(document);
        transformer.transform(source, result);

        System.out.println("Archivo XML formateado generado exitosamente");
	}
	
	// FUNCIONALIDAD 11 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void añadirNuevoLibro() throws IOException {
		
		// Paso 1: Leer el archivo JSON
		JSONObject biblioteca = leerFicheroJSON();
		
		Scanner scan = new Scanner(System.in);
		
		// Paso 2: Crear el nuevo libro como un JSONObject
		System.out.println("Introduce el título del nuevo libro");
		String titulo = scan.nextLine();
		System.out.println("Introduce el autor del nuevo libro");
		String autor = scan.nextLine();
		System.out.println("Introduce el número de ejemplares disponibles para nuevo libro");
		int ejemplaresDisponibles = scan.nextInt();		
		
        JSONObject nuevoLibro = new JSONObject();
        nuevoLibro.put("titulo", titulo);
        nuevoLibro.put("autor", autor);
        nuevoLibro.put("ejemplares_disponibles", ejemplaresDisponibles);
        
        // Paso 3: Obtener el array de libros de la biblioteca
        JSONArray libros = biblioteca.getJSONArray("libro");
        
        // Paso 4: Añadir el nuevo libro al array
        libros.put(nuevoLibro);
        
        // Paso 5: Actualizar el archivo JSON con los cambios
        escribirFicheroJSON(biblioteca);
        
        System.out.println("Libro añadido con éxito");
		
	}
	
	// FUNCIONALIDAD 12 //
	//////////////////////////////////////////////////////////////////////////////////////////
    public static void eliminarLibroPorTitulo() throws IOException {
        // Paso 1: Leer el archivo JSON
        JSONObject biblioteca = leerFicheroJSON();
        
        Scanner scan = new Scanner(System.in);
        
        // Paso 2: Pedir al usuario el título del libro a eliminar
        System.out.println("Introduce el título del libro que deseas eliminar:");
        String tituloAEliminar = scan.nextLine();
        
        // Paso 3: Obtener el array de libros de la biblioteca
        JSONArray libros = biblioteca.getJSONArray("libro");
        
        // Paso 4: Buscar el libro por título y eliminarlo
        boolean libroEliminado = false;
        
        for (int i = 0; i < libros.length(); i++) {
            JSONObject libro = libros.getJSONObject(i);
            if (libro.getString("titulo").equalsIgnoreCase(tituloAEliminar)) {
                libros.remove(i);  // Eliminar el libro
                libroEliminado = true;
                break;  // Salir del bucle una vez encontrado el libro
            }
        }

        if (libroEliminado) {
            // Paso 5: Actualizar el archivo JSON con los cambios
            escribirFicheroJSON(biblioteca);
            System.out.println("El libro '" + tituloAEliminar + "' ha sido eliminado con éxito.");
        } else {
            System.out.println("No se encontró un libro con el título '" + tituloAEliminar + "'.");
        }
    }
	
	// OBTENER FICHERO JSON //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static JSONObject leerFicheroJSON() throws IOException {
		// Paso 1: Leer el contenido del archivo JSON como una cadena
        String contenidoJSON = new String(Files.readAllBytes(Paths.get(rutaJSON)));

        // Paso 2: Convertir la cadena JSON en un JSONObject
        JSONObject jsonObject = new JSONObject(contenidoJSON);

        // Paso 3: Acceder a los datos dentro del JSONObject
        JSONObject biblioteca = jsonObject.getJSONObject("biblioteca");
        
        return biblioteca;
        
//     // Leer la lista de libros
//        JSONArray libros = biblioteca.getJSONArray("libro");
//        for (int i = 0; i < libros.length(); i++) {
//            JSONObject libro = libros.getJSONObject(i);
//            System.out.println("Título: " + libro.getString("titulo"));
//            System.out.println("Ejemplares disponibles: " + libro.getInt("ejemplares_disponibles"));
//            System.out.println("Autor: " + libro.getString("autor"));
//            System.out.println();
//        }
//
//        // Leer los usuarios
//        JSONObject usuarios = biblioteca.getJSONObject("usuarios");
//        JSONArray usuariosArray = usuarios.getJSONArray("usuario");
//        for (int i = 0; i < usuariosArray.length(); i++) {
//            JSONObject usuario = usuariosArray.getJSONObject(i);
//            System.out.println("Nombre de usuario: " + usuario.getString("nombre"));
//            JSONArray librosReservados = usuario.optJSONArray("libros_reservados") != null ? usuario.getJSONArray("libros_reservados").optJSONArray("libro") : new JSONArray();
//            if (librosReservados.length() > 0) {
//                System.out.print("Libros reservados: ");
//                for (int j = 0; j < librosReservados.length(); j++) {
//                    System.out.print(librosReservados.getString(j) + " ");
//                }
//                System.out.println();
//            } else {
//                System.out.println("Libros reservados: Ninguno");
//            }
//            System.out.println();
//        }
	}
	
	// ESCRIBIR FICHERO JSON //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void escribirFicheroJSON(JSONObject biblioteca) throws JSONException, IOException {
		// Convertir el objeto biblioteca de vuelta a una cadena JSON con formato bonito
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("biblioteca", biblioteca);

        // Guardar el archivo JSON con los cambios
        Files.write(Paths.get(rutaJSON), jsonObject.toString(4).getBytes());  // '4' es el nivel de indentación para formato
    }
	
}
