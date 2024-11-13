package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import beans.Contacto;
import beans.Datos;

public class AppAgenda1 {

	static Map<Integer,Contacto> agenda 
		= new TreeMap<>();
	static int id = 0;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre de la agenda:");
		String nombreAgenda = scan.nextLine();
		
		try {
			recuperarContactos(new File(nombreAgenda));
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("No se pudieron recuperar contactos existentes");
			e.printStackTrace();
		}
				
		int op;
		do {
			System.out.println("Opción:");
			System.out.println("1. Insertar");
			System.out.println("2. Consultar");
			System.out.println("3. Salir");
			op = Integer.parseInt(scan.nextLine());
			switch(op) {
				case 1 : 
					insertarContacto();
					break;
				case 2:
					System.out.println("Introduzca nombre:");
					String nom = scan.nextLine();
					for(Contacto c : recuperaContacto(nom))
						System.out.println(c);
					break;
				case 3:
				try {
					guardarAgenda(new File(nombreAgenda));
				} catch (FileNotFoundException e) {
					System.out.println("Ha habido un problema al guardar los contactos");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Ha habido un problema al guardar los contactos");
					e.printStackTrace();
				}
					System.out.println("Adiós");
					break;
				default:
					System.out.println("Opción incorrecta");
			}
			
			
		}while(op != 3);
		
	}
	
	static void recuperarContactos(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		if(!f.exists())
			return;
		
		ObjectInputStream ois = 
				new ObjectInputStream(new FileInputStream(f));
		Map<Integer,Contacto> a = (Map<Integer,Contacto>)ois.readObject();
		agenda = a;
		
		ois.close();
		
		//hay que obtener el máximo de las claves 
		//para actualizar id:
		Set<Integer> claves = agenda.keySet();
		int max = 0;
		for(Integer i : claves)
			if(i>max)
				max = i;
		
		id = max++;
	}

	static void insertarContacto() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Nombre:");
		String nombre = scan.nextLine();
		System.out.println("Dirección:");
		String dir = scan.nextLine();
		System.out.println("Teléfono:");
		String tel = scan.nextLine();
		Contacto c = new Contacto(nombre, new Datos(tel,dir));
		agenda.put(id++,c);
	}
	
	static List<Contacto> recuperaContacto(String nombre) {
		
		List<Contacto> contactos = new LinkedList<>();
		
//		Set<Integer> claves = agenda.keySet();
//		Iterator<Integer> it = claves.iterator();
//		while(it.hasNext()) {
//			Integer clave = it.next();
//			if(agenda.get(clave).getNombre().equals(nombre))
//				contactos.add(agenda.get(clave));
//		}
		
		Set<Entry<Integer,Contacto>> entradas = 
				agenda.entrySet();
		
		for(Entry<Integer,Contacto> e : entradas) {
			if(e.getValue().getNombre().equals(nombre))
				contactos.add(e.getValue());
			
		}
		
		return contactos;
		
	}
	
	static void guardarAgenda(File f) throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(f));
		
		oos.writeObject(agenda);
		
		oos.close();
	}

}
