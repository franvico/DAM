package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;


// Importar dependencia de JSON de Maven Repository

public class Main {

	public static void main(String[] args) throws IOException {

		JSONArray notas = leerFicheroJSON(new File("notas.json"));
		
		for(int i = 0; i < notas.length(); i++) {
			JSONObject alumno = notas.getJSONObject(i);
			
			String nombre = alumno.getString("nombre");
			System.out.println("Alumno: " + nombre);
			
			JSONArray notasAlumno = alumno.getJSONArray("notas");
			for(int j = 0; j < notasAlumno.length(); j++) {
				JSONObject nota = notasAlumno.getJSONObject(j);
				System.out.println(nota.getString("materia") + " : " + nota.getInt("nota"));
			}
		}

	}
	
	public static JSONArray leerFicheroJSON(File f) throws IOException{
		
		String cadena = "";
		
		// para evitar el error que nos da de que no encuentra el fichero
		InputStream input = Main.class.getClassLoader().getResourceAsStream("notas.json");
		
		BufferedReader ent = new BufferedReader(new InputStreamReader(input));
		
		String linea;
		while(((linea = ent.readLine()) != null)) {
			cadena += linea;
		}
		
		ent.close();
		
		JSONArray notas = new JSONArray(cadena);
		
		return notas;
		
	}

}
