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

import objects.Song;
import objects.Ticket;
import objects.TrackMaped;
import player.PlayerController;
import jukebox.IndexSongs;
import jukebox.JukeboxLocalImp;
import jukebox.JukeboxSpotifyImpl;
import spotify.SpotifyOperations;
import clases.TicketGen;

import com.google.gson.Gson;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import dboperations.DBOperations;

@Path("/api")
public class ApiJukebox {

	List<Track> result_s = new ArrayList<Track>();
	SpotifyOperations op = SpotifyOperations.getInstance();
	DBOperations opdb;
	
	//Se debe indicar cuál es la implementacion!
	//JukeboxSpotifyImpl jukebox = new JukeboxSpotifyImpl();
	JukeboxLocalImp jukebox = JukeboxLocalImp.getInstance();

	@GET
	@Path("/prova/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "Jersey say : " + msg;

		return Response.status(200).entity(output).build();

	}


	@GET
	@Path("/getticket")
	public String getTicket(@Context HttpServletRequest request) {

		
		Ticket tiq = new Ticket();
		TicketGen gen = new TicketGen();
		try {
			tiq = gen.genticket();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonserver = gson.toJson(tiq);
		System.out.println(jsonserver);
		return jsonserver;

	}

	@GET
	@Path("/getsongs")
	public String getSongs(@Context HttpServletRequest request) {

		System.out.println("vamos a pedir las canciones disponibles");

		ArrayList<Song> availablesgs= jukebox.getSongs();
		System.out.println(request.getRemoteAddr());
		// List<String> names = index.GetSongsName();
		Gson Gs = new Gson();
		String json_available = Gs.toJson(availablesgs);
		System.out.println(json_available);
		return json_available;
		

	}
	
	
	@GET
	@Path("/getplaylist")
	public String getPlaylist(@Context HttpServletRequest request) {
		int cPosition = PlayerController.currentsong;
		
		try {
			
			ArrayList<Song> playlistsgs= jukebox.getPlaylist();
			
			// List<String> names = index.GetSongsName();
			if(playlistsgs != null){
			Gson Gs = new Gson();
			String json_available = Gs.toJson(playlistsgs);
			System.out.println(json_available);
			
			return json_available;}
			
		} catch (Exception e) {
			return "Lista de reproduccion vacia...";
		}
		return null;
		
		
			
		

	}

	@GET
	@Path("/queque_song/{idsong}/{key}")
	public String queque_song(@PathParam("idsong") String idsong,
			@PathParam("key") String key, @Context HttpServletRequest request)
			throws IOException, WebApiException {
		
		
		
		
		
		
		System.out.println("LA IP QUE ESTA INTRODUCIENDO LA CLAVE ES:  "+request.getRemoteAddr());
		opdb = DBOperations.getInstance();
		
		
		int ipState= opdb.checkIp(request.getRemoteAddr());
		
		if(ipState==DBOperations.IPCLIENT_BANNED)
		{
			return "Has intentado mas de 10 veces una clave erronea";
		}
		
		else{
		
		
		int checking = opdb.checkTicket(key);
		if (checking == DBOperations.TICKET_ERR_NOT_FOUND) {
			System.out.println("Ticket incorrecto");
			opdb.addtryClient(request.getRemoteAddr());
			return "Incorrecto";
		}
		if (checking == DBOperations.TICKET_OK) {
			System.out.println("EL ticket es correcto");
			int response = jukebox.addToPlaylist(idsong);
			System.out.println(response);
			// Se enviara a la playList....
			return "El ticket es correcto:" + response;
		}
		if (checking == DBOperations.TICKET_ERR_EXPIRED) {
			System.out.println("El tiquet ha caducado");

			return "El tiquet ha caducado";
		}
		if (checking == DBOperations.TICKET_ERR_ALREADY_USED) {
			System.out.println("El tiquet ya se ha usado");

			return "El tiquet ya se ha usado";
		} else
			return "error";
		}
	}

	@GET
	@Path("/search_song/{search_s}")
	public String search_song(@PathParam("search_s") String search_s,

	@Context HttpServletRequest request) {
		System.out.println("1");
		System.out.println("VAMOS A BUSCAR: " + search_s);
		ArrayList<Song> tracks = new ArrayList<Song>();
		tracks = jukebox.search(search_s);
		if (tracks == null)
		{
			return "Lo Sentimos, no hay coincidencias.";
		}
		Gson gson = new Gson();
		String json_result_s = gson.toJson(tracks);
		System.out.println(json_result_s);
		return json_result_s;

//			System.out.println("2");
//			// SpotifyOperations op = SpotifyOperations.getInstance();
//			System.out.println("4");
//			result_s = op.searchTrack(search_s);
//
//			int i = 0;
//			System.out.println("HOLAAAAAA:   "
//					+ result_s.get(1).getArtists().get(0).getName());
//			List<TrackMaped> traks = new ArrayList<TrackMaped>();
//			while (i < result_s.size()) {
//				result_s.get(i).getAlbum().getImages().get(0);
//				TrackMaped map = new TrackMaped(result_s.get(i).getArtists()
//						.get(0).getName(), result_s.get(i).getName(),
//						Integer.toString(result_s.get(i).getDuration()),
//						result_s.get(i).getId(), result_s.get(i).getAlbum()
//								.getImages().get(0).getUrl());
//				traks.add(map);
//				i++;
//			}
			

		
			
			
		

	}
//	@GET
//	@Path("/getplaylist")
//	public String getCurrentPlaylist(@PathParam("song") String song,
//
//	@Context HttpServletRequest request) throws IOException, WebApiException {
//
//		
//
//		return "HELLO";
//	}
	

	@GET
	@Path("/insertSpoti_song/{song}")
	public String insertSpoti_song(@PathParam("song") String song,

	@Context HttpServletRequest request) throws IOException, WebApiException {

		System.out.println("se quiere añadir: " + song);
		//String respuesta = op.addSong(song);
		//System.out.println(respuesta);

		return null;
	}

	@GET
	@Path("/getSpoti_song/{gtrack}")
	public String getSpoti_song(@PathParam("gtrack") String gtrack,

	@Context HttpServletRequest request) throws IOException, WebApiException {
		Track respuesta = op.getTrack(gtrack);

		System.out.println("EN API: " + respuesta.getName());
		TrackMaped map2 = new TrackMaped(respuesta.getArtists().get(0)
				.getName(), respuesta.getName(), Integer.toString(respuesta
				.getDuration()), respuesta.getId(), respuesta.getAlbum()
				.getImages().get(0).getUrl());

		Gson gson2 = new Gson();
		String json_result_s2 = gson2.toJson(map2);
		System.out.println(json_result_s2);
		return json_result_s2;

	}
	
	
	@GET
	@Path("/get_song/{gtrack}")
	public String get_Song(@PathParam("gtrack") String gtrack,

	@Context HttpServletRequest request) throws IOException, WebApiException {
		
		System.out.println("EN API, QUEREMOS OBTENER CANCION CON ID:"+gtrack);
		Song songresponse = jukebox.getSong(gtrack);
		System.out.println("EN API OBTENEMOS :"+songresponse.getName());

		Gson gson2 = new Gson();
		String json_result_s2 = gson2.toJson(songresponse);
		System.out.println(json_result_s2);
		return json_result_s2;

	}

}
