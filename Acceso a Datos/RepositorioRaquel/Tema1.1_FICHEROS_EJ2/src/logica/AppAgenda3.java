package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;

import beans.Contacto;
import beans.Datos;
import daoImpl.ContactoDaoImpl;
import dao.ContactoDao;

public class AppAgenda3 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
//		System.out.println("Nombre de la agenda:");
//		String nombreAgenda = scan.nextLine();
		
		//LEEMOS EL NOMBRE DEL FICHERO DE UN
		//ARCHIVO DE CONFIGURACIÓN:
		Properties conf = new Properties();
		
		try {
			conf.load(new FileInputStream("configuracion.props"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		};
		
		String nombreAgenda = conf.getProperty("nomFichero");
		
		
		ContactoDao dao = new ContactoDaoImpl(new File(nombreAgenda));
		//ContactoDao dao = new ContactoDaoImpl(nombreAgenda);

		int op;
		do {
			System.out.println("Opción:");
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Modificar");
			System.out.println("0. Salir");
			op = Integer.parseInt(scan.nextLine());
			switch(op) {
				case 1 : 
					System.out.println("Nombre:");
					String nombre = scan.nextLine();
					System.out.println("Dirección:");
					String dir = scan.nextLine();
					System.out.println("Teléfono:");
					String tel = scan.nextLine();
					Contacto c = new Contacto(nombre, new Datos(tel,dir));
					try {
						dao.addContacto(c);
					} catch (IOException e) {
						e.printStackTrace();
					}
				
					break;
				case 2:
					System.out.println("Nombre:");
					nombre = scan.nextLine();
					try {
						System.out.println(dao.getContacto(nombre));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:	
					System.out.println("Nombre:");
					nombre = scan.nextLine();
					System.out.println("Dirección:");
					dir = scan.nextLine();
					System.out.println("Teléfono:");
					tel = scan.nextLine();
					c = new Contacto(nombre, new Datos(tel,dir));
					try {
						dao.updateContacto(nombre, c);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 0:
					System.out.println("Adiós");
					break;
				default:
					System.out.println("Opción incorrecta");
			}
			
			
		}while(op != 0);
		
	}

}
