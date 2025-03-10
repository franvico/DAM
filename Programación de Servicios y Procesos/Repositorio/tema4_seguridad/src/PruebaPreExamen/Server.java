package PruebaPreExamen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Server {
	
	public static void main(String[] args) {
		
		try {
		// Creo par de claves RSA
			// 1. Crear el generador de claves
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			// 2. Generar par de claves
			KeyPair keypair = keygen.generateKeyPair();
			
			// Inicio el serverSocket			
			ServerSocket ss = new ServerSocket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			ss.bind(isa);
			
			while(true) {
				Socket socketCliente = ss.accept();
				
				Thread t = new Thread(new GestorDeClientes(socketCliente, keypair));
				t.start();
			}
			
			
			
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
