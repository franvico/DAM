package Ejer1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class LadoCliente {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		// crear el socket (punto de conexión entre dos programas)
		Socket s = new Socket();
		
		// crear dirección a la que se conectará el socket
		InetSocketAddress isa = new InetSocketAddress("localhost", 9999);
		
		try {
			System.out.println("CLIENTE: iniciado");
			
			// conecto el socket con la address
			s.connect(isa);
			
			// abro canales de comunicación hacia y desde el socket
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			
			// instancio las herramientas para leer y escribir en los canales de escritura y lectura
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			// espero a que se lea algo (el servidor le pedirá un nombre para el fichero que se va a crear)
			IOUtility.leer(br);

			// creo el nombre del fichero y lo envío al servidor
			String nombreFichero = scan.nextLine() + s.getLocalPort();
			IOUtility.escribir(bw, nombreFichero);
			
			// espero a que el servidor me conteste
			IOUtility.leer(br);
			
			// creo el contenido y se lo envío al servidor
			String contenido = scan.nextLine();
			IOUtility.escribir(bw, contenido);
			
			// cierro los canales de lectura y escritura
			os.close();
			is.close();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
