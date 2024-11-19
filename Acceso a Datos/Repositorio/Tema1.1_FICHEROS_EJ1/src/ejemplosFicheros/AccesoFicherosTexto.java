package ejemplosFicheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class AccesoFicherosTexto {

	/** Este tipo de comentarios (javadoc) permite crear documentación usando estos comentarios
	 *  con doble asterisco, a diferencia de los comentarios con asterisco único
	 */
	/**
	 * Método que dado un nombre de fichero muestre sus propiedades
	 * si no exite lo indicará.
	 */
	
	/* Clase File: objeto que está asociado a una ruta del sistema de ficheros
	 * Se encuentra en la librería java.io 
	 */
	
	// java.io: File - FileReader - FileWriter - BufferedReader - BufferedWriter - ObjectInputStream - ObjectOutputStream
	
	public static void main(String[] str) {

		Scanner scan = new Scanner(System.in);
		
		// EJERCICIO 1 ejecución
//		System.out.println("Nombre del fichero:");
//		String nombreFich = scan.nextLine();
		
//		nombreFich tiene que especificar toda la ruta dentro del sistema de archivos
//		si le doy un nombre sin la ruta completa asume que estoy en la ruta por defecto de la app, que es la raíz de mi proyecto
//		
//		File file = new File(nombreFich);
//		
		// ---------------------------
		
		// EJERCICIO 2 ejecución
//		C:\\Users\\Alumno\\Desktop\\prueba.txt
//		listarPropiedades(file);
		
		// ---------------------------
		
		// EJERCICIO 3: ejecución
//		try {
//			System.out.println(listarDirectorio_vRaquel(file, ""));
//		} catch (NoEsDirectorioException e) {
//			// TODO Auto-generated catch block
//			System.out.println("No es un directorio");
//			e.printStackTrace(); // método que me muestra toda la traza de excepción. Sirve en desarrollo pero se quita en producción
//		}
		
		// ---------------------------
		
		// EJERCICIO 4 ejecución
//		ejemploJavaNIO();
		
		// ---------------------------
		
		// EJERCICIO 5
		
		// ---------------------------
		
		// EJERCICIO 6 ejecución
//		System.out.println("Introduzca un caracter a eliminar");
//		char c = scan.nextLine().charAt(0);
//		System.out.println("Introduzca el nombre del archivo");
//		String nomArchivo = scan.nextLine();
//		
//		eliminarCaracter(new File(nomArchivo), c);

		// ---------------------------
				
		// EJERCICIO 7 ejecución
		try {
			eliminarUsuario(new File("usuarios"), "Anita");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("FIN");
	}
	
	/** EJERCICIO 2: Introducción a ficheros
	 * 
	 * @param fichero
	 */
	// no puedo usar un método static si el método depende de propiedades de la clase, en este caso no habría ese problema
	static void listarPropiedades(File fichero){
		
		System.out.println((fichero.exists()) ? "Existe" : "No existe");
		
		boolean ejecutable = (fichero.canExecute());
		System.out.println((ejecutable) ? "Se puede ejecutar" : "No se puede ejecutar");
		
		System.out.println((fichero.isDirectory()) ? "Es un directorio" : "No es un directorio");
		
	}
	
	static String result = "";
	
	/** EJERCICIO 3: Directorios y repaso de excepciones (versión Fran)
	 * 
	 * Este método recibe una ruta que se supone que es un directorio e imprime todo el directorio
	 * 
	 * @param directorio Ruta del sistema de archivos
	 * @return Una cadena con el listado de todo el contenido
	 * @throws NoEsDirectorioException si la ruta no es un directorio
	 */
	static String listarDirectorio_vFran(File directorio) throws NoEsDirectorioException{ // en la declaración del método hay que avisar que ese método puede lanzar una excepción que hereda de Exception
		
		File[] files;
		
		if(!directorio.isDirectory())
			throw new NoEsDirectorioException();		
		
		files = directorio.listFiles();
		
		for(int i = 0; i < files.length; i++) {
			result += files[i] + "\n";
			if(files[i].isDirectory()) {
				listarDirectorio_vFran(files[i]);
			}
		}
		
		return result;
	}
	
	/** EJERCICIO 3: Directorios y repaso de excepciones (versión Raquel)
	 * 
	 * Este método recibe una ruta que se supone que es un directorio e imprime todo el directorio
	 * 
	 * @param directorio Ruta del sistema de archivos
	 * @return Una cadena con el listado de todo el contenido
	 * @throws NoEsDirectorioException si la ruta no es un directorio
	 */
	static String listarDirectorio_vRaquel(File directorio, String tab) throws NoEsDirectorioException{ // en la declaración del método hay que avisar que ese método puede lanzar una excepción que hereda de Exception
		
		String result = "";
		
		if(!directorio.isDirectory())
			throw new NoEsDirectorioException(); // como es una excepción personalizada, tengo que crear su clase, la cual hereda de la clase Exception
			// si lanzo la excepción la recoge el método superior, en el cual la puedo volver a lanzar al siguiente método (throw)
			// o tratar de capturar si se producte (try/cacht)
		    // en el main tengo que tratar de capturar sí o sí, no la puedo lanzar para que la capture el método superior
		
		File[] files = directorio.listFiles();
		
		for(File f : files) {
			if(!f.isDirectory()) {
				result += tab + f.getName() + "\n";
			}
			else {
				result += tab + f.getName() + "\n"
							+ 
						tab + listarDirectorio_vRaquel(f, tab + "\t") + "\n";
			}
		}
		
		return result;
	}
	
	/** EJERCICIO 4 : Introducción a las clases de java.nio
	 * 
	 * Lee el contenido de un fichero
	 * 
	 */
	static void ejemploJavaNIO() {
		// clases: Path, Paths, Files -> ya no se llama directorio a ficheros, era un poco ambiguo y se mejoró con java.nio
		
		Path ruta = Paths.get("C:\\Users\\Alumno\\Desktop\\prueba.txt");
		
		List<String> lineas = null;
		
		try {
			lineas = Files.readAllLines(ruta);
		} catch (IOException e) {
			System.out.println("Problema al abrir el fichero");
			e.printStackTrace();
		}
		
		if(lineas != null) {
			for(String lin : lineas)
				System.out.println(lin);
		}
	}
	
	/** EJERCICIO 5
	 * 
	 * @param directorio La ruta de un directorio
	 * @param nomArchivo Nombre de un archivo
	 * @return true si nomArchivo si está directorio (incluyendo subcarpetas), false si no.
	 */
	boolean existeArchivo(File directorio, String nomArchivo) {
		
		return false;
	}
	
	/** EJERCICIO 6
	 * 
	 * Método que elimina un caracter de un archivo de texto
	 * 
	 * @param nomArchivo Nombre del archivo
	 * @param caracter Caracter a eliminar en el archivo
	 * 
	 * @return File El archivo modificado sin el caracter.
	 * 		   null si ha habido algún problema.
	 */
	static File eliminarCaracter(File nomArchivo, char caracter) {
		
		/* CLASES:
		 * FileReader -> Sirve para leer caracteres del fichero, lee caracter por caracter.
		 * FileWriter -> Sirve para escribir caracteres en un fichero de texto.
		 */
		
		/* PASOS A SEGUIR
		 * 1. Leer el archivo
		 * 2. Si el caracter leído no coincide con el que le hemos pasado, lo escribo en un archivo temporal
		 * 3. Cerrar flujos de lectura y escritura
		 * 4. Borrar el primer archivo
		 * 5. Sustituir el nombre del archivo temporal por el del archivo que hemos borrar
		 */
		
		FileReader fr;
		
		try {
			fr = new FileReader(nomArchivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		// Si el fichero exite limpia o sobreescribe el fichero según parámetro del contructor
		// algunos nombres están reservado y no se pueden usar para crear ficheros desde la aplicación
		
		FileWriter fw;
		
		try {
			fw = new FileWriter(new File("temp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		int charLeido; // usamos una variable int y no una char para poder incorporar la marca de fin de archivo
			// Mirar los espacios de memoria que usa int y en cuál se guarda el char.
		try {
			while((charLeido = fr.read()) != -1) {
				
				char c = (char)charLeido;
				if(c != caracter) {
					// escribir en el archivo temporal
					fw.write(charLeido);
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		// cerrar flujos de lectura y escritura
		
		try {
			fr.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		// borrar primer archivo
		nomArchivo.delete(); // el método delete() puede dar problemas por los permisos que tenga asociados ese fichero y también si este archivo se
		                     // usa en más clases o método.
		
		// renombramos el archivo temporal con el nombre del que acabamos de borrar
		new File("temp").renameTo(nomArchivo);
		
		return nomArchivo;
	}

	/** EJERCICIO 7
	 * 
	 * Método para eliminar un usuario de un fichero
	 * 
	 * @param usuarios Fichero csv con los usuarios
	 * @param nombre nombre del usuario a eliminar
	 * @throws IOException 
	 */
	static void eliminarUsuario(File usuarios, String nombre) throws IOException {
		
		/*CLASES:
		 * BufferedReader -> Permite leer de golpe un línea completa.
		 * BufferedWriter -> Permite escribir de golpe un línea completa.
		 */
		
		BufferedReader br = new BufferedReader(new FileReader(usuarios));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("temp")));
		
		String linea;
		while((linea = br.readLine()) != null) {
			
			// este if tiene el problema que siempre que aparezca "Juan" por ejemplo, lo borro. "Juan2" lo borrará también
//			if(!linea.contains(nombre)) {
//				bw.write(linea+"\n");
//			}
			
			String [] campos = linea.split(";");
			boolean esta = false;
			for(String c : campos) {
				if(c.equals(nombre)) {
					esta = true;
				}
			}
			if(!esta) {
				bw.write(linea + "\n");
			}			
		}
		
		br.close();
		bw.close();
		
		new File("temp").renameTo(usuarios);
	}
	
	/*CLASES:
	 * InputStream ->
	 * 	FileInputStream 
	 * OutputStream -> 
	 * 	FileOutputStream
	 * 
	 * ObjectInputStream -> leer objetos java en un fichero
	 * ObjectOutputStream -> guardar objetos java en un fichero
	 */
	
	
}
