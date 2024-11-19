package logica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
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
	
	// determinamos el tipo de colección en la que guardaremos los contactos:
	// set -> no admite duplicados - más eficiente
	// list -> admite duplicados, nos permite acceder por posición - menos eficiente
	
	// map -> asocia a cada objeto una clave - puede haber dos objetos iguales asociados a diferente clave
		// TreeMap --> las claves se guardan en un TreeSet -> implica que los objetos clave son comparables entre sí y
					   // para ello la clase de de la clave tiene que tener un criterio de orden (implementa Comparable)
		// HashMap --> las claves se guardan en un HashSet -> para ello la clase de la clave tiene que tener HashCode y equals
	
	static Map<Integer,Contacto> agenda = new TreeMap<>(); // tengo que asegurarme que la clase de la clave tiene criterio de orden (la clase implemente Comparable)
	static int id = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre de la agenda de contactos");
		String nombreAgenda = scan.nextLine();
		
		try {
			recuperarContactos(new File(nombreAgenda));
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("No se pudieron recuperar los contactos existentes");
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
				case 2 :
					System.out.println("Introduzca nombre:");
					String nom = scan.nextLine();
					for(Contacto c : recuperaContacto(nom))
						System.out.println(c);
					break;
				case 3 :
					try {
						guardarAgenda(new File(nombreAgenda));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("Ha habido un problema al guardar los contactos");
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Ha habido un problema al guardar los contactos");
						e.printStackTrace();
					}
					System.out.println("Adiós");
					break;
				default :
					System.out.println("Opción incorrecta");
			}
			
		}while(op != 3);

	}

	static void insertarContacto() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nombre:");
		String nombre = scan.nextLine();
		System.out.println("Teléfono:");
		String tel = scan.nextLine();
		System.out.println("Dirección:");
		String dir = scan.nextLine();
		
		Contacto c = new Contacto(nombre, new Datos(tel, dir));
		
		agenda.put(id++, c); // si hago un put con una clave que ya existe, se interpreta como modificación del objeto en esa clave
	}
	
	static List<Contacto> recuperaContacto(String nombre) { // devuelve una List porque puede haber más de un Contacto con el mismo nombre
		
		List<Contacto> contactos = new LinkedList<>(); // linkedList es más eficiente para añadir y eliminar objetos de la lista. Más que el ArrayList
		
		// para recorrer el TreeMap hay que sacar un set con las claves y posteriormente recorrer el Tree
		
		Set<Integer> claves = agenda.keySet();
		Iterator<Integer> it = claves.iterator();
		while(it.hasNext()) {
			Integer clave = it.next();
			if(agenda.get(clave).getNombre().equals(nombre)) {
				contactos.add(agenda.get(clave));
			}
		}
		
		// a diferencia de keySet
		// EntrySet devuelve un conjuto de Entry, que es un objeto que engloba clave y objeto
		// su ventaja es que sus objetos no se quedan separados del Map, si hago modificaciones de estos, se refleja en el Map
		
//		Set<Entry<Integer,Contacto>> entradas = agenda.entrySet();
//		
//		for(Entry<Integer,Contacto> e : entradas) {
//			if(e.getValue().getNombre().equals(nombre)) {
//				contactos.add(e.getValue());
//			}
//		}
		
		return contactos;
	}
	
	static void guardarAgenda(File f) throws FileNotFoundException, IOException {
		
		// para guardar un objeto en un fichero (serializar). La clase que nos permirte serializar un objeto es ObjectOutputStream
		// este objeto tiene que implementar Serializable!!!! (y sus objetos dentro de ese)
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		
		oos.writeObject(agenda);
		
		oos.close();
	}

	private static void recuperarContactos(File f) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		if(!f.exists()) {
			return;
		}
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));		
		Map<Integer,Contacto> a = (Map<Integer,Contacto>)ois.readObject();
		agenda = a;
		
		ois.close();
		
		// hay que actualizar la variable id
		// obtener el valor máximo de las claves para actualizar id
		Set<Integer> claves = agenda.keySet();
		int max = 0;
		for(Integer i : claves) {
			if(i>max)
				max = i;
		}
		
		id = max++;
		
	}

}
