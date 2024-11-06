package ejercicio5_SumWorker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SumManager {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// instancio el process builder con los comandos de ejecución del proceso hijo
		ProcessBuilder pb = new ProcessBuilder("java", "-cp", "./bin", "ejercicio5_SumWorker.SumWorker");
		
		// inicio el proceso hijo
		Process process = pb.start();
		
		// instancio el canal de comunicación de salida del proceso padre con el hijo (el hijo no se entera)
		OutputStream os = process.getOutputStream();
		
		// instancio la herramienta de escritura del canal de comunicación
		// existen varias herramientas de escritura/lectura con sus respectivos métodos para especificar qué datos quiero enviar
		
		// | HERRAMIENTAS DE ESCRITURA -> método para escribir	| HERRAMIENTAS DE LECTURA -> método para leer |
		// ----------------------------------------------------------------------------------------------------
		// BufferedWriter -> bw.nextLine()						| BufferedReader -> br.readLine()
		// DataOutputStream -> dos.write()						| DataInputStream -> dis.read()
		// ObjectOutputSream -> oos.write()						| ObjectInputStream -> ois.read
		// OutputStreamWriter -> osw.write() 					| OutputStreamReader ???
		
		ObjectOutputStream oos = new ObjectOutputStream(os);
		ObjectInputStream ois = null;
		
		
		DataOutputStream dos = new DataOutputStream(os);
//		dos.w;
		DataInputStream dis = null;
//		dis.re;
		
		OutputStreamWriter osw = new OutputStreamWriter(os);
//		osw.w
		
////		BufferedWriter bw=null;
//		BufferedReader br=null;
//		br.readLine();
		
//		bw.n
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.println("Introduzca un número");
//		int num = scan.nextInt();
		
		
		

	}

}
