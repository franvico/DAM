package Ejemplo1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
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
		
		// Recibe mensaje (Mejor usar DataInputStream)
			int msgSize = is.read();
			byte[] msgCifrado = is.readNBytes(msgSize);
			
		// Recibe clave (bytes)
			int keySize = is.read();
			byte[] keyBytes = is.readNBytes(keySize);
			
		// Recontruye clave (bytes -> objeto)
			// Este constructor acepta un array de bytes para instanciar un nuevo objeto key, a diferencia de SecretKey
				SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			
		// Descifra mensaje con la clave
			// Creo cifrador/descifrador de claves
				Cipher c = Cipher.getInstance("AES");
			// Configuro el cifrador/descifrador para descifrar
				c.init(Cipher.DECRYPT_MODE, key);
			// Descrifro el mensaje
				String msgDescifrado = new String(c.doFinal(msgCifrado));
		
		// Muestro el mensaje en la consola del server
			System.out.println(msgDescifrado);
				
		// Cierro canales de comunicación
			is.close();

		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
