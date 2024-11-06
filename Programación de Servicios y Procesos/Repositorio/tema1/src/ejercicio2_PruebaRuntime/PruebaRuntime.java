package ejercicio2_PruebaRuntime;

import java.io.IOException;

public class PruebaRuntime {

	public static void main(String[] args) {

		Runtime rt = Runtime.getRuntime();
		String[] com = {"notepad"};
		
		try {
			Process process = rt.exec(com); // este es el homólogo del ProcessBuilder.create()
											// tanto el ProcessBuilder como el Runtime hacen llamadas del sistema (Windows, Linux) para ejecutar los comandos
											// que crean diferentes procesos (createprocess, fork), respectivamente.
			Thread.sleep(5000);
			process.destroy();
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
