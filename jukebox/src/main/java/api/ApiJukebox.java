package api;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;











import objects.Ticket;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import player.Test;
import player.pruebaplayer;
import clases.Jsonticket;
import clases.TicketGen;










//import com.eetac.pycto.managers.ServerBallotBox;
//import com.eetac.pycto.managers.ServerCACR;
//import com.eetac.pycto.models.CA_CR;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/api")
public class ApiJukebox {
	
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C:/xampp/htdocs/images/";

		@GET
		@Path("/prova/{param}")
		public Response getMsg(@PathParam("param") String msg) {
	 
			String output = "Jersey say : " + msg;
	 
			return Response.status(200).entity(output).build();
	   
		}
//		@GET
//		@Path("/getticket")
//		public Response getMsg2()   {
//			System.out.println("getticket");
//			Test test = new Test();
//			String x=test.PlayerOn();
//			return Response.status(200).entity("").build();
//	   
//		}
		@GET
		@Path("/getticket")
		public String getTicket(@Context HttpServletRequest request ) {
			
			System.out.println("getticket");
			Ticket tiq = new Ticket();
			TicketGen gen = new TicketGen();
			try {
				tiq=gen.genticket();
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
			Gson gson = new Gson();		
			  String jsonserver =gson.toJson(tiq);
			System.out.println(jsonserver);
				return jsonserver;

	   
	    	
	    }	
		
		
}
