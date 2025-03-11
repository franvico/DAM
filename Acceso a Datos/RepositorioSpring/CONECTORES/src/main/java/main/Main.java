package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//AQUÍ PARA PROBAR LOS MÉTODOS
	}
	
	/**
	 * Método para añadir a un pedido existente
	 * un producto ya existente, en la cantidad indicada.
	 * @param idProd
	 * @param idPed
	 * @param cantidad
	 */
	private static void addProduct(int idProd, int idPed, int cantidad) {
		
		String url = "jdbc:mysql://localhost:8080/tienda?serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,"root","1234");
			String s1 = "SELECT id_pedido FROM pedidos WHERE id_pedido=?";
			String s2 = "SELECT id_producto FROM productos WHERE id_producto=?";
			String s3 = "INSERT INTO detalles_pedido VALUES (?,?,?)";
			
			PreparedStatement ps1 = con.prepareStatement(s1);
			ps1.setInt(1, idPed);
			ResultSet rs1 = ps1.executeQuery();
			if(!rs1.next()) {
				System.out.println("No existe pedido");
				con.close();
				return;
			}
			PreparedStatement ps2 = con.prepareStatement(s2);
			ps2.setInt(1, idProd);
			ResultSet rs2 = ps2.executeQuery();
			if(!rs2.next()) {
				System.out.println("No existe producto");
				con.close();
				return;
			}
			PreparedStatement ps3 = con.prepareStatement(s3);
			ps3.setInt(1, cantidad);
			ps3.setInt(2, idPed);
			ps3.setInt(3, idProd);
			ps3.executeUpdate();
			con.close();
			System.out.println("Pedido insertado");
			
		} catch (SQLException e) {
			System.out.println("No se pudo insertar");
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Método para mostrar el email del
	 * usuario que ha realizado el pedido indicado
	 * @param idPed
	 */
	private static void mostrarEmail(int idPed) {
		String url = "jdbc:mysql://localhost:8080/tienda?serverTimezone=UTC";
		try {
			Connection con = DriverManager.getConnection(url,"root","1234");
			PreparedStatement ps = con.prepareStatement(
					"SELECT u.email FROM usuarios u , pedidos p WHERE p.id_pedido = ? AND p.id_usuario = u.id_usuario");
			ps.setInt(1, idPed);
			ResultSet rs = ps.executeQuery();
			boolean esta = false;
			while(rs.next()) {
				System.out.println(rs.getString("email"));
				esta = true;
				
			}
			if(!esta)
				System.out.println("No existe ese pedido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para mostrar el usuario que más pedidos
	 * ha realizado
	 * @throws SQLException 
	 */
	private static void usuarioMasPedidos() throws SQLException {
		
		List<Integer> usuarios = new LinkedList<>();
		String url = "jdbc:mysql://localhost:8080/tienda?serverTimezone=UTC";
		
		Connection con = DriverManager.getConnection(url,"root","1234");
		
		Statement s1 = con.createStatement();
		ResultSet ids = s1.executeQuery("SELECT id_usuario FROM usuarios");
		while(ids.next())
			usuarios.add(ids.getInt("id_usuario"));
		
		int idUsuMax = usuarios.get(0);
		int contMax = 0;
		String sent = "SELECT COUNT(id_usuario) FROM pedidos WHERE id_usuario = ?";
		PreparedStatement ps = con.prepareStatement(sent);
		for(int i=0;i<usuarios.size();i++) {
			ps.setInt(1, usuarios.get(i));
			ResultSet rs = ps.executeQuery();
			int cont = 0;
			while(rs.next())
				cont = rs.getInt(1);
			if(cont > contMax) {
				contMax = cont;
				idUsuMax = usuarios.get(i);
			}	
		}
		System.out.println("El usuario con más pedidos tiene id "+idUsuMax);
		
	}

}
