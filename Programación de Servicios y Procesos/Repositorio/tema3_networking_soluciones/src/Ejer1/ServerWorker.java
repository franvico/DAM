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
	Socket s;
	BufferedReader brCliente;
	BufferedWriter bwCliente;
	
	public ServerWorker(Socket s)
	{this.s=s;
	InputStream is;
	try {
		is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		brCliente = new BufferedReader(new InputStreamReader(is));
		bwCliente = new BufferedWriter(new OutputStreamWriter(os));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public void run()
	{
		IOUtility.escribir(bwCliente,"Te has conectado. Indicame un nombre de fichero");
		String nombreFichero=IOUtility.leer(brCliente);
		nombreFichero="server//"+nombreFichero;
		File miFichero=new File(nombreFichero);
		if(!miFichero.exists())
		{
			try {
				miFichero.createNewFile();
				BufferedWriter bwFichero= new BufferedWriter(new FileWriter(miFichero));
				
				IOUtility.escribir(bwCliente,"muy Bien. Que quieres escribir en el fichero");
				String contenido=IOUtility.leer(brCliente);
				IOUtility.escribir(bwFichero,contenido);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
