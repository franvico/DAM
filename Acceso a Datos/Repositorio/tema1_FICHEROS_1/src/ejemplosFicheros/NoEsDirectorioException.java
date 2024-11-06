package ejemplosFicheros;

public class NoEsDirectorioException extends Exception {
	
	
	// Las excepciones pueden heredar de la clase Exception o RunTimeException
	
	// Exception --> si hereda de Exception, hay que avisar en la cabecera del médoto que puede lanzar la exception
	// RunTimeException --> si hereda de RunTimeException, no hay que avisar en la cabecera del médoto (avisar en tiempo de compilación) que puede lanzar la exception
	
	public NoEsDirectorioException() {
		super("La ruta no es un directorio");
	}

}
