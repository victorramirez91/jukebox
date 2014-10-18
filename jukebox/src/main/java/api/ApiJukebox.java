package api;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;























import objects.Ticket;
import objects.TrackMaped;
import jukebox.IndexSongs;
import spotify.SpotifyOperations;
import clases.TicketGen;






















//import com.eetac.pycto.managers.ServerBallotBox;
//import com.eetac.pycto.managers.ServerCACR;
//import com.eetac.pycto.models.CA_CR;
import com.google.gson.Gson;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import dboperations.DBOperations;


@Path("/api")
public class ApiJukebox {
	
	List<Track> result_s = new ArrayList<Track>();
	SpotifyOperations op = SpotifyOperations.getInstance();
	
	

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
		
		@GET
		@Path("/search_song/{search_s}")
		public String search_song(
				@PathParam("search_s") String search_s,
				
				@Context HttpServletRequest request)  {
			System.out.println("1");
			System.out.println("VAMOS A BUSCAR: "+search_s);
			try {
				
			System.out.println("2");
//			SpotifyOperations op = SpotifyOperations.getInstance();
			System.out.println("4");
			result_s= op.searchTrack(search_s);
			
			int i = 0;
			System.out.println("HOLAAAAAA:   "+result_s.get(1).getArtists().get(0).getName());
			List <TrackMaped> traks = new ArrayList<TrackMaped>();
			while (i < result_s.size())
			{
				result_s.get(i).getAlbum().getImages().get(0);
				TrackMaped map = new TrackMaped(result_s.get(i).getArtists().get(0).getName(), result_s.get(i).getName(), Integer.toString(result_s.get(i).getDuration()), result_s.get(i).getId(), result_s.get(i).getAlbum().getImages().get(0).getUrl());
				traks.add(map);
				i++;
			}
			
			
			
			Gson gson = new Gson();		
			  String json_result_s =gson.toJson(traks);
			System.out.println(json_result_s);
			return json_result_s;
			
		    	   
		    	   
		    	} catch (Exception e) {
		    	   System.out.println("Something went wrong!");
		    	   return "error";
		    	}
			
			
			
			
		}
		
		
		
		
		
		@GET
		@Path("/insertSpoti_song/{song}")
		public String insertSpoti_song(
				@PathParam("song") String song,
				
				@Context HttpServletRequest request) throws IOException, WebApiException  {
				String respuesta =op.addSong(song);
				System.out.println(respuesta);
			
			
			return null;
		}
		
		
		
		
		
		
		
}
