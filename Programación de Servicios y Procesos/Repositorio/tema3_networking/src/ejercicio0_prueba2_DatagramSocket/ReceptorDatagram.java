package ejercicio0_prueba2_DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ReceptorDatagram {

	public static void main(String[] args) {
		try {
			System.out.println("Creando socket Datagram");
			InetSocketAddress addr = new InetSocketAddress("localhost", 8888);
			DatagramSocket ds = new DatagramSocket(addr);
			
			System.out.println("Recibiendo mensaje");
			
			byte[] msg = new byte[55];
			DatagramPacket dp1 = new DatagramPacket(msg, 55);
			ds.receive(dp1);
			
			System.out.println("recibido:" + new String(msg));
			InetAddress addr2 = InetAddress.getByName("localhost");
			DatagramPacket datagrama = new DatagramPacket(msg, msg.length, addr2, 8889);
			ds.send(datagrama);
			
			System.out.println("mensaje enviado");
			
			ds.close();
			System.out.println("cerrado y terminado");
			
		} catch(IOException e) {
			
		}
	}

}
