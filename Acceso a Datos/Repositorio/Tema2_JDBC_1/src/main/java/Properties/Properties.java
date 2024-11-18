package Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Properties {
	
	static private java.util.Properties config = null;
	
	public static java.util.Properties getConfig() {
		
		if(config != null) {
			return config;
		}
		
		config = new java.util.Properties();
		
		try {	
			// forma común de acceder a properties:
			config.load(new FileInputStream("src/main/resources/bd.properties"));
			
			// otra otra forma de cargar las properties: Sirve para cuando exportamos el proyecto Maven poder encontrar los fichero de properties
			// los ficheros que están en resources se copian en la raíz cuando se exporta el proyecto, por lo que podremos acceder a esos ficheros de esta forma (ahora está comentada)
//			InputStream inputStream = Properties.class.getClassLoader().getResourceAsStream("bd.properties");
//			config.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
	}

}
