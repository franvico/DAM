package Ejer1;
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

public class LadoServer {

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
				Thread t=new Thread(new ServerWorker(s));
				t.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
