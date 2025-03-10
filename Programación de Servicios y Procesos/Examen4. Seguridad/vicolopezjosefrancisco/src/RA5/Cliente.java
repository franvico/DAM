package RA5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cliente {
	
	static SecretKey sessionKey;
	static PublicKey publicKeyServidor;
	static Socket s;

	public static void main(String[] args) {
		
		try {
		// CREO SESSION KEY (la usaré más adelante)
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			sessionKey = keygen.generateKey();
					
		// CONECTO CON EL SERVIDOR
			s = new Socket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			s.connect(isa);
			
		// ABRO CANALES DE COMUNICACIÓN
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
		// RECIBO CLAVE PÚBLICA DEL SERVIDOR
			publicKeyServidor = (PublicKey) ois.readObject();
		
		// AHORA QUE TENGO LA CLAVE PÚBLICA DEL SERVIDOR, PUEDO ENVIARLE MENSAJES SEGUROS
		// NO TENGO QUE ENVIAR LA SESSION KEY AL SERVIDOR PORQUE NO TIENE PERMITIDO DESCIFRAR ALGUNOS MENSAJES	
		
		// ENVÍO COMANDO Y CONTENIDO AL SERVIDOR
		// MENU DE OPCIONES
			Scanner scan = new Scanner(System.in);
			boolean exit = false;
			
			while(!exit) {				
				System.out.println("Escribe uno de los siguientes comandos: PUT | GET | EXIT");
				String comando = scan.nextLine();
				
				switch(comando) {
					case "PUT" : PUT(os); 
								 break;
					case "GET" : GET(os, is); 
								 break;
					case "EXIT": System.out.println("Cerrando comunicación con el servidor");
								 exit = true;
								 break;
					
					default: System.out.println("Opción incorrecta");
				}
			}
			
			ois.close();
			
		} catch (IOException | ClassNotFoundException | NoSuchAlgorithmException  e) {			
			e.printStackTrace();
		}
	}
	
	public static void PUT(OutputStream os) {
		DataOutputStream dos = new DataOutputStream(os);
		Scanner scan = new Scanner(System.in);
		
	// PIDO ID Y MENSAJE
		System.out.println("Introduce un identificador");
		String id = scan.nextLine();
		System.out.println("Introduce un mensaje");
		String msg = scan.nextLine();
		
		try {
		// CIFRO ID Y "PUT"
			// CREO EL CIPHER
			Cipher c1 = Cipher.getInstance("RSA");
			// LO CONFIGURO PARA CIFRAR CON LA PUBLIC KEY DEL SERVIDOR
			c1.init(Cipher.ENCRYPT_MODE, publicKeyServidor);
			// CIFRO ID Y "PUT"
			byte[] idCifrado = c1.doFinal(id.getBytes());
			byte[] putCifrado = c1.doFinal("PUT".getBytes());
			
		// CIFRO MENSAJE
			// CREO EL CIPHER
			Cipher c2 = Cipher.getInstance("AES");
			// LO CONFIGURO PARA CIFRAR CON LA SESSION KEY
			c2.init(Cipher.ENCRYPT_MODE, sessionKey);
			byte[] msgCifrado = c2.doFinal(msg.getBytes());
	
		// ENVÍO "PUT"
			dos.writeInt(putCifrado.length);
			dos.write(putCifrado);
			dos.flush();
		// ENVÍO ID
			dos.writeInt(idCifrado.length);
			dos.write(idCifrado);
			dos.flush();
		// ENVÍO MENSAJE
			dos.writeInt(msgCifrado.length);
			dos.write(msgCifrado);
			dos.flush();
			
			dos.close();
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void GET(OutputStream os, InputStream is) {
		DataOutputStream dos = new DataOutputStream(os);
		DataInputStream dis = new DataInputStream(is);		
		Scanner scan = new Scanner(System.in);
		
		// PIDO ID
			System.out.println("Introduce un identificador");
			String id = scan.nextLine();
		
			try {
			// CIFRO ID Y "PUT"
				// CREO EL CIPHER
				Cipher c1 = Cipher.getInstance("RSA");
				// LO CONFIGURO PARA CIFRAR CON LA PUBLIC KEY DEL SERVIDOR
				c1.init(Cipher.ENCRYPT_MODE, publicKeyServidor);
				// CIFRO ID Y "GET"
				byte[] idCifrado = c1.doFinal(id.getBytes());
				byte[] getCifrado = c1.doFinal("GET".getBytes());
			
			// ENVÍO "GET"
				dos.writeInt(getCifrado.length);
				dos.write(getCifrado);
				dos.flush();
			// ENVÍO ID
				dos.writeInt(idCifrado.length);
				dos.write(idCifrado);
				dos.flush();
				
			// RECIBO RESPUESTA DEL SERVIDOR
				
				byte[] respuesta = new byte[dis.readInt()];
				dis.readFully(respuesta);
				
				if(new String(respuesta) == ":NOMESSAGE:") {
					System.out.println(":NOMESSAGE:");
				}
				else {
				// DESCIFRO EL MENSAJE CON LA SESSION KEY
					// CREO EL CIPHER
					Cipher c2 = Cipher.getInstance("AES");
					// LO CONFIGURO PARA DESCIFRAR CON LA SESSION KEY
					c2.init(Cipher.DECRYPT_MODE, sessionKey);
					// DESCIFRO EL MENSAJE
					byte[] msg = c2.doFinal(respuesta);
					
					String msgString = new String(msg);
					
					System.out.println("Este es el mensaje de la colección que has solicitado: " + msgString);					
				}
				
				dos.close();
				dis.close();
				
				
			} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
