package logica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import modelo.Cochera;
import modelo.Estacion;
import modelo.Linea;
import modelo.LineaEstacion;
import modelo.LineaEstacionPK;

public class Main {

	public static void main(String[] args) {
		
		/*PRUEBA*/
		insertarLinea("linea1");
		
		modificarNombreCochera(1, "cochera1");
		
		Linea l = mostrarLineaConMasTrenes();
		
		if(l==null) {
			System.out.println("No hay lineas");
		}else {
			System.out.println("Linea con mas trenes: "+l);
		}
		
		ampliarLinea(1, 2, 2);
		
		actualizarEstaciones();
		
		List<Estacion> estaciones = mostrarEstacionesDeLinea("Linea 1");
		
		for(Estacion e : estaciones) {
			System.out.println(e);
		}
	}
	
	/*
	 * PERSIST: para añadir objetos
	 * MERGE/NADA: para actualizar
	 * REMOVE: eliminar objeto
	 * */
	
	/*PRUEBA*/
	public static void insertarLinea(String nombre) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
		
		Linea l = new Linea();
        l.setNombre(nombre);
        
        sesion.persist(l);
        
        tx.commit();
        sesion.close();
	}
	
	public static void modificarNombreCochera(int codCochera, String nombre) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Cochera c = sesion.get(Cochera.class, codCochera);
        
        if(c!=null) {
        	c.setNombre(nombre);
        	System.out.println("Nombre actualizado");
        }else {
        	System.out.println("Id no encontrado");
        }
        
        tx.commit();
        sesion.close();
	}
	
	public static Linea mostrarLineaConMasTrenes() {
		
		int max = 0;
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        Linea linea = null;
        
        List<Linea> lineas = sesion.createQuery("FROM Linea", Linea.class).list();
        
        for(Linea l : lineas) {
        	int trenes = l.getTrenes().size();
        	
        	if(max<trenes) {
        		max = trenes;
        		linea = l;
        	}
        }
        
        tx.commit();
        sesion.close();
        
        return linea;
	}
	
	public static void ampliarLinea(int codLinea, int codEstacion, int orden) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        LineaEstacion le = new LineaEstacion();
    	LineaEstacionPK lePK = new LineaEstacionPK(codLinea, codEstacion);
        
        Linea l = sesion.get(Linea.class, codLinea);
        Estacion e = sesion.get(Estacion.class, codEstacion);
        
        if(l!=null && e!=null) {
        	String hql = "SELECT COUNT(le) "
        			+ "FROM LineaEstacion le "
        			+ "WHERE le.linea.codLinea = :codLinea AND le.estacion.codEstacion = :codEstacion";
        	
        	Query<Long> query = sesion.createQuery(hql, Long.class);
            query.setParameter("codLinea", codLinea);
            query.setParameter("codEstacion", codEstacion);
            
            Long i = query.uniqueResult();
            
            if(i>0) {
            	System.out.println("La estacion ya existe en esa linea");
            }else {
        	
	        	String maxHQL = "SELECT MAX(le.orden) "
	        			+ "FROM LineaEstacion le "
	        			+ "WHERE le.linea.codLinea = :codLinea ";
	        	
	        	Query<Integer> query1 = sesion.createQuery(maxHQL, Integer.class);
	            query1.setParameter("codLinea", codLinea);
	            
	            Integer max = query1.uniqueResult();
	            
	            if(max!=null && max<orden) {
	            	le.setId(lePK);
	            	le.setLinea(l);
	            	le.setEstacion(e);
	            	le.setOrden(orden);
	            	
	            	System.out.println("Estacion insertada al final");
	            }else {
	            	String hql2 = "SELECT le.orden "
	            			+ "FROM LineaEstacion le "
	            			+ "WHERE le.linea.codLinea = :codLinea AND le.orden = :orden";
	            	
	            	Query<Integer> query2 = sesion.createQuery(hql2, Integer.class);
	                query2.setParameter("codLinea", codLinea);
	                query2.setParameter("orden", orden);
	                
	                Integer n = query2.uniqueResult();
	                
	                if(n!=null) {
		                String hql3 = "UPDATE LineaEstacion le "
		            			+ "SET le.orden = le.orden + 1 "
		            			+ "WHERE le.linea.codLinea = :codLinea AND le.orden >= :n";
		                
		                MutationQuery query3 = sesion.createMutationQuery(hql3);//para consultas que modifican datos (UPDATE, DELETE, INSERT).
		                query3.setParameter("codLinea", codLinea);
		                query3.setParameter("n", n);
		                
		                query3.executeUpdate();
	                }
	            	
	                le.setId(lePK);
	            	le.setLinea(l);
	            	le.setEstacion(e);
	            	le.setOrden(orden);
	            	
	            	System.out.println("Estacion insertada en medio");
	            }
	        	
	        	sesion.persist(le);
	        	System.out.println("Linea ampliada");
            }
        }else {
        	System.out.println("Linea o estacion no existen");
        }
        
        Linea linea = mostrarLineaConMasTrenes();
    	
    	if(linea!=null && linea.getCodLinea()==codLinea) {
    		System.out.println("Ahora esta linea es la mas larga");
    	}
        
        tx.commit();
        sesion.close();
	}
	
	public static void actualizarEstaciones() {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
        String hql1 = "UPDATE Estacion e SET e.numAccesos = " +
                      "(SELECT COUNT(a) FROM Acceso a WHERE a.estacion.codEstacion = e.codEstacion)";
        
        sesion.createMutationQuery(hql1).executeUpdate();

        String hql2 = "UPDATE Estacion e SET e.numLineas = " +
                      "(SELECT COUNT(DISTINCT le.linea.codLinea) FROM LineaEstacion le WHERE le.estacion.codEstacion = e.codEstacion)";
        
        sesion.createMutationQuery(hql2).executeUpdate();

        String hql3 = "UPDATE Estacion e SET e.numViajesDestino = " +
                      "(SELECT COUNT(v) FROM Viaje v WHERE v.estacionDestino.codEstacion = e.codEstacion)";
        
        sesion.createMutationQuery(hql3).executeUpdate();

        String hql4 = "UPDATE Estacion e SET e.numViajesOrigen = " +
                      "(SELECT COUNT(v) FROM Viaje v WHERE v.estacionOrigen.codEstacion = e.codEstacion)";
        
        sesion.createMutationQuery(hql4).executeUpdate();
        
        System.out.println("Tablas actualizadas");
        
        tx.commit();
        sesion.close();
	}
	
	public static List<Estacion> mostrarEstacionesDeLinea(String nombre) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesion.beginTransaction();
        
//        String hql = "SELECT e "
//        		+ "FROM Estacion e, Linea l ,LineaEstacion le "
//        		+ "WHERE l.nombre = :nombre AND e.codEstacion = le.estacion.codEstacion "
//        		+ "AND le.linea.codLinea = l.codLinea "
//        		+ "ORDER BY le.orden";
        
        String hql = "SELECT e FROM LineaEstacion le " +
                "JOIN le.estacion e " +
                "JOIN le.linea l " +
                "WHERE l.nombre = :nombre " +
                "ORDER BY le.orden";
		
        Query<Estacion> query = sesion.createQuery(hql, Estacion.class);
        query.setParameter("nombre", nombre);
        
        List<Estacion> estaciones = query.getResultList();
        
        sesion.close();
		
		return estaciones;
	}

}
