package RA5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GestorDeClientes implements Runnable{
	
	Map<String, byte[]> coleccionMensajes = new HashMap<>();
	
	Socket sCliente;
	KeyPair keypairServer;
	
	public GestorDeClientes(Socket s, KeyPair keypair) {
		this.sCliente = s;
		this.keypairServer = keypair;
	}

	@Override
	public void run() {		

		try {
		// CREO UN CIPHER
			Cipher c = Cipher.getInstance("RSA");
			// LO CONFIGURO PARA DESCIFRAR CON LA PRIVATE KEY DEL SERVIDOR
			c.init(Cipher.DECRYPT_MODE, keypairServer.getPrivate());
		
		// ABRO CANALES DE COMUNICACIÓN
			OutputStream os = sCliente.getOutputStream();
			InputStream is = sCliente.getInputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			
		// ENVÍO CLAVE PÚBLICA DEL SERVIDOR AL CLIENTE
			oos.writeObject(keypairServer.getPublic());
			
		// AHORA QUE EL CLIENTE TIENE LA CLAVE PÚBLICA DEL SERVIDOR, PUEDO RECIBIR MENSAJES SEGUROS
		// NO NECESITO LA SESSIÓN KEY DEL CLIENTE PORQUE LO QUE VENGA CIFRADA CON ELLA EL SERVIDOR NO PUEDE TENER ACCESO
			
		
			String comando;
			do {
			// ESPERO COMANDO DEL CLIENTE
				byte[] comandoCifrado = new byte[dis.readInt()];
				dis.readFully(comandoCifrado);
			// DESCIFRO EL COMANDO DEL CLIENTE
				comando = new String(c.doFinal(comandoCifrado));
				
				if(comando == "PUT") {
				// ESPERO EL ID
					byte[] idCifrado = new byte[dis.readInt()];
					dis.readFully(idCifrado);
				// DESCIFRO EL ID
					String id = new String(c.doFinal(idCifrado));
					
				// ESPERO EL MENSAJE
					byte[] msgCifrado = new byte[dis.readInt()];
					dis.readFully(msgCifrado);
				
				// GUARDO EL ID Y EL MENSAJE EN LA COLECCIÓN DE MENSAJES
					coleccionMensajes.put(id, msgCifrado);
					
				} else if(comando == "GET"){
				// ESPERO EL ID
					byte[] idCifrado = new byte[dis.readInt()];
					dis.readFully(idCifrado);
				// DESCIFRO EL ID
					String id = new String(c.doFinal(idCifrado));
				// SI EXISTE ESE ID EN LA COLECIÓN DE MENSAJES
					if(coleccionMensajes.get(id) != null) {
					// ENVÍO EL MENSAJE CORRESPONDIENTE A ESE ID
						dos.writeInt(coleccionMensajes.get(id).length);
						dos.write(coleccionMensajes.get(id));
						dos.flush();
					}
					else {
					// ENVÍO UN MENSAJE DE QUE NO EXISTE MENSAJE PARA ESE ID
						byte[] msgError = ":NOMESSAGE:".getBytes();
						dos.writeInt(msgError.length);
						dos.write(msgError);
						dos.flush();
					}
				}
				
			} while(comando  != "EXIT");
			
			// CIERRO COMUNICACIÓN CON EL CLIENTE
			oos.close();
			dos.close();
			dis.close();
			
		} catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {			
			e.printStackTrace();
		}		
		
				

	}
	

	
	
}
