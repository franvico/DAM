package logica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import clases_JAXB.BibliotecaVideojuegos;
import clases_JAXB.Intercambio;
import clases_JAXB.Usuario;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Funcionalidades {
	
	static String rutaXML = "videojuegos.xml";
	
	// FUNCIONES PRINCIPALES //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void intercambiado(int idJuego) throws ParserConfigurationException, SAXException, IOException {

		Document doc = leerFicheroXML_DOM();
		
		if(comprobarQueExisteElJuego(idJuego, doc)){
			obtenerInformacionDeIntercambio(idJuego, doc);			
		}
		else {
			System.out.println("El juego que buscas no existe en el registro");
		}
	}
	
	public static void eliminaUsuario(int idUsuario) throws JAXBException {
		
		BibliotecaVideojuegos bibliotecaVideojuegos = leerFicheroXML_JAXB();
		
		Usuario usuario = comprobarQueUsuarioExiste(idUsuario, bibliotecaVideojuegos);
		if(usuario != null) {
			eliminarIntercambiosDelUsuario(idUsuario, bibliotecaVideojuegos);
			eliminarUsuarioDelRegistro(usuario, bibliotecaVideojuegos);
			escribirFicheroXML_JAXB(bibliotecaVideojuegos);			
		}
		else {
			System.out.println("El usuario introducido no exite");
		}
	}

	public static void generaInforme(int idUsuario) {
		
//		String ficheroJasper = "reports\\listadoAlumnos.jasper";
//		String nombreInforme = "reports\\listadoAlumnos.pdf";
//		
//		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);
//		
//		// compilamos la plantilla:
//		
//		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
//		
//		// rellenamos el informe
//		
//		JasperPrint informe = JasperFillManager.fillReport(jasperReport, null, camposInforme);
//		
//		// exportamos a pdf
//		JasperExportManager.exportReportToPdfFile(informe, nombreInforme);
		System.out.println("FUNCIÓN NO IMPLEMENTADA TODAVÍA");
	}
	
	
	// FUNCIONES ASOCIADAS AL EJERCICIO 1 : XML DOM //
	//////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean comprobarQueExisteElJuego(int idJuego, Document doc) {
		boolean juegoExite = false;
		
		Node nodoJuegos = doc.getElementsByTagName("juegos").item(0);
		
        if (nodoJuegos.getNodeType() == Node.ELEMENT_NODE) {
            Element elementJuegos = (Element) nodoJuegos;

            NodeList nodosJuego = elementJuegos.getElementsByTagName("juego");
            
            for (int i = 0; i < nodosJuego.getLength(); i++) {
                Node nodoJuego = nodosJuego.item(i);

                if (nodoJuego.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoJuego = (Element) nodoJuego;

                    if(Integer.parseInt(elementoJuego.getElementsByTagName("ID_Juego").item(0).getTextContent()) == idJuego) {                   	
                    	juegoExite = true;
                    }
                }
            }
        }
		
		return juegoExite;
	}
	
	public static void obtenerInformacionDeIntercambio(int idJuego, Document doc) {
		Node nodoIntercambios = doc.getElementsByTagName("intercambios").item(0);
		
        if (nodoIntercambios.getNodeType() == Node.ELEMENT_NODE) {
            Element elementIntercambios = (Element) nodoIntercambios;

            NodeList nodosIntercambios = elementIntercambios.getElementsByTagName("intercambio");
            
            for (int i = 0; i < nodosIntercambios.getLength(); i++) {
                Node nodoIntercambio = nodosIntercambios.item(i);

                if (nodoIntercambio.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoIntercambio = (Element) nodoIntercambio;

                    if(Integer.parseInt(elementoIntercambio.getElementsByTagName("ID_Juego").item(0).getTextContent()) == idJuego) {                   	
                    	String id_usuario_emisor = elementoIntercambio.getElementsByTagName("ID_Usuario_Emisor").item(0).getTextContent();
                    	String id_usuario_receptor = elementoIntercambio.getElementsByTagName("ID_Usuario_Emisor").item(0).getTextContent();
                    	
                    	System.out.println("El juego con ID: " + idJuego + " ha sido intercambiado");
                    	System.out.println("El ID de usuarios que han realizado el intercambios son:");
                    	System.out.println("ID usuario emisor: " + id_usuario_emisor);
                    	System.out.println("ID usuario receptor: " + id_usuario_receptor);
                    	
                    }
                    else {
                    	System.out.println("El juego con ID: " + idJuego + " NO ha sido intercambiado");
                    }
                }
            }
        }
	}
	
	// OBTENER FICHERO XML_DOM //
	//////////////////////////////////////////////////////////////////////////////////////////		
	public static Document leerFicheroXML_DOM() throws ParserConfigurationException, SAXException, IOException {
		File archivo = new File(rutaXML);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (archivo.exists()) {
            return builder.parse(archivo);
        } else {
            // Si no existe, crear un nuevo archivo XML
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("BibliotecaVideojuegos");
            doc.appendChild(rootElement);
            return doc;
        }
	}
	
	// FUNCIONES ASOCIADAS AL EJERCICIO 2 : XML JAXB //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void eliminarUsuarioDelRegistro(Usuario usuario, BibliotecaVideojuegos biblioteca) {
		Iterator<Usuario> iterator = biblioteca.getUsuarios().iterator();
		while (iterator.hasNext()) {
		    if (iterator.next().equals(usuario)) {
		        iterator.remove();
		    }
		}
		System.out.println("Usuario eliminado con éxito\n");
	}
	
	public static Usuario comprobarQueUsuarioExiste(int idUsuario, BibliotecaVideojuegos biblioteca) {
		Usuario usuario = null;
		
		for(Usuario user : biblioteca.getUsuarios()) {
			if(user.getId_usuario() == idUsuario) {
				usuario = user;
			}
		}		
		return usuario;
	}
	
	public static void eliminarIntercambiosDelUsuario(int idUsuario, BibliotecaVideojuegos biblioteca) {
		
		ArrayList<Intercambio> intercambiosAEliminar = new ArrayList<Intercambio>();
		
		// añado los intercambios a eliminar
		for(Intercambio intercambio : biblioteca.getIntercambios())
		{
			if((intercambio.getId_usuario_emisor() ==  idUsuario) || (intercambio.getId_usuario_receptor() == idUsuario)) {
				intercambiosAEliminar.add(intercambio);
			}
		}
		
		if(intercambiosAEliminar.size() > 0) {
			// los elimino			
			Iterator<Intercambio> iterator = biblioteca.getIntercambios().iterator();
			while (iterator.hasNext()) {
			    if (intercambiosAEliminar.contains(iterator.next())) {
			        iterator.remove();
			    }
			}
			System.out.println("Los intercambios del usuario con ID: " + idUsuario + " han sido eliminados");			
			
		}
		else {
			System.out.println("No hay intercambios registrados para el usuario con ID: " + idUsuario);
		}		
	}
	
	// OBTENER FICHERO XML JAXB //
	//////////////////////////////////////////////////////////////////////////////////////////
	private static BibliotecaVideojuegos leerFicheroXML_JAXB() throws JAXBException {
		
		File file = new File(rutaXML);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);
		
		Unmarshaller u = jaxbContext.createUnmarshaller();
		
		BibliotecaVideojuegos bibliotecaVideojuegos = (BibliotecaVideojuegos) u.unmarshal(file);
		
		return  bibliotecaVideojuegos;		
	}
	
	// ESCRIBIR FICHERO XML JAXB //
	//////////////////////////////////////////////////////////////////////////////////////////
	private static void escribirFicheroXML_JAXB(BibliotecaVideojuegos bibliotecaVideojuegos) throws JAXBException {
		File file = new File(rutaXML);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(BibliotecaVideojuegos.class);		
		Marshaller marshall = jaxbContext.createMarshaller();
		
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // para que saque una salida formateada
		marshall.marshal(bibliotecaVideojuegos, file);
		
	}
}
