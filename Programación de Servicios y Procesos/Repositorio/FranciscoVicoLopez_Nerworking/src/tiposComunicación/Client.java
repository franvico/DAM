package tiposComunicación;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

public class Client {

	public static void main(String[] args) {
		
		Socket s = new Socket();
		InetSocketAddress isa = new InetSocketAddress("192.168.1.59", 7777);
		
		try {
			s.connect(isa);
			
			// Canales de comunicación
			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
		// COMUNICACIÓN 1
			// envío nombre
			String nombreAlumno = "Fran Vico";
			IOUtility.escribir(bw, nombreAlumno);
			
			// recibo contestación
			String contestacion = IOUtility.leer(br);
			System.out.println(contestacion);
			
		// COMUNICACIÓN 2
			MiMensaje miMensaje = new MiMensaje(nombreAlumno, 0, false);
			
			// herramientas de envío y recepción de objetos por los canales de comunicación
			ObjectOutputStream oos = new ObjectOutputStream(os);
			ObjectInputStream ois = new ObjectInputStream(is);
			
			// envío objeto
			oos.writeObject(miMensaje);
			oos.flush();
			
			// recibo objeto
			MiMensaje miMensajeMod;
			miMensajeMod = (MiMensaje) ois.readObject();
			
			System.out.println(miMensajeMod.toString());
			
		// COMUNICACIÓN 3
			// herramientas de envío y recepción
			DataOutputStream dos = new DataOutputStream(os);
			DataInputStream dis = new DataInputStream(is);			
				
			// creo el random
			int cnum = new Random().nextInt(10) + 1;

			// envío el random
			dos.writeInt(cnum);
			dos.flush();
				
			// recibo un int
			int snum;
			snum = dis.readInt();
			System.out.println("cnum = " + cnum);
			System.out.println("snum = " + snum);
			
		// COMUNICACION 4			
			
			// array de bytes para enviar
			byte[] arrayBytesSnum = new byte[snum];
			
			for(int i = 0; i < arrayBytesSnum.length; i++) {
				arrayBytesSnum[i] = (byte) (new Random().nextInt());
			}			

			// envío los bytes
			os.write(arrayBytesSnum);
			os.flush();
			
			// array de bytes para recibir
			byte[] arrayBytesCnum = new byte[cnum];
			
			// recibo los bytes del server
			for(int i = 0; i < cnum; i++) {				
				arrayBytesCnum[i] = (byte) is.read();
			}
			
			// muestro ambos arrays
			System.out.println("Array de bytes enviada: ");
			for(int i = 0; i < arrayBytesSnum.length; i++) {
				System.out.println(arrayBytesSnum[i]);
			}
			System.out.println("Array de bytes recibida: ");
			for(int i = 0; i < arrayBytesCnum.length; i++) {
				System.out.println(arrayBytesCnum[i]);
			}
			
		// CIERRO CANALES DE COMUNICACIÓN
			os.close();
			is.close();
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
