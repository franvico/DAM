package ExamenNetwork2425;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servidor {
	
	public static Map<Integer, List<Tarea>> listaTareas = new HashMap<>();
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 7777);
			
			ss.bind(addr);
			
			System.out.println("Servidor corriendo en el puerto 7777, a la espera de conexiones");
			
			while(true) {
				Socket socketCliente = ss.accept();
				
				Thread t = new Thread(new GestorCliente(socketCliente));
				t.start();
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
