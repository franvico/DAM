package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.Conexion;

public class Main {

	public static void main(String[] args) {
		
		//AQUÍ PARA PROBAR LOS MÉTODOS
		addProduct(1, 1, 4);
		
		System.out.println("\n");
		
		mostrarEmail(1);
		
		System.out.println("\n");
	}
	
	/**
	 * Método para añadir a un pedido existente
	 * un producto ya existente, en la cantidad indicada.
	 * @param idProd
	 * @param idPed
	 * @param cantidad
	 */
	private static void addProduct(int idProd, int idPed, int cantidad) {

		int filas = 0;
		
		Connection con = Conexion.getConex();
		PreparedStatement ps;
		ResultSet rs;
		
		String sqlExiste = "SELECT COUNT(*) FROM detalles_pedido WHERE id_pedido = ? AND id_producto = ?";
		String SQL = "INSERT INTO detalles_pedido (cantidad, id_pedido, id_producto) VALUES (?, ?, ?)";
		
		try {
			//verificar
			ps = con.prepareStatement(sqlExiste);
	        ps.setInt(1, idPed);
	        ps.setInt(2, idProd);
	        
	        rs = ps.executeQuery();
	        
	        int cont = 0;
	        if (rs.next()) {
	            cont = rs.getInt(1);
	        }
			
	        if (cont > 0) {
	        	System.out.println("El pedido ya existe");
	        }else {
				//insertar
				ps = con.prepareStatement(SQL);
				ps.setInt(1, cantidad);
				ps.setInt(2, idPed);
				ps.setInt(3, idProd);
				
				filas = ps.executeUpdate();
				
				if(filas>0) {
					System.out.println("Producto añadido a pedido");
				}else {
					System.out.println("No se ha podido añadir el producto");
				}
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Método para mostrar el email del
	 * usuario que ha realizado el pedido indicado
	 * @param idPed
	 */
	private static void mostrarEmail(int idPed) {
		
		String email = "";
		
		Connection con = Conexion.getConex();
		PreparedStatement ps;
		ResultSet rs;
		
		String SQL = "SELECT u.email "
				+ "FROM pedidos p, usuarios u "
				+ "WHERE p.id_pedido = ? AND p.id_usuario = u.id_usuario";
		
		try {
			ps = con.prepareStatement(SQL);
			ps.setInt(1, idPed);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				email = rs.getString("email");
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(email);
	}
	
	/**
	 * Método para mostrar el usuario que más pedidos
	 * ha realizado
	 */
	private static void usuarioMasPedidos() {
		
		
	}

}
