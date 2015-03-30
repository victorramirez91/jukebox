package api;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import javazoom.jl.decoder.JavaLayerException;
import objects.Song;
import objects.Ticket;
import objects.TrackMaped;
import objects.UserAdmin;
import player.PlayerController;
import player.TestController;
import jukebox.IndexSongs;
import jukebox.JukeboxLocalImp;
import jukebox.JukeboxSpotifyImpl;
import spotify.SpotifyOperations;
import clases.TicketGen;

import com.google.gson.Gson;
import com.wrapper.spotify.exceptions.BadRequestException;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import dboperations.DBOperations;

@Path("/api")
public class ApiJukebox {
	
	List<Track> result_s = new ArrayList<Track>();
	SpotifyOperations op = SpotifyOperations.getInstance();
	DBOperations opdb;
	String clave = "abcdefgg";

	//Se debe indicar cuál es la implementacion!
	//JukeboxSpotifyImpl jukebox = new JukeboxSpotifyImpl();
	JukeboxLocalImp jukebox = JukeboxLocalImp.getInstance();

	@Path("/login/{username}/{password}")
	@GET
	
	public String login(@PathParam("username") String username, @PathParam("password") String password) throws BadRequestException {
		System.out.println("Entramos al metodo");
		byte[] key = new byte[64];
		new SecureRandom().nextBytes(key);
		System.out.println(key);
		
		String compact="";
		if (username == null || password == null)
			throw new BadRequestException("username and password cannot be null.");

//		String pwdDigest = DigestUtils.md5Hex(UserAdmin.getUserpass()); //calculamos el md5 de la contraseña
//		String storedPwd = getUserFromDatabase(UserAdmin.getUsername(), true) //nos devuelve el pasword en md5 y en hexadecimal y se puede comparar con el de la base de datos y que sean iguales
//				.getUserpass();
			UserAdmin usr = new UserAdmin("", "");
		//UserAdmin.setLoginSuccessful(pwdDigest.equals(storedPwd)); //ponemos el atributo de login si es true o false si coinciden
		if (username.equals("admin") && password.equals("admin"))
	
		{	usr.setUsername(username);
			usr.setLoginSuccessful(true);
			java.util.Date date = new Date();
			System.out.println (date);
			 long lnMilisegundos = date.getTime();
			 long expiration = lnMilisegundos+30000;
			 Date expd = new Date(expiration);
			 compact = Jwts.builder().setExpiration(expd).setSubject("Joe").signWith(SignatureAlgorithm.HS256, clave).compact();
			 usr.setKey(compact);}
		else
			compact= "error login";
		Gson gson2 = new Gson();
		String json_key = gson2.toJson(usr);
		
		return json_key;
		
	}
	
	@Path("/checkJWT")
	@POST
	//@Consumes("application/json")	
	public String register(String data) {		
		//System.out.println(key);
//		byte[] key2 = new byte[64];
//		new SecureRandom().nextBytes(key2);
		System.out.println("esto es checking");
			try {
				System.out.println(1);
				Jwts.parser().setSigningKey(clave).parse(data);
				System.out.println(2);
				Date exp2 = Jwts.parser().setSigningKey(clave).parseClaimsJws(data).getBody().getExpiration();
				
				java.util.Date date2 = new Date();
				System.out.println("VAMOS A COMPROVAR QE LA EXP "+exp2+ "con la local "+date2);
				if(exp2.before(date2))
				{
					 return "ko";
				}
				else{
				Ticket tiq = new Ticket();
				TicketGen gen = new TicketGen();
				try {
					tiq = gen.genticket();
				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
				Gson gson = new Gson();
				String jsonservert = gson.toJson(tiq);
				System.out.println(jsonservert);
				return jsonservert;
				}
			} catch (Exception e) {
				
				return "ko";
			}
		    
			
		
	
 }
	
	
	@GET
	@Path("/play")
	public void getMsg(@PathParam("param") String msg) {
		PlayerController pc = PlayerController.getInstance();
		try {
			pc.initalizePlayer();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = "Jersey say : " + msg;

		//return Response.status(200).entity(output).build();

	}
	@GET
	@Path("/stop")
	public void getMsg2(@PathParam("param") String msg) {
		PlayerController pc = PlayerController.getInstance();
		
			pc.stopPlayer();
		
		String output = "Jersey say : " + msg;

		//return Response.status(200).entity(output).build();

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
			System.out.println(json_available);
			return json_available;}
			
		} catch (Exception e) {
			return "Lista de reproduccion vacia..."+e.toString();
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
		if (tracks.size() ==0)
		{
			System.out.println("API: NO HAN HABIDO COINCIDENCIAS");
			Song err = new Song();
			err.setId("error");
			tracks.add(err);
			Gson gson = new Gson();
			String json_result_s2 = gson.toJson(tracks);
			System.out.println(json_result_s2);
			return json_result_s2;
			
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
