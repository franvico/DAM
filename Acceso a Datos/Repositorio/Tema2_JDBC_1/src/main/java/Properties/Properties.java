package Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {
	
	static private java.util.Properties config = null;
	
	public static java.util.Properties getConfig() {
		
		if(config != null) {
			return config;
		}
		
		config = new java.util.Properties();
		
		try {
			config.load(new FileInputStream("src/main/resources/bd.properties"));
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
