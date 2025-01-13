package logic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Func {
	
	public static void main(String[] args) {
		mostrarEmpleadosProyecto(3);
		
	}
	
	public static void mostrarEmpleadosProyecto(int id) {
		
		String basedatos = "proyectos";
	    String host = "localhost";
	    String port = "3306";
	    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;
	    String user = "root";
	    String pwd = "1234";

	    Connection c = null;
		try {
			c = DriverManager.getConnection(urlConnection, user, pwd);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	    
		try {
			CallableStatement s 
			= c.prepareCall("{call MostrarEmpleadosProyecto(?)}");
			
			s.setInt(1,id);
			
				
			s.execute();
			
			ResultSet rs = s.getResultSet();
			
			while(rs.next()) {
				System.out.print(rs.getString("DNI_Empleado")+"\t");
				System.out.println(rs.getString("Nombre_Empleado"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
