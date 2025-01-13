import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Connection c = DriverManager.getConnection
				("jdbc:mysql://localhost/proyectos","root","1234");
		
		
		DatabaseMetaData dbmd = c.getMetaData();
		
		System.out.println(dbmd.getDriverName());
		System.out.println(dbmd.getURL());
		
		ResultSet tablas = dbmd.getTables("proyectos", "proyectos", null, null);
		
		while(tablas.next()) {
			
			String nombreTabla = tablas.getString("TABLE_NAME");
			ResultSet columnas = dbmd.getColumns("proyectos", "proyectos", nombreTabla, null);
			System.out.println("COLUMNAS DE LA TABLA: "+nombreTabla);
			while(columnas.next()) {
				System.out.print("Columna: "+columnas.getString(4)+" ");
				System.out.println("Tipo de dato:"+columnas.getString(5));
				
			}
		
		}
		
		
		
		

	}

}
