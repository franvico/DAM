package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CLASEN CON PATRÓN DE DIDEÑO SINGLETON
public class Conexion {
	
	private static Connection conex = null;
	
	public static Connection getConex() {
		
		if(conex != null)
			return conex;
		
		String url = properties.Properties.getConfig().getProperty("url");
		String user = properties.Properties.getConfig().getProperty("user");;
		String passwd = properties.Properties.getConfig().getProperty("passwd");;
		
		try {
			conex = DriverManager.getConnection(url,user,passwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conex;
		
	}

}
