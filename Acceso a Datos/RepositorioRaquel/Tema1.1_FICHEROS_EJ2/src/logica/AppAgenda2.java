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
import io.ObjectOutputStreamNoHeader;

public class AppAgenda2 {

	static File ficheroAgenda;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre de la agenda:");
		String nombreAgenda = scan.nextLine();
		ficheroAgenda = new File(nombreAgenda);
		
		int op;
		do {
			System.out.println("Opción:");
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Eliminar");
			System.out.println("0. Salir");
			op = Integer.parseInt(scan.nextLine());
			switch(op) {
				case 1 : 
				try {
					insertarContacto();
				} catch (IOException e) {
					System.out.println("Problema al guardar el contacto"); 
					e.printStackTrace();
				}
					break;
				case 2:
					System.out.println("Introduzca nombre:");
					String nom = scan.nextLine();
				try {
					for(Contacto c : recuperaContacto(nom))
						System.out.println(c);
				}catch(FileNotFoundException ex) {
					System.out.println("No tiene aún fichero agenda");
					ex.printStackTrace();
				}
				catch (ClassNotFoundException | IOException e) {
					System.out.println("No se pudo recuperar");
					e.printStackTrace();
				}
					break;
				case 3:	
					System.out.println("Introduzca nombre:");
					nom = scan.nextLine();
					try {
						eliminarContacto(nom);
					}catch(FileNotFoundException e) {
						System.out.println("No existe agenda");
					}
					catch (ClassNotFoundException | IOException e) {
						System.out.println("Error al eliminar");
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
	
	static List<Contacto> recuperaContacto(String nom) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		List<Contacto> contactos = new LinkedList<>();
		
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(ficheroAgenda));
		
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

	static void insertarContacto() throws FileNotFoundException, IOException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Nombre:");
		String nombre = scan.nextLine();
		System.out.println("Dirección:");
		String dir = scan.nextLine();
		System.out.println("Teléfono:");
		String tel = scan.nextLine();
		Contacto c = new Contacto(nombre, new Datos(tel,dir));
		
		//insertar el objeto c en el fichero:
		ObjectOutputStream oos = null;
		if(!ficheroAgenda.exists()) {
			oos = 
				new ObjectOutputStream(new FileOutputStream(ficheroAgenda));
		}else {
			oos = 
				new ObjectOutputStreamNoHeader(new FileOutputStream(ficheroAgenda,true));
		}
		oos.writeObject(c);
		oos.close();
	}
	
	static void eliminarContacto(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(ficheroAgenda));
		
		ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(new File("temp")));
		
		boolean finArchivo = false;
		while(!finArchivo) {
			
			try {
				Contacto c = (Contacto)ois.readObject();
				if(!c.getNombre().equals(nombre))
					oos.writeObject(c);
					
			}catch(EOFException e) {
				finArchivo = true;
			}
		}
		
		ois.close();
		oos.close();
		
		System.out.println(ficheroAgenda.delete());
		new File("temp").renameTo(ficheroAgenda);
	}

}
