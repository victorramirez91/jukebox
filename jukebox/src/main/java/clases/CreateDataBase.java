package clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
		Ticket campeonato2= new Ticket("test","xxx","aaa");
		sesion.save(campeonato2);

		
		
		sesion.getTransaction().commit();
		System.out.println("Done");		
			
		
	}
}
