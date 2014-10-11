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
import jukebox.IndexSongs;
import player.Test;

import clases.Jsonticket;
import clases.TicketGen;














//import com.eetac.pycto.managers.ServerBallotBox;
//import com.eetac.pycto.managers.ServerCACR;
//import com.eetac.pycto.models.CA_CR;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import dboperations.DBOperations;


@Path("/api")
public class ApiJukebox {
	
	

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
		
		@GET
		@Path("/getsongs")
		public String getSongs(@Context HttpServletRequest request ) {
			
			System.out.println("getticket");
			
			IndexSongs index = new IndexSongs();
			
			//List<String> names = index.GetSongsName();
			String a = index.getSongsJson();
			return a;

	   
	    	
	    }	
		@GET
		@Path("/queque_song/{idsong}/{key}")
		public String queque_song(
				@PathParam("idsong") String idsong,
				@PathParam("key") String key,
				@Context HttpServletRequest request) {
			
		   DBOperations op= new DBOperations();
		   int checking = op.checkTicket(key);
		   if(checking == 0)
		   {
			   System.out.println("Ticket incorrecto");
			   
			   //Se enviara a la playList....
			   return "Incorrecto";
		   }
		   if(checking == 1)
		   {
			   System.out.println("EL ticket es correcto");
			   
			   //Se enviara a la playList....
			   return "El ticket es correcto";
		   }
		   if(checking == 2)
		   {
			   System.out.println("El tiquet ha caducado");
			   
			   //Se enviara a la playList....
			   return "El tiquet ha caducado";
		   }
		   else 
			   return "error";
		    
			
		}
		
		
}
