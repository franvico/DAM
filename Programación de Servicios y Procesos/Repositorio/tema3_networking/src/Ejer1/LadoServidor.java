package Ejer1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class LadoServidor {

	public static void main(String[] args) {
		
		
		try {
			
			// creo el socketServer al que se conectarán los clientes
			ServerSocket ss = new ServerSocket();
			
			// defino la dirección del ServerSocket
			InetSocketAddress isa = new InetSocketAddress("localhost", 9999);
			
			// hago la bind (asocio el serverSocket con la dirección ip y el puerto)
			ss.bind(isa);
			
			System.out.println("SERVIDOR: Servidor creado y bindado, esperando conexiones");
			
			while(true) {
				
				// espera a que un cliente se conecte y
				// crea la instancia del socket del cliente
				Socket s = ss.accept();
				
				// crea y ejecuta un hilo para cada cliente para que
				// no tengan que esperar uno detrás de otro
				Thread t = new Thread(new ServerWorker(s));
				t.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
