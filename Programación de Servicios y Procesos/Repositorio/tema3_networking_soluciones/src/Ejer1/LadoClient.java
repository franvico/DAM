package Ejer1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class LadoClient {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Socket s= new Socket();
		InetSocketAddress isa=new InetSocketAddress("localhost",9999);
		try {
			System.out.println("CLIENTE:INICIADO");
			s.connect(isa);
			OutputStream os=s.getOutputStream();
			InputStream is=s.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));

			
			IOUtility.leer(br);
			String nombreFichero=scan.nextLine();
			nombreFichero=nombreFichero+s.getLocalPort();
			IOUtility.escribir(bw,nombreFichero);
			IOUtility.leer(br);
			String contenido=scan.nextLine();
			IOUtility.escribir(bw,contenido);
			
			os.close();
			is.close();
			
			// byte[] miArray = new byte[n];
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
