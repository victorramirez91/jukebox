package dboperations;

import java.sql.Timestamp;
import java.util.Date;

import objects.Ticket;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
			//añade ticket a la BBDD
			
			
			Session sesion = factory.openSession();
			System.out.println("addticket");
			sesion.beginTransaction();
			sesion.save(ticket);
			sesion.getTransaction().commit();
			System.out.println("Ticket saved correctly.");
			sesion.close();
			
			
			
		}
	 
	 public int checkTicket(String  key)
		{
			//chequea si la clave esta en la base de datos
			System.out.println(key);
			
			Session sesion = factory.openSession();
			System.out.println("checkticket");
			
			
			
			SQLQuery query = sesion.createSQLQuery("SELECT * FROM ticket WHERE idticket = :key");
			query.addEntity(Ticket.class);
			query.setString("key", key);
			sesion.beginTransaction();
			Ticket ticket = (Ticket) query.uniqueResult();
			sesion.getTransaction().commit();
			sesion.close();

			if (ticket == null) {
				System.out.println("No existe");
				return 0;
			} else {
				System.out.println("Existe");
				String timestamp = ticket.getDate();
				//String text = "2011-10-02 18:48:05.123456";
		        Timestamp ts = Timestamp.valueOf(timestamp);
		        java.util.Date date = new Date();
				System.out.println (date);
				 long lnMilisegundos = date.getTime();
		        java.sql.Timestamp sqlTimestamp2 = new java.sql.Timestamp(lnMilisegundos);
		         float diff = sqlTimestamp2.getTime() - ts.getTime();
		        System.out.println(diff);
		         float hora = diff/3600000;
		         System.out.println("Horas"+hora);
		         	// Borrar con intervalos:
		         	//delete from ticket where date > (DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 10 DAY),'%Y-%m-%d'))
		         if(hora < 0.5)
		         {
		        	 System.out.println("No ha pasado media hora");
		        	 return 1;
		         }
		         else {
		        	 System.out.println("Ha pasado media hora");
		        	 return 2;
		         }
				
			}
			
			
			
		}
	
}
