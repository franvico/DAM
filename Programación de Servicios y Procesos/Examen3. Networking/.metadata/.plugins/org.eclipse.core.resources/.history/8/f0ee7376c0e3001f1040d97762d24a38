package ExamenNetwork2425;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IOUtility {
	
	public static void escribir(BufferedWriter bw, String mensaje) {
		try {
			bw.write(mensaje);
			bw.newLine();
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String leer(BufferedReader br) {
		String mensaje = "";
		try {
			mensaje += br.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mensaje;
	}
}
