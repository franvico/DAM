package EjemplosStefano;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class S01Asimetrica2 {

	public static void main(String[] args) {
		try {
			System.out.println("Creo generador de claves RSA");
			KeyPairGenerator keygen= KeyPairGenerator.getInstance("RSA");
			
			System.out.println("Genero par de claves");
			KeyPair keypair=keygen.generateKeyPair();
			
			System.out.println("Objeto Cipher con cifrado RSA");
			Cipher c=Cipher.getInstance("RSA");
			
			System.out.println("Configuro c para que encripte usando la clave privada");
			c.init(Cipher.ENCRYPT_MODE,keypair.getPrivate());
			
			String msg="Mensaje que quiero no repudiar";
			
			System.out.println("cifro el mensaje:"+msg);
			byte [] msgCifrado=c.doFinal(msg.getBytes());
			
			System.out.println("mensaje cifrado:"+new String(msgCifrado));
			
			c=Cipher.getInstance("RSA");
			System.out.println("Configuro c para decriptar usando clave publica");
			c.init(Cipher.DECRYPT_MODE,keypair.getPublic());
			
			String msgDescifrado=new String(c.doFinal(msgCifrado));
			
			System.out.println("mensaje descifrado:"+msgDescifrado);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}