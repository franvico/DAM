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
	
	/**
	 * Método que dado un  nombre de fichero
	 * muestre sus propiedades. Si no existe, 
	 * lo indicará.
	 * Clase File : objeto que está asociado a
	 * una ruta del sistema de ficheros
	 */
	static void listarPropiedades(File fichero){
		
		
		
		System.out.println
		((fichero.exists())?"Existe":"No existe");
		
		//boolean ejecutable = fichero.canExecute();
		System.out.println
		((fichero.canExecute())?"Se puede ejecutar":"No se puede ejecutar");
		
		System.out.println(fichero.length());
	}
	
	//ESTE MAIN ES SOLO PARA PRUEBAS DE LOS
	//MÉTODOS:
	public static void main(String[] args) {
		
//		System.out.println("Nombre de fichero:");
		Scanner scan = new Scanner(System.in);
//		String nombreFich = scan.nextLine();
		
		//nombreFich tiene que especificar toda
		//la ruta, si no asume que es la
		//raíz del proyecto:
		
		// "C:\\Users\\Admin\\Desktop\\prueba.txt"
		//File file = new File(nombreFich);
		
		//System.out.println(nombreFich);
		
		//listarPropiedades(file);
		
//		try {
//			System.out.println(listarDirectorio_v2(file,""));
//		} catch (NoEsDirectorioException e) {
//			System.out.println("No puedo hacerlo, no es un directorio");
//			//e.printStackTrace();
//		}
//		
		//System.out.println("ADIÓSSSS");
		
		//ejemploJavaNIO();
		
//		System.out.println("Introduzca caracter a eliminar:");
//		char c = scan.nextLine().charAt(0);
//		System.out.println("Introduzca nombre archivo:");
//		String nomArchivo = scan.nextLine();
//		eliminarCaracter(new File(nomArchivo),c);
		
		try {
			eliminarUsuario(new File("usuarios"),"Anita");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Este método recibe una ruta que se supone
	 * que es un directorio
	 * @param directorio Ruta del sistema de 
	 * archivos
	 * @return Una cadena con el listado
	 * de todo el contenido, si el parámetro no
	 * es un directorio devuelve null
	 */
	static String listarDirectorio(File directorio) {
		
		if(!directorio.isDirectory())
			return null;
		
		//listar contenido:
		return "xxxxx";
	}
	
	/**
	 * Este método recibe una ruta que se supone
	 * que es un directorio
	 * @param directorio Ruta del sistema de 
	 * archivos
	 * @return Una cadena con el listado
	 * de todo el contenido
	 * @throws NoEsDirectorioException si
	 * la ruta no es un directorio
	 */
	static String listarDirectorio_v2(File directorio, String tab) throws NoEsDirectorioException{
		
		String result = "";
		
		
		
		if(!directorio.isDirectory())
			throw new NoEsDirectorioException();
		
		File[] files = directorio.listFiles();
		
		for(File  f : files) {
			if(!f.isDirectory())
				result += tab + f.getName()+"\n";
			else
				result += tab + f.getName() + "\n" 
					+ listarDirectorio_v2(f,tab + "\t") + "\n";
					
		}
		return result;
	}
	/**
	 * EJERCICIO:
	 * @param directorio La ruta de un directorio
	 * @param nomArchivo Nombre de archivo
	 * @return true si nomArchivo esta en directorio
	 * (incluyendo subcarpetas), false si no
	 */
	boolean existeArchivo(File directorio, String nomArchivo) {
		
		return false;
	}
	
	static void ejemploJavaNIO() {
		
		//clases Path, Paths, Files
		
		Path ruta = Paths.get("aaa");
		
		List<String> lineas = null; 
		try {
			lineas = Files.readAllLines(ruta);
		} catch (IOException e) {
			System.out.println("Problema al abrir el archivo");
			e.printStackTrace();
		}
		
		if(lineas != null)
			for(String lin : lineas)
				System.out.println(lin);
		
	}
	/**
	 * Método que elimina un carácter de un
	 * archivo de texto
	 * @param nomArchivo Nombre archivo
	 * @param caracter Carácter a eliminar
	 * @return null si ha habido algún problema
	 */
	static File eliminarCaracter(File nomArchivo, char caracter) {
		
		FileReader ent;
		try {
			ent = new FileReader(nomArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
			
		}
		//si el fichero no existe lo crea
		//si el fichero existe limpia o sobreescribe
		//según parámetro del constructor
		//CUIDADO CON NOMBRE AUX
		
		FileWriter salida;
		try {
			salida = new FileWriter(new File("temp"),false);
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
		int charLeido;
		try {
			while((charLeido = ent.read()) != -1) {
				
				char c = (char)charLeido;
				if(c != caracter) {
					salida.write(charLeido);
				}
					//escribir en archivo temporal
				
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
		try {
			ent.close();
			salida.close();
		} catch (IOException e) {
			
			e.printStackTrace();
			return null;
		}
		
		System.out.println(nomArchivo.delete());
		new File("temp").renameTo(nomArchivo);
		
		return nomArchivo;
		
	}
	/**
	 * Método para eliminar un usuario de un fichero
	 * @param usuarios Fichero csv con los usuarios
	 * @param nombre Nombre del usuario a eliminar
	 * @throws IOException 
	 */
	static void eliminarUsuario(File usuarios, String nombre) throws IOException{
		
		BufferedReader br = new BufferedReader(
				new FileReader(usuarios));
		BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("temp")));
		
		String linea;
		while( (linea = br.readLine()) != null) {
			
//			Este código tiene el problema de Juan y Juan2
//			if(!linea.contains(nombre))
//				bw.write(linea+"\n");
			
			String[] campos = linea.split(";");
			boolean esta = false;
			for(String c : campos)
				if (c.equals(nombre))
					esta = true;
			if(!esta)
				bw.write(linea+"\n");
				
			
		}
		
		br.close();
		bw.close();
		
		System.out.println(usuarios.delete());
		new File("temp").renameTo(usuarios);
	}
	
	
	
}
