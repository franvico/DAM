package logica;

import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class EjemploProperties {
	
	public static void main(String[] args) {
		
		//crearFicheroConfiguracion();
		leerFicheroConfiguración();
		
	}
	
	static void crearFicheroConfiguracion() {
		
		Properties configuracion = new Properties();
		configuracion.setProperty("zout", "prueba");
		configuracion.setProperty("user", "raquel");
		configuracion.setProperty("password", "1234");
		configuracion.setProperty("server", "localhost");
		configuracion.setProperty("port", "8080");
		try {
		  configuracion.store(new FileOutputStream("confPrueba.props"), 
		                                           "Fichero de configuración");
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	}
	
	static void leerFicheroConfiguración() {
		
		Properties configuracion = new Properties();
		try {
		  configuracion.load(new FileInputStream("confPrueba.props"));
		  String usuario = configuracion.getProperty("user");
		  String password = configuracion.getProperty("password");
		  String servidor = configuracion.getProperty("server");
		  Integer puerto = Integer.valueOf(configuracion.getProperty("port"));
		
		  System.out.println(usuario + " " + password + " " + servidor + " " + puerto);
		
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace();
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	}

}
