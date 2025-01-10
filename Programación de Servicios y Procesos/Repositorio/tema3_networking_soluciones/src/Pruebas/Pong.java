package Pruebas;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Pong {
	public static void main(String[] args) {
		try {
			InetSocketAddress isa=new InetSocketAddress("localhost",7777);
			DatagramSocket ds=new DatagramSocket(isa);
			InetAddress addr=InetAddress.getByName("localhost");
			String msg="PONG";
			byte [] rec=new byte[4];
			String recibido="";
			int random=0;
			DatagramPacket d=new DatagramPacket(rec,rec.length);
			ds.receive(d);
			recibido=new String(rec);
			System.out.println("PONG RECIBE:"+recibido);
			random=(int)(Math.random()*10);
			
			while(!recibido.equals("FINE")&&!msg.equals("FINE")) {
				if(random==9) {msg="FINE";}
			DatagramPacket datagrama=new DatagramPacket(msg.getBytes(),msg.getBytes().length,addr,8888);
			ds.send(datagrama);
			Thread.sleep(1000);
			if(!msg.equals("FINE")) {
			    d=new DatagramPacket(rec,rec.length);
				ds.receive(d);
				recibido=new String(rec);
				System.out.println("PONG RECIBE:"+recibido);
				random=(int)(Math.random()*10);
				}
			}
			if(msg.equals("FINE")) {
			System.out.println("PONG HA PERDIDO");
			}
			else
			{System.out.println("PONG HA GANADO");}
		} catch (Exception e) {
		}
	}
}
