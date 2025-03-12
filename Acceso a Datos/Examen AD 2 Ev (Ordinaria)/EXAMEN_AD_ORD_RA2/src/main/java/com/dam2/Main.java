package com.dam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
//		eliminaRecetaMasIngredientes();
//		mostrarRecetasIngrediente(2);
	}
	
	
	
	/**
	 * Método para eliminar de la BD la receta
	 * que más ingredientes tenga
	 */
	private static void eliminaRecetaMasIngredientes() {
		
		String url = "jdbc:mysql://localhost:3306/recetas?serverTimezone=UTC";
		
		 try {
		        Connection con = DriverManager.getConnection(url, "root", "1234");
		        
		        // Paso 1. Obtengo el id_receta de la receta con la cantidad máxima de ingredientes
		        String maxIngredientesQuery = "SELECT COUNT(*) AS total, id_receta FROM receta_ingrediente GROUP BY id_receta ORDER BY total DESC LIMIT 1";
		        Statement stmtMax = con.createStatement();
			    ResultSet rsMax = stmtMax.executeQuery(maxIngredientesQuery);
			    
			    int maxIngredientes = 0;
			    if (rsMax.next()) {
			    	maxIngredientes = rsMax.getInt("total");
			    }
			   
			    if (maxIngredientes == 0) {
			        System.out.println("No hay ingredientes asignados a recetas.");
			        return;
			    }
			    else {
			    	int id_recetaMax = rsMax.getInt("id_receta");
			    	// Paso 2. Elimino los registros de receta_ingredientes con ese id_receta
			    	String queryEliminarRecetaDeIngredientesReceta = "DELETE FROM receta_ingrediente WHERE id_receta = ?";
			    	PreparedStatement ps = con.prepareStatement(queryEliminarRecetaDeIngredientesReceta);
			    	ps.setInt(1, id_recetaMax);
			    	ps.executeUpdate();
			    	
			    	// Paso 3. Elimino la receta de la tabla receta
			    	String queryEliminarReceta = "DELETE FROM receta WHERE id_receta = ?";
			    	PreparedStatement ps2 = con.prepareStatement(queryEliminarReceta);
			    	ps2.setInt(1, id_recetaMax);
			    	ps2.executeUpdate();
			    	
			    	System.out.println("Receta eliminada de la base de datos");
			    }
		        
			    
		        
		 } catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	/**
	 * Método para mostrar todos los nombres de
	 * recetas a los que pertenece un ingrediente.
	 * @param idIng
	 */
	private static void mostrarRecetasIngrediente(int idIng) {
		
		String url = "jdbc:mysql://localhost:3306/recetas?serverTimezone=UTC";
		
		 try {
		        Connection con = DriverManager.getConnection(url, "root", "1234");

		        String query = "SELECT r.nombre FROM receta r WHERE id_receta IN (SELECT id_receta FROM receta_ingrediente WHERE id_ingrediente = ?)";
		        PreparedStatement ps = con.prepareStatement(query);
		        ps.setInt(1, idIng);
		        ResultSet rs = ps.executeQuery();
		        List<String> nombresRecetas = new ArrayList<>();
		        
		        while(rs.next()) {
		        	nombresRecetas.add(rs.getString("nombre"));
		        }
		        
		        if(nombresRecetas.isEmpty()) {
		        	System.out.println("Ese ingrediente no está asignado a ninguna receta");
		        }
		        else {
		        	System.out.println("El ingrediente se usa en las siguiente recetas:");
		        	nombresRecetas.forEach(System.out::println);
		        }
		        
		 } catch (Exception e) {
			 e.printStackTrace();
		}
		
	}

}
