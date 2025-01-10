package ejercicio0_prueba2_DatagramSocket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmisorDatagram {

	public static void main(String[] args) {
		
		try {
			System.out.println("Creando socket Datagram");
			DatagramSocket ds = new DatagramSocket();
			
			System.out.println("Envío mensaje");
			String msg = "HOLA (enviado de Emisor)";
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket datagrama = new DatagramPacket(msg.getBytes(), msg.getBytes().length, addr, 8888);
			ds.send(datagrama);
			
			System.out.println("mensaje enviado");
			
			ds.close();
			System.out.println("cerrado y terminado");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
