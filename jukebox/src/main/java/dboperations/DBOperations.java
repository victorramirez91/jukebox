package dboperations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.Query;

import objects.ClientIP;
import objects.Folder;
import objects.Song;
import objects.Ticket;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import spotify.SpotifyOperations;

public class DBOperations {

	public static int TICKET_OK = 0;
	public static int TICKET_ERR_NOT_FOUND = -1;
	public static int TICKET_ERR_EXPIRED = -2;
	public static int TICKET_ERR_ALREADY_USED = -3;
	public static int IPCLIENT_BANNED = -1;
	public static int IPCLIENT_OK = 0;

	private AnnotationConfiguration config;
	private SessionFactory factory;
	private static DBOperations instance = null;

	public static DBOperations getInstance() {
		if (instance == null) {
			instance = new DBOperations();
		}

		return instance;
	}

	private DBOperations() {
		super();
		config = new AnnotationConfiguration();
		config.addAnnotatedClass(Ticket.class);
		config.addAnnotatedClass(ClientIP.class);
		config.addAnnotatedClass(Folder.class);
		config.addAnnotatedClass(Song.class);
		config.configure();
		factory = config.buildSessionFactory();

	}

	public void addTicket(Ticket ticket) {
		// añade ticket a la BBDD

		ticket.setUsed(false);
		Session sesion = factory.openSession();
		System.out.println("addticket");
		sesion.beginTransaction();
		sesion.save(ticket);
		sesion.getTransaction().commit();
		System.out.println("Ticket saved correctly.");
		sesion.close();

	}

	public String saveSongstoDB(List<Song> lis)
	{
		int i =0;
		Session sesion = factory.openSession();
		
		SQLQuery query = sesion.createSQLQuery("DELETE FROM song WHERE 1");
		
		query.executeUpdate();

		//sesion.getTransaction().commit();
		
		
		
		System.out.println("Despues de la query");
	
		
		sesion.beginTransaction();
		while (i<lis.size())
		{
			sesion.save(lis.get(i));
			i++;
		}
		
		sesion.getTransaction().commit();
		System.out.println("Ticket saved correctly.");
		sesion.close();
		
		return null;
	}
	
	public ArrayList<Song> getSongsfromDB()
	{
		Session sesion = factory.openSession();
		
		SQLQuery query = sesion.createSQLQuery("SELECT * FROM song ");
		query.addEntity(Song.class);
		 List<Song> listDatos = query.list();
//		 for (Song datos : listDatos) {
//		    System.out.println(datos.getAlbum() + "--" + datos.getArtist());
//		 }
		 System.out.println("DEVUELVE UNA LISTA DE "+listDatos.size()+"ELEMENTOS ");
		ArrayList<Song> responsesongs =  new ArrayList<Song>(listDatos);
		return responsesongs;
	}
	public ArrayList<Song> searchSong(String busquedadb)
	{
		Session sesion = factory.openSession();
		
		SQLQuery query = sesion.createSQLQuery("SELECT * FROM song WHERE id LIKE "+"'%"+busquedadb+"%'"+" or Artist LIKE "+"'%"+busquedadb+"%'"+" or Name LIKE "+"'%"+busquedadb+"%'"+" or album LIKE "+"'%"+busquedadb+"%'");
		query.addEntity(Song.class);
		 List<Song> listDatos = query.list();
//		 for (Song datos : listDatos) {
//		    System.out.println(datos.getAlbum() + "--" + datos.getArtist());
//		 }
		 System.out.println("DEVUELVE UNA LISTA DE "+listDatos.size()+"ELEMENTOS ");
		ArrayList<Song> responsesongs =  new ArrayList<Song>(listDatos);
		return responsesongs;
	}
	
	public boolean checkFolderModified(String lastmod) {
		
		System.out.println(lastmod);

		Session sesion = factory.openSession();
		System.out.println("checkticket");
		SQLQuery query = sesion.createSQLQuery("SELECT * FROM folder");
		System.out.println("Despues de la query");
		query.addEntity(Folder.class);
		sesion.beginTransaction();
		Folder fold = (Folder) query.uniqueResult();
		System.out.println("Esta es la respuesta" +fold.getLastmodified());
		//sesion.getTransaction().commit();
		System.out.println("¿ " +fold.getLastmodified()+"ES IGUAL A  " +lastmod);
		if(fold.getLastmodified().equals(lastmod) || lastmod==null)
		{
			System.out.println("ES IGUAL");
			sesion.close();
			return false;
		}
		if(fold.getLastmodified()==null)
		{
			System.out.println("NO ES IGUAL");
			fold.setLastmodified(lastmod);
			System.out.println("LE PONEMOS ESTA LASTMOD  "+fold.getLastmodified());
			
			sesion.update(fold);
			
			sesion.getTransaction().commit();
			sesion.close();
			return true;
		}
		
		if(!fold.getLastmodified().equals(lastmod))
		{
			System.out.println("NO ES IGUAL");
			fold.setLastmodified(lastmod);
			System.out.println("LE PONEMOS ESTA LASTMOD  "+fold.getLastmodified());
			
			sesion.update(fold);
			
			sesion.getTransaction().commit();
			sesion.close();
			return true;
			
		}
		else 
			return true;
		
		

	}
	
	
	
	
	
	public int checkTicket(String key) {
		// chequea si la clave esta en la base de datos

		// 0---> NO existe
		// 1---> Valido
		// 2----> Caducado
		// 3----> Tiquet usado
		System.out.println(key);

		Session sesion = factory.openSession();
		System.out.println("checkticket");

		SQLQuery query = sesion
				.createSQLQuery("SELECT * FROM ticket WHERE idticket = :key");

		query.addEntity(Ticket.class);
		query.setString("key", key);
		sesion.beginTransaction();
		Ticket ticket = (Ticket) query.uniqueResult();
		sesion.getTransaction().commit();
		sesion.close();

		if (ticket == null) {
			System.out.println("No existe el ticket");
			return TICKET_ERR_NOT_FOUND;
		}

		if (ticket.getUsed() == true) {

			return TICKET_ERR_ALREADY_USED;
		}

		if (ticket.getUsed() == false)

		{
			System.out.println("Existe");
			String timestamp = ticket.getDate();
			// String text = "2011-10-02 18:48:05.123456";
			Timestamp ts = Timestamp.valueOf(timestamp);
			java.util.Date date = new Date();
			System.out.println(date);
			long lnMilisegundos = date.getTime();
			java.sql.Timestamp sqlTimestamp2 = new java.sql.Timestamp(
					lnMilisegundos);
			float diff = sqlTimestamp2.getTime() - ts.getTime();
			System.out.println(diff);
			float hora = diff / 3600000;
			System.out.println("Horas" + hora);
			// Borrar con intervalos:
			// delete from ticket where date > (DATE_FORMAT(DATE_ADD(NOW(),
			// INTERVAL 10 DAY),'%Y-%m-%d'))
			if (hora < 0.5) {
				System.out.println("No ha pasado media hora");
				Session sesion2 = factory.openSession();
				ticket.setUsed(true);
				sesion2.beginTransaction();
				sesion2.update(ticket);
				sesion2.getTransaction().commit();
				System.out.println("Ticket saved correctly.");
				sesion2.close();
				return TICKET_OK;
			} else {
				System.out.println("Ha pasado media hora");
				return TICKET_ERR_EXPIRED;
			}

		}
		return 0;

	}

	public int checkIp(String ip) {
		System.out.println("EN DB: COMPROVAMOS IP"+ip);
		Session sesion = factory.openSession();
		System.out.println("check ip");

		SQLQuery query = sesion
				.createSQLQuery("SELECT * FROM clientip WHERE ip ='"+ip+"'");

		
		//query.setParameter("ip", ip);
		System.out.println("ESTA ES LA QUERY : "+ query.toString());
		query.addEntity(ClientIP.class);
		sesion.beginTransaction();
		ClientIP cip = (ClientIP) query.uniqueResult();
		
		sesion.getTransaction().commit();
		sesion.close();
		if(cip==null)
		{
			System.out.println("EN DB: NO ESTA REGISTRADO EN LA BBDD");
			return IPCLIENT_OK;
			
		}
		if (cip.isBan() == false) {
			System.out.println("EN DB: IPCLIENT_OK");
			return IPCLIENT_OK;
		} else {
			System.out.println("EN DB: IPCLIENT_KO");
			return IPCLIENT_BANNED;
		}
		
	}

	public int addtryClient(String ip) {

		Session sesion = factory.openSession();
		System.out.println("check ip");
		ClientIP cip=null;
		SQLQuery query = sesion
				.createSQLQuery("SELECT * FROM clientip WHERE ip ='"+ip+"'");

		query.addEntity(ClientIP.class);
		//query.setParameter("ip", ip);
		sesion.beginTransaction();
		 cip = (ClientIP) query.uniqueResult();
		if(cip==null)
		{
			System.out.println("ES nulo");
			ClientIP cip2= new ClientIP(ip, 1, "", false);
			sesion.save(cip2);
			sesion.getTransaction().commit();
			sesion.close();
			return 0;
			
		}
		else{
		int tryC=cip.getCounter();
		if (tryC<10)
		{
			tryC++;
			cip.setCounter(tryC);
		}
		if(tryC==10)
		{
			cip.setBan(true);
		}
		sesion.update(cip);
		sesion.getTransaction().commit();
		sesion.close();
		return 0;
		}
		
		

	}

}
