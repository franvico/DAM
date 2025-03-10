package funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Empleado;
import clases.Piso;
import conexion.Conexion;

public class Funciones {
	
	//INSERTAR
	public static void insertarEmpleado(Empleado e) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		Statement sentencia;
		try {
			sentencia = con.createStatement();
			String SQL = "INSERT INTO empleados VALUES('"+e.getNif()+"', '"+e.getNombre()+"', "+e.getSueldo_base()+")";
		
			sentencia.executeUpdate(SQL);
			
			b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Empleado añadido");
		}else {
			System.out.println("Error al añadir empleado");
		}
	}
	
	//INSERTAR
	public static void insertarEmpleado_v2(Empleado e) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		String SQL = "INSERT INTO empleados (nif, nombre, sueldo_base) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			
			pstmt.setString(1, e.getNif());
			pstmt.setString(2, e.getNombre());
			pstmt.setDouble(3, e.getSueldo_base());
			
			//int filasActualizadas = pstmt.executeUpdate();
			pstmt.executeUpdate();
			
			b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Empleado añadido");
		}else {
			System.out.println("Error al añadir empleado");
		}
	}
	
	//INSERTAR
	public static void insertarPiso(Piso p) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		Statement sentencia;
		try {
			sentencia = con.createStatement();
			String SQL = "INSERT INTO pisos VALUES("+p.getCodigo()+", '"+p.getDireccion()+"', "+p.getMensualidad()+", '"+p.getAlquilado()+"', '"+p.getNifEmpleado()+"')";
		
			sentencia.executeUpdate(SQL);
			
			b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Piso añadido");
		}else {
			System.out.println("Error al añadir piso");
		}
	}
	
	//INSERTAR
	public static void insertarPiso_v2(Piso p) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		String SQL = "INSERT INTO pisos (codigo, direccion, mensualidad, alquilado, nifEmpleado) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			
			pstmt.setInt(1, p.getCodigo());
			pstmt.setString(2, p.getDireccion());
			pstmt.setDouble(3, p.getMensualidad());
			pstmt.setString(4, p.getAlquilado());
			pstmt.setString(5, p.getNifEmpleado());
			
			pstmt.executeUpdate();
			
			b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Piso añadido");
		}else {
			System.out.println("Error al añadir piso");
		}
	}
	
	//ACTUALIZAR
	public static void modificarMensualidad(Piso p) {
		
		boolean b = false;
		int filas = 0;
		
		Connection con = Conexion.getConex();
		
		String SQL = "UPDATE pisos SET mensualidad = ? WHERE codigo = ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
		
			pstmt.setDouble(1, p.getMensualidad());
			pstmt.setInt(2, p.getCodigo());
		    
		    filas = pstmt.executeUpdate();
		    
		    b = true;
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Filas modificadas: "+filas);
		}else {
			System.out.println("Error al modificar la mensualidad");
		}
	}
	
	//ACTUALIZAR
	public static void modificarEmpleadoDelPiso(Piso p) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		String SQL = "UPDATE pisos SET nifEmpleado = ? WHERE codigo = ?";
		
	    try {
	    	PreparedStatement pstmt = con.prepareStatement(SQL);

	        pstmt.setString(1, p.getNifEmpleado());
	        pstmt.setInt(2, p.getCodigo());
	        
	        pstmt.executeUpdate();
	        
	        b = true;
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		}
		
	    if(b==true) {
			System.out.println("Piso modificado");
		}else {
			System.out.println("Error al modificar el piso");
		}
	}
	
	//ACTUALIZAR
	public static void alquilarPiso(Piso p) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		String SQL = "UPDATE pisos SET alquilado = ? WHERE codigo = ?";
		
	    try {
	    	PreparedStatement pstmt = con.prepareStatement(SQL);

	        pstmt.setString(1, p.getAlquilado());
	        pstmt.setInt(2, p.getCodigo());
	        
	        pstmt.executeUpdate();
	        
	        b = true;
		} catch (SQLException e) {
			e.printStackTrace();
			b = false;
		}
		
	    if(b==true) {
			System.out.println("Piso modificado");
		}else {
			System.out.println("Error al modificar el piso");
		}
	}
	
	//CONSULTA
	public static void mostrarNombreEmpleado(int codigo) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		Statement sentencia;
		String SQL = "SELECT e.nombre "
				+ "FROM empleados e, pisos p "
				+ "WHERE p.codigo = "+codigo+" AND e.nif = p.nifEmpleado";
		
		String nombre = "";
		
	    try {
	    	sentencia = con.createStatement();
			ResultSet registros = sentencia.executeQuery(SQL);
			
			while(registros.next()) {
				nombre = registros.getString("nombre");
			}
	        
	        b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
	    if(b==true) {
			System.out.println("Nombre: "+nombre);
		}else {
			System.out.println("No se ha encontrado ninguna coincidencia");
		}
	}
	
	//CONSULTA
	public static void mostrarNombreEmpleado_v2(int codigo) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		
		String SQL = "SELECT e.nombre "
				+ "FROM empleados e, pisos p "
				+ "WHERE p.codigo = ? AND e.nif = p.nifEmpleado";
		
		String nombre = "";
		
	    try {
	    	PreparedStatement ps = con.prepareStatement(SQL);
	    	ps.setInt(1, codigo);
	    	
			ResultSet registros = ps.executeQuery();
			
			while(registros.next()) {
				nombre = registros.getString("nombre");
			}
	        
	        b = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			b = false;
		}
		
	    if(b==true) {
			System.out.println("Nombre: "+nombre);
		}else {
			System.out.println("No se ha encontrado ninguna coincidencia");
		}
	}
	
	//CONSULTA
	public static void mostrarSueldoEmpleado(String nif) {
		
//		boolean b = false;
//		double sueldo = 0;
//		double incremento = 0;
//		
//		Connection con = Conexion.getConex();
//		
//		String SQL1 = "SELECT sueldo_base"
//				+ "FROM empleados "
//				+ "WHERE nif = ?";
//		
//		String SQL2 = "SELECT SUM(p.mensualidad)*0.1 AS total "
//				+ "FROM pisos "
//				+ "WHERE p.alquilado != 0 AND nifEmpleado = ?";
//		
//		PreparedStatement ps;
//		ResultSet rs;
//		
//		try {
//			//1era
//			ps =con.prepareStatement(SQL1);
//			ps.setString(1, nif);
//			
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				sueldo = rs.getDouble("sueldo");
//			}
//			
//			//2da
//			ps =con.prepareStatement(SQL2);
//			ps.setString(2, nif);
//			
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				incremento = rs.getDouble("total");
//			}
//			
//			b = true;
//		}catch (Exception ex) {
//			ex.printStackTrace();
//			b = false;
//		}
//		
//		if(b==true) {
//			System.out.println(sueldo+incremento);
//		}else {
//			System.out.println("No se ha encontrado ninguna coincidencia");
//		}
		
	    // Conectar a la base de datos
	    Connection con = Conexion.getConex();
	    
	    // Consulta SQL para obtener el sueldo base del empleado
	    String SQLBase = "SELECT sueldo_base FROM empleados WHERE nif = ?";
	    
	    // Consulta SQL para obtener las mensualidades de los pisos alquilados
	    String SQLPisos = "SELECT p.mensualidad FROM pisos p WHERE p.nifEmpleado = ? AND p.alquilado = 'si'";
	    
	    double sueldoBase = 0.0;
	    double sueldoExtra = 0.0;
	    
	    try {
	        // Obtener el sueldo base del empleado
	        PreparedStatement pstmtBase = con.prepareStatement(SQLBase);
	        pstmtBase.setString(1, nif);
	        ResultSet rsBase = pstmtBase.executeQuery();
	        
	        if (rsBase.next()) {
	            sueldoBase = rsBase.getDouble("sueldo_base");
	        }
	        
	        // Obtener las mensualidades de los pisos alquilados
	        PreparedStatement pstmtPisos = con.prepareStatement(SQLPisos);
	        pstmtPisos.setString(1, nif);
	        ResultSet rsPisos = pstmtPisos.executeQuery();
	        
	        // Sumar el 10% de la mensualidad de cada piso alquilado
	        while (rsPisos.next()) {
	            double mensualidad = rsPisos.getDouble("mensualidad");
	            sueldoExtra += mensualidad * 0.10; // 10% de la mensualidad
	        }
	        
	        // Calcular el sueldo total
	        double sueldoTotal = sueldoBase + sueldoExtra;
	        
	        // Mostrar el sueldo total
	        System.out.println("El sueldo total del empleado con NIF " + nif + " es: " + sueldoTotal);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al calcular el sueldo.");
	    }
	}
	
	//CONSULTA
	public static void mostrarEmpleadoConMasPisos() {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		PreparedStatement ps;
		ResultSet rs;
		
		String SQL1 = "SELECT e.nif, e.nombre, COUNT(p.codigo) "
				+ "FROM empleados e, pisos p "
				+ "WHERE p.alquilado = 'si' AND p.nifEmpleado = e.nif "
				+ "GROUP BY e.nif, e.nombre";
		
		Empleado e = null;
		
		try {
			ps = con.prepareStatement(SQL1);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				e = new Empleado();
				e.setNif(rs.getString("nif"));
				e.setNombre(rs.getString("nombre"));
			}
			
			b = true;
		}catch (Exception ex) {
			ex.printStackTrace();
			b = false;
		}
		
		if(b==true) {
			System.out.println("Empleado: "+e.getNif()+" - "+e.getNombre());
		}else {
			System.out.println("No se ha encontrado ninguna coincidencia");
		}
	}
	
	//ELIMINAR
	public static void eliminarPiso(int codigo) {
		
		boolean b = false;
		
		Connection con = Conexion.getConex();
		PreparedStatement ps;
		
		String SQL = "DELETE FROM pisos WHERE codigo = ?";
		
		try {
			ps = con.prepareStatement(SQL);
			ps.setInt(1, codigo);
			
			int filas = ps.executeUpdate();
			
			if(filas>0) {
				b = true;
			}else {
				b = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(b) {
			System.out.println("Piso eliminado");
		}else {
			System.out.println("Piso no encontrado");
		}
	}

}
