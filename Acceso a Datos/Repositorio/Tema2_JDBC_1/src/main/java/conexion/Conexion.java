package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	
	private Connection conex = null;
	
	public Connection getConex() {
		
		// si la conexión ya está creada la devuelvo directamente
		if(conex != null) {
			return conex;	
		}
		
		// si la conexión no está creada, la creo y la devuelvo
		
		String url = Properties.Properties.getConfig().getProperty("url");
		String user = Properties.Properties.getConfig().getProperty("user");
		String passwd = Properties.Properties.getConfig().getProperty("passwd");
		
		try {
			conex = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conex;
		
	}
	

}
