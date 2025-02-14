package Ejemplo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Cliente {

	public static void main(String[] args) {		
		try {
		
		// Inicio canales de comunicación y sockets			
			Socket s = new Socket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			s.connect(isa);
			
			OutputStream os = s.getOutputStream();
			
		// Crear mensaje para enviar		
			String msg = "Mi mensaje secreto";
		
		// Crear clave (objeto)		
			// Crear generador de claves			
				KeyGenerator keygen = KeyGenerator.getInstance("AES");
			// Generar clave con el generador
				SecretKey key = keygen.generateKey();
				
		// Cifrar mensaje con la clave			
			// Crear cifrador/descifrador de claves
				Cipher c = Cipher.getInstance("AES");
			// Configurar cifrador/descifrador con la clave generada para cifrar
				c.init(Cipher.ENCRYPT_MODE, key);
			// Cifro el mensaje
				byte[] msgCifrado = c.doFinal(msg.getBytes());
			
		// Deconstruye clave (objeto -> bytes)?
				byte[] keyBytes = key.getEncoded();
				
		// Envía mensaje
				os.write(msgCifrado);
				os.flush();
				
		// Envía clave
				os.write(keyBytes);
				os.flush();
				
				
				
				
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
