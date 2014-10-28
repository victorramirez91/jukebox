package dboperations;

import objects.Ticket;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;



public class CreateDataBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Ticket.class);
		
		config.configure();
		
	
		
		new SchemaExport (config).create(true, true);
		
		//hibernate session
		SessionFactory factory = config.buildSessionFactory();
		Session sesion = factory.getCurrentSession();
		sesion.beginTransaction();		
		Ticket campeonato2= new Ticket("test2","xxx2","aaa2",true);
		sesion.save(campeonato2);

		
		
		sesion.getTransaction().commit();
		System.out.println("Done");		
			
		
	}
}
