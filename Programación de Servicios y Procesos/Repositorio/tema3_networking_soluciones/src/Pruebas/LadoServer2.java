package Pruebas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class LadoServer2 {

	public static void main(String[] args) {
		try {
			// creo socket
			ServerSocket ss = new ServerSocket();
			// bind
			InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
			ss.bind(addr);
			System.out.println("Server: Creado y bindado el socket, voy a esperar conexiones");
			while (true) {
				Socket s = ss.accept();
				Thread t=new Thread(new ServerWorker2(s));
				t.start();
			}
			/*	
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

				System.out.println("server: voy a leer");
				String mensaje = br.readLine();
				System.out.println("Server:he recibido:" + mensaje);
				bw.write("Server:ADIOS");
				bw.newLine();
				bw.flush();
				Thread.sleep(10000);
				os.close();
				is.close();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

}
}
