package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		addProduct(3,1,10);
//		mostrarEmail(2);
//		usuarioMasPedidos();
//		usuarioMenosPedidos();
//		actualizarNombreUsuario(2, "Raquel Gómez");
//		eliminarPedido(2);
//		obtenerUsuarioPorEmail("maria.gomez@example.com");
//		obtenerPedidosPorFecha("2025-03-11");
//		listarProductosDePedido(1);
//		actualizarCantidadProductoEnPedido(1, 1, 5);
//		obtenerPedidosAntesDeFecha("2025-03-11 11:00:00");
//		obtenerPedidosDespuesDeFecha("2025-03-11 11:00:00");

	}
	
	/**
	 * Método para añadir a un pedido existente
	 * un producto ya existente, en la cantidad indicada.
	 * @param idProd
	 * @param idPed
	 * @param cantidad
	 */
	private static void addProduct(int idProd, int idPed, int cantidad) {
		
		String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

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
		
		String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";
		
		try {
			Connection con = DriverManager.getConnection(url,"root","1234");
			String s1 = "SELECT u.email FROM usuarios u, pedidos p WHERE u.id_usuario = p.id_usuario AND p.id_pedido = ?";
			
			PreparedStatement ps = con.prepareStatement(s1);
			ps.setInt(1, idPed);
			ResultSet rs = ps.executeQuery();
			boolean esta = false;
			while(rs.next()) {
				System.out.println(rs.getString("email"));
				esta = true;
				
			}
			if(!esta)
				System.out.println("No existe ese pedido");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para mostrar el usuario que más pedidos
	 * ha realizado
	 * @throws SQLException 
	 */
	private static void usuarioMasPedidos() {		

		String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";
		
		try {
			Connection con = DriverManager.getConnection(url,"root","1234");
			
			// Paso 1: Obtener la cantidad máxima de pedidos hecha por un usuario
		    String maxPedidosQuery = "SELECT COUNT(*) as total FROM pedidos GROUP BY id_usuario ORDER BY total DESC LIMIT 1";
		    Statement stmtMax = con.createStatement();
		    ResultSet rsMax = stmtMax.executeQuery(maxPedidosQuery);

		    int maxPedidos = 0;
		    if (rsMax.next()) {
		        maxPedidos = rsMax.getInt("total");
		    }
		    rsMax.close();
		    stmtMax.close();

		    if (maxPedidos == 0) {
		        System.out.println("No hay pedidos registrados.");
		        return;
		    }

		    // Paso 2: Obtener los usuarios que han realizado esa cantidad máxima de pedidos
		    String query = "SELECT u.id_usuario, u.nombre, COUNT(p.id_usuario) as total_pedidos " +
		                   "FROM usuarios u " +
		                   "JOIN pedidos p ON u.id_usuario = p.id_usuario " +
		                   "GROUP BY u.id_usuario, u.nombre " +
		                   "HAVING total_pedidos = ?";
		    
		    PreparedStatement ps = con.prepareStatement(query);
		    ps.setInt(1, maxPedidos);
		    ResultSet rs = ps.executeQuery();

		    System.out.println("Usuarios con más pedidos (" + maxPedidos + " pedidos):");
		    while (rs.next()) {
		        int idUsuario = rs.getInt("id_usuario");
		        String nombreUsuario = rs.getString("nombre");
		        System.out.println("- ID: " + idUsuario + ", Nombre: " + nombreUsuario);
		    }

		    rs.close();
		    ps.close();
		    con.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método para mostrar el/los usuario(s) que menos pedidos
	 * han realizado
	 */
	private static void usuarioMenosPedidos() {		

	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");

	        // Paso 1: Obtener la cantidad mínima de pedidos hecha por un usuario
	        String minPedidosQuery = "SELECT COUNT(*) as total FROM pedidos GROUP BY id_usuario ORDER BY total ASC LIMIT 1";
	        Statement stmtMin = con.createStatement();
	        ResultSet rsMin = stmtMin.executeQuery(minPedidosQuery);

	        int minPedidos = 0;
	        if (rsMin.next()) {
	            minPedidos = rsMin.getInt("total");
	        }
	        rsMin.close();
	        stmtMin.close();

	        if (minPedidos == 0) {
	            System.out.println("No hay pedidos registrados.");
	            return;
	        }

	        // Paso 2: Obtener los usuarios que han realizado esa cantidad mínima de pedidos
	        String query = "SELECT u.id_usuario, u.nombre, COUNT(p.id_usuario) as total_pedidos " +
	                       "FROM usuarios u " +
	                       "LEFT JOIN pedidos p ON u.id_usuario = p.id_usuario " +
	                       "GROUP BY u.id_usuario, u.nombre " +
	                       "HAVING total_pedidos = ?";

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, minPedidos);
	        ResultSet rs = ps.executeQuery();

	        System.out.println("Usuarios con menos pedidos (" + minPedidos + " pedidos):");
	        while (rs.next()) {
	            int idUsuario = rs.getInt("id_usuario");
	            String nombreUsuario = rs.getString("nombre");
	            System.out.println("- ID: " + idUsuario + ", Nombre: " + nombreUsuario);
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para actualizar el nombre de un usuario dado su ID.
	 * @param idUsuario ID del usuario a actualizar.
	 * @param nuevoNombre Nuevo nombre del usuario.
	 */
	private static void actualizarNombreUsuario(int idUsuario, String nuevoNombre) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "UPDATE usuarios SET nombre = ? WHERE id_usuario = ?";
	        
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, nuevoNombre);
	        ps.setInt(2, idUsuario);
	        
	        int filasAfectadas = ps.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Usuario actualizado correctamente.");
	        } else {
	            System.out.println("No se encontró un usuario con ese ID.");
	        }

	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para eliminar un pedido dado su ID. 
	 * Primero elimina los productos asociados en detalles_pedido.
	 * @param idPedido ID del pedido a eliminar.
	 */
	private static void eliminarPedido(int idPedido) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");

	        // Paso 1: Eliminar los detalles del pedido antes de eliminar el pedido
	        String deleteDetallesQuery = "DELETE FROM detalles_pedido WHERE id_pedido = ?";
	        PreparedStatement psDetalles = con.prepareStatement(deleteDetallesQuery);
	        psDetalles.setInt(1, idPedido);
	        psDetalles.executeUpdate();
	        psDetalles.close();

	        // Paso 2: Eliminar el pedido
	        String deletePedidoQuery = "DELETE FROM pedidos WHERE id_pedido = ?";
	        PreparedStatement psPedido = con.prepareStatement(deletePedidoQuery);
	        psPedido.setInt(1, idPedido);
	        
	        int filasAfectadas = psPedido.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Pedido eliminado correctamente.");
	        } else {
	            System.out.println("No se encontró un pedido con ese ID.");
	        }

	        psPedido.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para obtener un usuario dado su correo electrónico.
	 * @param email Correo del usuario.
	 */
	private static void obtenerUsuarioPorEmail(String email) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "SELECT * FROM usuarios WHERE email = ?";
	        
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id_usuario") + ", Nombre: " + rs.getString("nombre"));
	        } else {
	            System.out.println("No se encontró un usuario con ese email.");
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para obtener todos los pedidos realizados en una fecha específica.
	 * @param fecha Fecha de los pedidos (formato: YYYY-MM-DD).
	 */
	private static void obtenerPedidosPorFecha(String fecha) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");

	        // Convertimos fecha_pedido a solo fecha para ignorar la hora
	        String query = "SELECT id_pedido, id_usuario, fecha_pedido FROM pedidos WHERE DATE(fecha_pedido) = ?";
	        
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, fecha);
	        ResultSet rs = ps.executeQuery();

	        List<String> pedidos = new ArrayList<>();
	        while (rs.next()) {
	            pedidos.add("ID Pedido: " + rs.getInt("id_pedido") + 
	                        ", ID Usuario: " + rs.getInt("id_usuario") + 
	                        ", Fecha: " + rs.getString("fecha_pedido"));
	        }

	        if (pedidos.isEmpty()) {
	            System.out.println("No hay pedidos en la fecha indicada.");
	        } else {
	            pedidos.forEach(System.out::println);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	/**
	 * Método para obtener la lista de productos de un pedido específico.
	 * @param idPedido ID del pedido.
	 */
	private static void listarProductosDePedido(int idPedido) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "SELECT p.id_producto, p.nombre, dp.cantidad " +
	                       "FROM detalles_pedido dp " +
	                       "JOIN productos p ON dp.id_producto = p.id_producto " +
	                       "WHERE dp.id_pedido = ?";

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, idPedido);
	        ResultSet rs = ps.executeQuery();

	        List<String> productos = new ArrayList<>();
	        while (rs.next()) {
	            productos.add("ID Producto: " + rs.getInt("id_producto") + ", Nombre: " + rs.getString("nombre") + ", Cantidad: " + rs.getInt("cantidad"));
	        }

	        if (productos.isEmpty()) {
	            System.out.println("No hay productos en el pedido indicado.");
	        } else {
	            productos.forEach(System.out::println);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para actualizar la cantidad de un producto en un pedido específico.
	 * @param idPedido ID del pedido.
	 * @param idProducto ID del producto.
	 * @param nuevaCantidad Nueva cantidad a actualizar.
	 */
	private static void actualizarCantidadProductoEnPedido(int idPedido, int idProducto, int nuevaCantidad) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "UPDATE detalles_pedido SET cantidad = ? WHERE id_pedido = ? AND id_producto = ?";

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, nuevaCantidad);
	        ps.setInt(2, idPedido);
	        ps.setInt(3, idProducto);

	        int filasAfectadas = ps.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Cantidad de producto actualizada correctamente.");
	        } else {
	            System.out.println("No se encontró el producto en el pedido indicado.");
	        }

	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Método para obtener todos los pedidos realizados antes de una fecha y hora específica.
	 * @param fechaHora Fecha y hora en formato: "YYYY-MM-DD HH:MM:SS".
	 */
	private static void obtenerPedidosAntesDeFecha(String fechaHora) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "SELECT id_pedido, id_usuario, fecha_pedido FROM pedidos WHERE fecha_pedido < ?";

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, fechaHora);
	        ResultSet rs = ps.executeQuery();

	        List<String> pedidos = new ArrayList<>();
	        while (rs.next()) {
	            pedidos.add("ID Pedido: " + rs.getInt("id_pedido") +
	                        ", ID Usuario: " + rs.getInt("id_usuario") +
	                        ", Fecha: " + rs.getString("fecha_pedido"));
	        }

	        if (pedidos.isEmpty()) {
	            System.out.println("No hay pedidos antes de la fecha indicada.");
	        } else {
	            pedidos.forEach(System.out::println);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Método para obtener todos los pedidos realizados después de una fecha y hora específica.
	 * @param fechaHora Fecha y hora en formato: "YYYY-MM-DD HH:MM:SS".
	 */
	private static void obtenerPedidosDespuesDeFecha(String fechaHora) {
	    String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";

	    try {
	        Connection con = DriverManager.getConnection(url, "root", "1234");
	        String query = "SELECT id_pedido, id_usuario, fecha_pedido FROM pedidos WHERE fecha_pedido > ?";

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, fechaHora);
	        ResultSet rs = ps.executeQuery();

	        List<String> pedidos = new ArrayList<>();
	        while (rs.next()) {
	            pedidos.add("ID Pedido: " + rs.getInt("id_pedido") +
	                        ", ID Usuario: " + rs.getInt("id_usuario") +
	                        ", Fecha: " + rs.getString("fecha_pedido"));
	        }

	        if (pedidos.isEmpty()) {
	            System.out.println("No hay pedidos después de la fecha indicada.");
	        } else {
	            pedidos.forEach(System.out::println);
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
