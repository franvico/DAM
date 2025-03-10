package PruebaPreExamen;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyStore.SecretKeyEntry;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Client {

	public static void main(String[] args) {
		
		try {
		// Creo la secretKey
			// 1. Creo el generador de clave
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// 2. Genero la key
			SecretKey key = keygen.generateKey();
			
		// Conecto con el ServerSocket
			Socket s = new Socket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			s.connect(isa);
			
		// Recibo la clave pública del server
			InputStream is = s.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			
			byte[] publicKeyServerBytes = new byte[dis.readInt()];
			dis.read(publicKeyServerBytes);
			
		// Creo la clave a partir de los bytes
			SecretKeySpec
			
			
			
			
			
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
