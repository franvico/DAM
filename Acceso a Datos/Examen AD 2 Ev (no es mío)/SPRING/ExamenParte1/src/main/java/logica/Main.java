package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import clases.Jugador;
import clases.Partido;
import conexion.Conexion;

public class Main {

	public static void main(String[] args) {
		
		List<Partido> partidos = juegaEn(2);
		
		for(Partido p : partidos) {
			System.out.println(p);
		}
		
		System.out.println("\n");
		
		boolean si = asignaPartidoJugador(2, 2);
		
		if(si==true) {
			System.out.println("Jugador asignado a partido");
		}else {
			System.out.println("Jugador o partido no existen");
		}

	}
	
	public static List<Partido> juegaEn (int idJugador) {
		
		Connection con = Conexion.getConex();
		PreparedStatement ps;
		ResultSet rs;
		
		String sql = "SELECT p.* "
				+ "FROM partido p, jugador j, juegaen je "
				+ "WHERE j.id = ? AND j.id = je.idJugador AND je.idPartido = p.id";
		
		List<Partido> partidos = new LinkedList<>();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, idJugador);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				Date fecha = rs.getDate("fecha");
				String lugar = rs.getString("lugar");
				
				partidos.add(new Partido(id, fecha, lugar));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return partidos;
	}
	
	public static boolean asignaPartidoJugador(int idPartido, int idJugador) {
		
		boolean si = false;
		
		int idP = 0;
		int idJ = 0;
		
		Connection con = Conexion.getConex();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sqlPartido = "SELECT p.id "
				+ "FROM partido p "
				+ "WHERE p.id = ?";
		
		String sqlJugador = "SELECT j.id "
				+ "FROM jugador j "
				+ "WHERE j.id = ?";
		
		try {
			//partido
			ps = con.prepareStatement(sqlPartido);
			ps.setInt(1, idPartido);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idP = rs.getInt("id");
			}
			
			//jugador
			ps = con.prepareStatement(sqlJugador);
			ps.setInt(1, idJugador);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idJ = rs.getInt("id");
			}
			
			if(idP==0 || idJ==0) {				
				si = false;
			}else {
				String sqlExiste = "SELECT COUNT(*) FROM juegaen WHERE idJugador = ? AND idPartido = ?";
				
				ps = con.prepareStatement(sqlExiste);
		        ps.setInt(1, idJugador);
		        ps.setInt(2, idPartido);
		        
		        rs = ps.executeQuery();
		        
		        int cont = 0;
		        if (rs.next()) {
		            cont = rs.getInt(1);
		        }
		        
		        if (cont > 0) {
		        	System.out.println("El jugador " + idJugador + " ya está asignado al partido " + idPartido + ".");
		            return false;
		        }else {
					String sql = "INSERT INTO juegaen (idJugador, idPartido) VALUES (?, ?)";
					
					ps = con.prepareStatement(sql);
					ps.setInt(1, idJugador);
					ps.setInt(2, idPartido);
					
					ps.executeUpdate();
					
					si = true;
		        }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		return si;
	}
	
	public static Jugador masPartido() {
		
		return null;
	}
	
	

}
