package Ejer1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerWorker implements Runnable {
	
	public Socket s;
	public BufferedReader brCliente;
	public BufferedWriter bwCliente;
	
	public ServerWorker(Socket s) {
		this.s = s;
		
		InputStream is;
		OutputStream os;
		try {
			
			is = s.getInputStream();
			os = s.getOutputStream();

			this.brCliente = new BufferedReader(new InputStreamReader(is));
			this.bwCliente = new BufferedWriter(new OutputStreamWriter(os));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		// envío primer mensaje al cliente tras la conexión
		IOUtility.escribir(bwCliente, "SERVER: te has conectado, indícame el nombre de fichero");
		
		// espero a la contestación del cliente
		String nombreFichero = IOUtility.leer(brCliente);
		nombreFichero = "server//" + nombreFichero;
		
		// creo el fichero con el nombre recibido del cliente
		File miFichero = new File(nombreFichero);
		
		// si no exite el fichero lo crea
		if(!miFichero.exists()) {			
			try {
				miFichero.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// envío mensaje al cliente para que me devuelva el contenido
		// que quiere escribir en el fichero
		IOUtility.escribir(bwCliente, "SERVER: fichero creado, escribe el contenido");
		
		// espero la respuesta del cliente con el contenido
		String contenido = IOUtility.leer(brCliente);
		
		try {
			
			// creo un canal de escritura para el fichero
			BufferedWriter bwFichero = new BufferedWriter(new FileWriter(miFichero));
			
			// escribo el contenido en el fichero
			IOUtility.escribir(bwFichero, contenido);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
