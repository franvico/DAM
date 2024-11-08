package ejercicio4_RunAndWait;



import java.io.IOException;

public class RunAndWait {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// puedo pasarle "notepad" como arg o si quiero pasar un ping sería : "ping -t 127.0.0.1" (-t es para que lo haga constantemente)
		// para lanzarla hago RunConfigurations y en la pestaña Arguments meto los comandos que le quiero pasar
		// ProcessBuilder pb = new ProcessBuilder(args);
		
		ProcessBuilder pb = new ProcessBuilder("notepad");
		
		try {
			Process p = pb.start(); // inicio el proceso hijo (abrir notepad)			
			p.waitFor(); // el proceso padre se mantiene a la espera hasta que el hijo termine
			System.out.println(p.exitValue()); // cuando el hijo termine me da el valor de retorno del hijo (0 si todo ha ido bien)
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		// forzar el cierre del proceso desde el cmd:
		// tasklist --> saca un listado de los procesos que se están ejecutando
		// taskkill /f /pid 6792 --> para matar un proceso /f = forzado, /pid = process, id 6792 = id del proceso a matar
	}

}
