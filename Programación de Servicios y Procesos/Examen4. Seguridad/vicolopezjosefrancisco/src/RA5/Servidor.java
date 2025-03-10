package RA5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class Servidor {
	
	/* CRITERIOS QUE HE SEGUIDO PARA ELEGIR LA CORRECTA CRIPTOGRAFÍA
	 * 
	 * El servidor no debe ser capaz de visualizar toda la información que le llega del cliente, por lo tanto:
	 * La información que SÍ NECESITE CONOCER el servidor (comando e id) será cifrada en el cliente mediante criptografía RSA con la clave pública del servidor
	 * 	(enviada previamente desde el servidor al cliente como objeto, ya que es una clave pública y no necesita ser cifrada)
	 * 	Tanto el comando como el id serán enviados de forma segura porque sólo podrán ser descifrados con la clave privada del servidor.
	 * La información que NO DEBA CONOCER el servidor (mensaje) será cifrada mediante criptografía AES en el cliente con una session key generada por él y que sólo tendrá él
	 * 	Dado que el servidor no tendrá acceso a esta información, la almacenará cifrada como byte[] en un Map (asociando el id (que sí conoce) al mensaje)
	 * Al hacer una petición GET al servidor y recibir una respuesta. Si la respuesta no es ":NOMESASGE:", se descrifrará mediante criptografía AES
	 * con la session key que solo tiene el cliente
	 */

	public static void main(String[] args) {

		try {
			// GENERO PAR DE CLAVES DEL SERVIDOR
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			KeyPair keypair = keygen.generateKeyPair();
			
			// CREO CONEXIÓN PARA CLIENTES
			ServerSocket ss = new ServerSocket();
			InetSocketAddress isa = new InetSocketAddress("localhost", 7777);
			ss.bind(isa);
			
			while(true) {
				Socket s = ss.accept();
				Thread t = new Thread(new GestorDeClientes(s, keypair));
				t.start();
			}
			
			
			
		} catch (IOException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		

	}

}
