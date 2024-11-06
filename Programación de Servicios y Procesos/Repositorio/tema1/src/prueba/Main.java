package prueba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			
			
			System.out.println("Escribe un número: ");
			BufferedReader br_si = new BufferedReader(new InputStreamReader(System.in));
			String num = br_si.readLine();
			
			Process lector = new ProcessBuilder("java", "-cp", "./bin", "prueba.Lector").start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(lector.getOutputStream()));
			bw.write(num);
			bw.newLine();
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(lector.getInputStream()));
			
			
			String dataPrueba = "";
			String linea;
			while((linea = br.readLine()) != null) {
				dataPrueba += linea;
			}
			
			System.out.println(dataPrueba);
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
