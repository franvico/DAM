package Ejemplo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Servidor {

	public static void main(String[] args) {
		try {
		// Inicio canales de comunicación
			ServerSocket ss = new ServerSocket();			
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			ss.bind(isa);
			
			Socket sCliente = ss.accept();
			InputStream is = sCliente.getInputStream();	
		
		// Recibe mensaje
			byte[] msgCifrado = is.readAllBytes();
			
		// Recibe clave (bytes)
			byte[] keyBytes = is.readAllBytes();
			
		// Recontruye clave (bytes -> objeto)
			// Este constructor acepta un array de bytes para instanciar un nuevo objeto key, a diferencia de SecretKey
				SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			
		// Descifra mensaje con la clave
			// Creo cifrador/descifrador de claves
				Cipher c = Cipher.getInstance("AES");
			// Configuro el cifrador/descifrador para descifrar
				//SEGUIR AQUÍ!!!!
			// Descrifor la clave
				

		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
