package Pruebas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerWorker2 implements Runnable {
	Socket s;
	
	public ServerWorker2(Socket s)
	{this.s=s;}
	
	public void run()
	{
		try (InputStream is = s.getInputStream()){
		
		OutputStream os = s.getOutputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

		System.out.println("server: voy a leer");
		String mensaje = br.readLine();
		System.out.println("Server:he recibido:" + mensaje);
		Thread.sleep(3000);
		bw.write("Server:ADIOS");
		bw.newLine();
		bw.flush();
		
		os.close();
		s.close();
		}catch(IOException | InterruptedException e) {}
		
	}
}
