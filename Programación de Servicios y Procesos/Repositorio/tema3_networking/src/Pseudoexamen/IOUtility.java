package Pseudoexamen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class IOUtility {
	
	public static void escribir(BufferedWriter bw, String msg) {
		
		try {
			bw.write(msg);
			bw.newLine();
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static String leer(BufferedReader br) {
		String mensaje = "";
		try {
			mensaje = br.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mensaje;
	}

}
