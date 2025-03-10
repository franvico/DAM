package logica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import modelo.JuegaEn;
import modelo.JuegaEnPK;
import modelo.Jugador;

public class Main {

	public static void main(String[] args) {
		
		Jugador j = masPartidos();
		
		System.out.println(j.getNombre());

	}
	
	public static Jugador masPartidos() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        String hql = "SELECT je.jugador "
        		+ "FROM JuegaEn je "
        		+ "GROUP BY je.jugador "
        		+ "ORDER BY COUNT(je.jugador) DESC";
        
        Query<Jugador> query = sesion.createQuery(hql, Jugador.class);
        query.setMaxResults(1);
        
        Jugador j = query.uniqueResult();
        
        tx.commit();
        sesion.close();
        
        return j;
	}
	
	public static boolean juegaEn(int idJugador, String lugar) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
       // String hql = "SELECT je.id"
        
        //JuegaEnPK jePK = new JuegaEnPK(idJugador);
        //JuegaEn je = sesion.get(JuegaEn.class, jePK);
        
        //if(je!=null) {
//        	String hql = "FROM jugador "
//            		+ "WHERE nom_proy = :nomProy";
//            
//            Query<Jugador> query = sesion.createQuery(hql, Jugador.class);
//            query.setParameter("nomProy", nom_proy);
//            
//            List<Proyecto> proyectos = query.list();
//        }else {
//        	System.out.println("Jugador no existe");
//        	return false;
//        }
		
		return false;
	}

}
