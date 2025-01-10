package Pruebas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Ping {

	public static void main(String[] args) {
		try {
			InetSocketAddress isa=new InetSocketAddress("localhost",8888);
			DatagramSocket ds=new DatagramSocket(isa);
			InetAddress addr=InetAddress.getByName("localhost");
			String msg="PING";
			byte [] rec=new byte[4];
			String recibido="";
			int random=0;
			while(!recibido.equals("FINE")&&!msg.equals("FINE")) {
				if(random==9) {msg="FINE";}
			DatagramPacket datagrama=new DatagramPacket(msg.getBytes(),msg.getBytes().length,addr,7777);
			ds.send(datagrama);
			Thread.sleep(1000);
			if(!msg.equals("FINE")) {
			DatagramPacket d=new DatagramPacket(rec,rec.length);
			ds.receive(d);
			recibido=new String(rec);
			System.out.println("PING RECIBE:"+recibido);
			random=(int)(Math.random()*10);
			}
			}
			if(msg.equals("FINE")) {
				System.out.println("PING HA PERDIDO");
				}
				else
				{System.out.println("PING HA GANADO");}
		} catch (Exception e) {
		}
	}

}
