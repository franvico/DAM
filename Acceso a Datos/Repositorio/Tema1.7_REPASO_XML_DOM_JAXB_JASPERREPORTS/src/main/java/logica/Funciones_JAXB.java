package logica;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

import clases_JAXB.Biblioteca;
import clases_JAXB.Libro;
import clases_JAXB.Usuario;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Funciones_JAXB {

	static String rutaXML = "biblioteca.xml";

	// LO PRIMERO ES CREAR LAS CLASES ASOCIADAS AL XML Y AÑADIR SUS ETIQUETAS XML
	
	// FUNCIONALIDAD 4 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void comprobarQueUnLibroNoTieneMasReservasQueEjemplares() throws JAXBException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el título del libro");
		String tituloBuscado = scan.nextLine();
		
		boolean libroExiste = false;
		int ejemplaresDisponibles = 0;
		int ejemplaresReservados = 0;
		
		Biblioteca biblioteca = leerFicheroXML();
		
		for(Libro libro : biblioteca.getLibros()) {
			if(libro.getTitulo().equals(tituloBuscado)) {
				libroExiste = true;
				ejemplaresDisponibles = libro.getEjemplaresDisponibles();
			}			
		}
		if(libroExiste) {
			for(Usuario usuario : biblioteca.getUsuarios()) {
				for(String libro : usuario.getLibrosReservados()) {
					if(libro.equals(tituloBuscado)) {
						ejemplaresReservados++;
					}
				}
			}
			if(ejemplaresReservados > ejemplaresDisponibles) {
				System.out.println("Se han reservado más ejemplares de los que hay disponibles:");
				System.out.println("Reservados: " + ejemplaresReservados);
				System.out.println("Disponibles: " + ejemplaresDisponibles);
			}
			else if(ejemplaresReservados < ejemplaresDisponibles) {
				System.out.println("Aún quedan ejeplares disponibles:");
				System.out.println("Reservados: " + ejemplaresReservados);
				System.out.println("Disponibles: " + ejemplaresDisponibles);
			}
			else {
				System.out.println("Se han reservado todos los ejemplares disponibles:");
				System.out.println("Reservados: " + ejemplaresReservados);
				System.out.println("Disponibles: " + ejemplaresDisponibles);
			}
		}
		else {
			System.out.println("El libro consultado no existe");
		}		
	}
	
	// FUNCIONALIDAD 5 //
	//////////////////////////////////////////////////////////////////////////////////////////
	public static void eliminarReservaPorTituloYUsuario() throws JAXBException {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Introduzca el nombre de usuario");
		String nombreUsuario = scan.nextLine();
		
		Biblioteca biblioteca = leerFicheroXML();
		
		Usuario usuario = comprobarQueUsuarioExiste(nombreUsuario, biblioteca);
		if(usuario != null) {
			System.out.println("Introduzca el título del libro");
			String tituloBuscado = scan.nextLine();
			if(comprobarQueUsuarioTieneLibroReservado(tituloBuscado, usuario)) {
				eliminarLibrosReservados(tituloBuscado, usuario);
				escribirFicheroXML(biblioteca);
				System.out.println("RESERVA ELIMINADA CON ÉXITO\n");
			}
			else {
				System.out.println("Este usuario no tiene ninguna reserva de ese libro");
			}
		}
		else {
			System.out.println("El usuario introducido no exite");
		}
	}
	
	public static Usuario comprobarQueUsuarioExiste(String nombreUsuario, Biblioteca biblioteca) {
		Usuario usuario = null;
		
		for(Usuario user : biblioteca.getUsuarios()) {
			if(user.getNombre().equals(nombreUsuario)) {
				usuario = user;
			}
		}		
		return usuario;
	}
	
	public static boolean comprobarQueUsuarioTieneLibroReservado(String tituloBuscado, Usuario usuario) {
		for(String libro : usuario.getLibrosReservados()) {
			if(libro.equals(tituloBuscado)) {
				return true;
			}
		}
		return false;
	}
	
	public static void eliminarLibrosReservados(String tituloBuscado, Usuario usuario) {
		Iterator<String> iterator = usuario.getLibrosReservados().iterator();
		while (iterator.hasNext()) {
		    if (iterator.next().equals(tituloBuscado)) {
		        iterator.remove();
		    }
		}
	}
	
	// OBTENER FICHERO XML //
	//////////////////////////////////////////////////////////////////////////////////////////
	private static Biblioteca leerFicheroXML() throws JAXBException {
		
		File file = new File(rutaXML);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Biblioteca.class);
		
		Unmarshaller u = jaxbContext.createUnmarshaller();
		
		Biblioteca biblioteca = (Biblioteca) u.unmarshal(file);
		
		return  biblioteca;		
	}
	
	// ESCRIBIR FICHERO XML //
	//////////////////////////////////////////////////////////////////////////////////////////
	private static void escribirFicheroXML(Biblioteca biblioteca) throws JAXBException {
		File file = new File(rutaXML);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Biblioteca.class);		
		Marshaller marshall = jaxbContext.createMarshaller();
		
		marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // para que saque una salida formateada
		marshall.marshal(biblioteca, file);
		
	}
}
