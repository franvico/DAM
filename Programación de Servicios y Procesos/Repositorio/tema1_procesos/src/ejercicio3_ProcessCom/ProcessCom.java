package ejercicio3_ProcessCom;

import java.io.IOException;

public class ProcessCom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "start", "ping", "-t", "localhost");
		
		ProcessBuilder pb = new ProcessBuilder(args);
		// el args lo configuro lanzando la aplicación como "Run Configurations..." en la pestaña Arguments escribo ping 127.0.0.1
		
		try {
			Process process = pb.start();
			process.getInputStream();
			process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
