package prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Lector {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String num = br.readLine();
		
		
		String datos = Lector.leerArchivo(Integer.parseInt(num));
		System.out.println(datos);

	}
	
	public static String leerArchivo(int longitud) throws IOException {
		
		String lectura = "";
		
		File data = new File("dataPrueba.txt");		
		BufferedReader br = new BufferedReader(new FileReader(data));
		
		String linea;
		while((linea = br.readLine()) != null) {
			lectura += linea + "\n";
		}
		
		return lectura;
	}

}
