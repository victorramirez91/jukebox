package dboperations;

import objects.Ticket;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;
import org.hibernate.Session;


public class DBOperations {

	

	private AnnotationConfiguration config;
	private SessionFactory factory;

	
	 public DBOperations() {
		 super();
			config = new AnnotationConfiguration();
			config.addAnnotatedClass(Ticket.class);
			config.configure();
			factory = config.buildSessionFactory();
		
	}
	 
	 public void addTicket(Ticket ticket)
		{
			//añade un equipo y lo mete en un campeonato
			
			
			Session sesion = factory.openSession();
			System.out.println("addticket");
			sesion.beginTransaction();
			sesion.save(ticket);
			sesion.getTransaction().commit();
			System.out.println("Ticket saved correctly.");
			sesion.close();
			
			
			
		}
	
}
