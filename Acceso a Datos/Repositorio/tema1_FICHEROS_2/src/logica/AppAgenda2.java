package logica;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import beans.Contacto;
import beans.Datos;

public class AppAgenda2 {
	
	// mejora de rendimiento de la AppAgenda1.
	// leemos el fichero cada vez que consultemos y no guardamos todos los contactos en una variable para mejorar la memoria ram
	
	static File ficheroAgenda;

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre de la agenda de contactos");
		String nombreAgenda = scan.nextLine();
		ficheroAgenda = new File(nombreAgenda);

		int op;
		
		do {
			System.out.println("Opción:");
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Salir");
			
			op = Integer.parseInt(scan.nextLine());
			
			switch(op) {
				case 1 : 
					try {
						insertarContacto();
					} catch (IOException e) {
						System.out.println("No se ha podido insertar contacto");
						e.printStackTrace();
					}
					break;
				case 2 :
					System.out.println("Introduzca nombre:");
					String nom = scan.nextLine();
					try {
						for(Contacto c : recuperaContacto(nom))
							System.out.println(c);
					} catch (ClassNotFoundException | IOException e) {
						System.out.println("No se ha podido recuperar");
						e.printStackTrace();
					}
					break;
				case 3 :
					
					System.out.println("Adiós");
					break;
				default :
					System.out.println("Opción incorrecta");
			}
		}while(op != 3);
	}

	static void insertarContacto() throws FileNotFoundException, IOException {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("Nombre:");
			String nombre = scan.nextLine();
			System.out.println("Teléfono:");
			String tel = scan.nextLine();
			System.out.println("Dirección:");
			String dir = scan.nextLine();
			
			Contacto c = new Contacto(nombre, new Datos(tel, dir));
			
			// insertamos el objeto c directamente en el fichero:
			// POSIBILIDADES DE ERROR AL LEER VARIAS VECES EL FICHERO
			// 1 . No existe el fichero -> lo crea con una cabecera con información sobre el fichero
					// con el write crea un contacto
					// otro write = otro contacto
			// 2. El fichero existe -> si instanciamos el ObjectOutputStream, introduce otra cabecera (PROBLEMA!!)
					// cuando lo abramos para leer (ois) habrá un problema al leer tantas cabecera.
			// SOLUCIÓN
				// si el fichero ya existía hay que hacer un cambio para que no vuelva a escribir la cabecera
				// (lo tengo hecho en ContantoDAOImpl.java) --> coger de ahí
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroAgenda, true));
			
			oos.writeObject(c);
			oos.close();
		}
	
		private static List<Contacto> recuperaContacto(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
			
			List<Contacto> contactos = new LinkedList<>();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroAgenda));
			
			boolean finArchivo = false;
			
			while(!finArchivo) {
				
				try {
					Contacto c = (Contacto)ois.readObject();
					if(c.getNombre().equals(nom))
						contactos.add(c);
				}catch(EOFException e) {
					finArchivo = true;
				}
			}
			ois.close();
			
			return contactos;
	}
}
