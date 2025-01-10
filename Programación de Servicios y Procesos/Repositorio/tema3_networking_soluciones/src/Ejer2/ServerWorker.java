package Ejer2;
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
	BufferedWriter bwCliente;
	Contador num;
	
	public ServerWorker(Socket s, Contador c)
	{
		this.num=c;
		this.s=s;
	InputStream is;
	try {
		is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		
		bwCliente = new BufferedWriter(new OutputStreamWriter(os));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public void run()
	{
		for(int i=0;i<10;i++) {
		IOUtility.escribir(bwCliente,"Sigues Conectado "+num.getNumero());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}

}
