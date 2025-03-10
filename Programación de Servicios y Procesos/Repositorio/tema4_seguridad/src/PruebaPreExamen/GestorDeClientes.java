package PruebaPreExamen;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class GestorDeClientes implements Runnable{
	
	private Socket socketCliente;
	private KeyPair keyPairServer;
	private Cipher cipher;

	public GestorDeClientes(Socket s, KeyPair keypair) {
		try {
			this.socketCliente = s;
			this.keyPairServer = keypair;		
			this.cipher = Cipher.getInstance("AES");
			
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run() {
		try {
		// Envío la clave pública
			// 1. Paso clave a array de bytes
			byte[] keyPubServerBytes = keyPairServer.getPublic().getEncoded();
			// 2. Envío la clave con DataOutputStream		
			OutputStream os = socketCliente.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			dos.writeInt(keyPubServerBytes.length);
			dos.write(keyPubServerBytes);
			dos.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
