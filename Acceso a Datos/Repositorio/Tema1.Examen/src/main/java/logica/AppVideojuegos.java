package logica;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

public class AppVideojuegos {

	public static void main(String[] args) throws JAXBException, ParserConfigurationException, SAXException, IOException {
		
		Scanner scan = new Scanner(System.in);
		
		boolean exit = false;
		while(!exit) {
			
			System.out.println("\n----APLICACIÓN DE VIDEOJUEGOS----\n");
			System.out.println("SELECCIONE UN OPCIÓN\n");
			System.out.println("1. Intercambiado (XML_DOM)");
			System.out.println("2. Eliminar usuario (XML_JAXB)");
			System.out.println("3. Generar informe (JAPER_REPORTS)");

			int opcion = scan.nextInt();
			scan.nextLine();
			
			switch(opcion) {
				case 1 :
					System.out.println("Introduzca el ID del juego para comprobar si ha sido intercambiado");
					int juegoID = scan.nextInt();
					Funcionalidades.intercambiado(juegoID);					
					break;
				case 2 :
					System.out.println("Introduzca el ID el Usuario a eliminar");
					int userID_eliminar = scan.nextInt();
					Funcionalidades.eliminaUsuario(userID_eliminar);
					break;
				case 3 :
					System.out.println("Introduzca el ID el Usuario para generar su informe");
					int userID_infome = scan.nextInt();
					Funcionalidades.generaInforme(userID_infome);
					break;
				default:
                    System.out.println("Opción no válida.");
			}
				
		}

	}	
	
	

}
