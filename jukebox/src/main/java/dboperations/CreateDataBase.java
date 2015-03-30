package dboperations;

import objects.ClientIP;
import objects.Folder;
import objects.Song;
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
		
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Ticket.class);
		config.addAnnotatedClass(ClientIP.class);
		config.addAnnotatedClass(Folder.class);
		config.addAnnotatedClass(Song.class);
		config.configure();
		new SchemaExport (config).create(true, true);
		
		//hibernate session
		SessionFactory factory = config.buildSessionFactory();
		Session sesion = factory.getCurrentSession();
		sesion.beginTransaction();		
		Ticket campeonato2= new Ticket("test2","xxx2","aaa2",true);
		sesion.save(campeonato2);
		ClientIP ci = new ClientIP("8.8.8.8", 0, "xxxxxx2014", false);
		sesion.save(ci);
		Folder fd = new Folder();
		fd.setId("1");
		sesion.save(fd);
		Song sg = new Song();
		sg.setId("ONE");
		sesion.save(sg);

		
		
		sesion.getTransaction().commit();
		System.out.println("Done");		
			
		
	}
}
