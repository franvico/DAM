package main;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	  
	  static {
	    try {
	      // Cargar la configuración desde hibernate.cfg.xml
	      sessionFactory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	      // Log la excepción si ocurre algún problema al crear la SessionFactory
	      System.err.println("Initial SessionFactory creation failed." + ex);
	      throw new ExceptionInInitializerError(ex);
	    }
	  }
	  
	  // Método para obtener la SessionFactory
	  public static SessionFactory getSessionFactory() {
	    return sessionFactory;
	  }

	  // Método para cerrar la SessionFactory cuando ya no sea necesario
	  public static void shutdown() {
	    getSessionFactory().close();
	  }
}
