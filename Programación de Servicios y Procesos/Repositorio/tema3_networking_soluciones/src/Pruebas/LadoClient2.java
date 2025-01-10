package Pruebas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class LadoClient2 {

	public static void main(String[] args) throws InterruptedException {
		Socket s= new Socket();
		InetSocketAddress isa=new InetSocketAddress("localhost",9999);
		
		try {
			System.out.println("CLIENTE:INICIADO");
			s.connect(isa);
			OutputStream os=s.getOutputStream();
			InputStream is=s.getInputStream();
			
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));

			bw.write("Hola"+s.getLocalPort());
			bw.newLine();
			bw.flush();
			Thread.sleep(10000);
			String mensaje=br.readLine();
			System.out.println("CLIENTE:he recibido:"+mensaje);
		System.out.println(is.read(new byte[100]));
			System.out.println("letto");
			os.close();
			is.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
