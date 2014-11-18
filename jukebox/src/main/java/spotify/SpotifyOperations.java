package spotify;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AddTrackToPlaylistRequest;
import com.wrapper.spotify.methods.TrackRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.RefreshAccessTokenCredentials;
import com.wrapper.spotify.models.Track;

public class SpotifyOperations {
	
	
	private static SpotifyOperations instance= null;
	private SpotifyOperations(){
	}
	public static SpotifyOperations getInstance(){
		if( instance == null ) {
			instance = new SpotifyOperations();
		}
			
		return instance;
	}
	

	final static String clientId = "dd3d4e7aa058411e8d236c37b64d0111";
    final static String clientSecret = "dcfed4ca09394611afae7d7286984d8a";
    final static String redirectUri = "http://localhost:8888";
    final static String refreshToken = "AQCQXUjJ_hKt6GEmNpelhYbqzzeBCEDsi5OsjN2gSg8heQntv9c35feaFa9dnC63K96fq65n3LcK6oXBCrySrd4x2Pzcy_U7nofs-Og_5iTY2AlgnXnrRRlduLWnlrWehO4";
	//Con el refresh token vamos a poder obtener nuevos token para acceder al usuario
    
    final Api api = Api.builder()
			  .clientId(clientId)
			  .clientSecret(clientSecret)
			  .redirectURI(redirectUri)
			  .refreshToken(refreshToken)
			  .build();
    
    public String addSong(String track) throws IOException, WebApiException
    {
    	
    	 final RefreshAccessTokenRequest request = api
 	            .refreshAccessToken()
 	            
 	            .build();
 	 RefreshAccessTokenCredentials refreshAccessTokenResponse = request.get();
 	
 	 
 	  System.out.println(refreshAccessTokenResponse.getExpiresIn());;
 	  System.out.println(refreshAccessTokenResponse.getAccessToken());
 	  System.out.println(refreshAccessTokenResponse.getTokenType());
 	 
 	 
 	api.setAccessToken(refreshAccessTokenResponse.getAccessToken());
 	
	   
 	
 	final List<String> tracksToAdd = Arrays.asList(track);

 	// Index starts at 0
 	final int insertIndex = 0;

 	final AddTrackToPlaylistRequest request2 = api.addTracksToPlaylist("errezeta1", "68a1ualmfcXujcZYkjYMCD", tracksToAdd).position(-1).build();

 	try {	
 		
 	  request2.get(); // Empty response
 	 return track+"  Añadida";
 	} catch (Exception e) {
 	   System.out.println("Something went wrong!" + e.getMessage());
 	   return "No se ha podido añadir";
 	}
 	
    	
    }
    public List<Track> searchTrack(String search)
    {
    	
    	System.out.println("3");
    	final TrackSearchRequest request = api.searchTracks(search).build();

    	try {
    	   final Page<Track> trackSearchResult = request.get();
    	   System.out.println("I got " + trackSearchResult.getTotal() + " results!");
    	   //umero maximo de resultados
    	  // trackSearchResult.setLimit(200);
    	   return trackSearchResult.getItems();
    	   
    	} catch (Exception e) {
    	   System.out.println("Something went wrong!" + e.getMessage());
    	   return null;
    	}
		
    }
    
    public Track getTrack(String gtrack)
    {
    	
    	System.out.println("3");
    	System.out.println("En operaciones buscamos id: "+gtrack);
    	
    	final TrackRequest request = api.getTrack(gtrack).build();
    	try {
    		  Track track = request.get();
    		  
    		  System.out.println("La respuesta en operaciones es: "+track.getName());
    		 return track;
    	   
    	} catch (Exception e) {
    	   System.out.println("Something went wrong!" + e.getMessage());
    	   return null;
    	}
		
    }

}
