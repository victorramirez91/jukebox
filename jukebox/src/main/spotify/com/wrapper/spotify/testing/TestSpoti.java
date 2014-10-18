package com.wrapper.spotify.testing;

import static junit.framework.Assert.assertNotNull;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.AddTrackToPlaylistRequest;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.methods.AlbumSearchRequest;
import com.wrapper.spotify.methods.PlaylistCreationRequest;
import com.wrapper.spotify.methods.TrackSearchRequest;
import com.wrapper.spotify.methods.UserPlaylistsRequest;
import com.wrapper.spotify.methods.authentication.RefreshAccessTokenRequest;
import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.AuthorizationCodeCredentials;
import com.wrapper.spotify.models.ClientCredentials;
import com.wrapper.spotify.models.Image;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Playlist;
import com.wrapper.spotify.models.RefreshAccessTokenCredentials;
import com.wrapper.spotify.models.SimpleAlbum;
import com.wrapper.spotify.models.SimplePlaylist;
import com.wrapper.spotify.models.Track;
import com.wrapper.spotify.models.User;

public class TestSpoti {
	
	final static String clientId = "dd3d4e7aa058411e8d236c37b64d0111";
    final static String clientSecret = "dcfed4ca09394611afae7d7286984d8a";
    final static String redirectUri = "http://localhost:8888";
    final static String refreshToken = "AQCQXUjJ_hKt6GEmNpelhYbqzzeBCEDsi5OsjN2gSg8heQntv9c35feaFa9dnC63K96fq65n3LcK6oXBCrySrd4x2Pzcy_U7nofs-Og_5iTY2AlgnXnrRRlduLWnlrWehO4";
    
    
    public static void main(String[] args) throws Exception{
    	
    	final Api api = Api.builder()
    			  .clientId(clientId)
    			  .clientSecret(clientSecret)
    			  .redirectURI(redirectUri)
    			  .refreshToken(refreshToken)
    			  
    			  .build();
    	
    	/* Application details necessary to get an access token */
    	
    	
    	 final RefreshAccessTokenRequest request = api
    	            .refreshAccessToken()
    	            
    	            .build();
    	 RefreshAccessTokenCredentials refreshAccessTokenResponse = request.get();
    	
    	 
    	  System.out.println(refreshAccessTokenResponse.getExpiresIn());;
    	  System.out.println(refreshAccessTokenResponse.getAccessToken());
    	  System.out.println(refreshAccessTokenResponse.getTokenType());
    	 
    	 
    	api.setAccessToken(refreshAccessTokenResponse.getAccessToken());
	   
    	
    	final List<String> tracksToAdd = Arrays.asList("spotify:track:2jdUkWbV3y6bxfl2TjLUWb","spotify:track:0BG2iE6McPhmAEKIhfqy1X");

    	// Index starts at 0
    	final int insertIndex = 0;

    	final AddTrackToPlaylistRequest request2 = api.addTracksToPlaylist("errezeta1", "68a1ualmfcXujcZYkjYMCD", tracksToAdd).position(-1).build();

    	try {
    	  request2.get(); // Empty response
    	} catch (Exception e) {
    	   System.out.println("Something went wrong!" + e.getMessage());
    	}
    	
    	
//    	
//
////    	/* Set the necessary scopes that the applicaiton will need from the user */
//   	final List<String> scopes = Arrays.asList("user-read-private", "user-read-email", "playlist-modify-private", "playlist-read-private");
////
////    	/* Set a state. This is used to prevent cross site request forgeries. */
//   	final String state = "someExpectedStateString";
////
//    	String authorizeURL = api.createAuthorizeURL(scopes, state);
//   	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + authorizeURL);
    	 //final String code = "AQBehaGz6qQA2lfpCwj_18YPq47Ikdfi2BOvu1HPWh4CxwS6wRo3lduympy34tKKdDE_vBc9t_APnVymbALdU8AUXEbbNTna_5wusiyAANRoCJed4iDT1etCD52FdjU_7d-f1mVdScUUw3GHj5VV40luU6Yht4ywV1b1q7mKkFT1aPMG9VtXJVjixvvKf6Kgh3gwR51gtsfMF0Z14moFG4og03dpiTpZoHguAxtCmaNTiEenig1QYPBS09-InzcdRTbytAO9Je7dbaXy2oK0cFppQL5_9Z1fnm1G26UsEw";
    	/* Continue by sending the user to the authorizeURL, which will look something like
    	   https://accounts.spotify.com:443/authorize?client_id=5fe01282e44241328a84e7c5cc169165&response_type=code&redirect_uri=https://example.com/callback&scope=user-read-private%20user-read-email&state=some-state-of-my-choice
    	 */
    	//-----------------------------------------------
    	/* Make a token request. Asynchronous requests are made with the .getAsync method and synchronous requests
    	 * are made with the .get method. This holds for all type of requests. */
    	//final SettableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = api.authorizationCodeGrant(code).build().getAsync();

    	/* Add callbacks to handle success and failure */
    	//Futures.addCallback(authorizationCodeCredentialsFuture, new FutureCallback<AuthorizationCodeCredentials>() {
    	  //@Override
//    	  public void onSuccess(AuthorizationCodeCredentials authorizationCodeCredentials) {
//    	    /* The tokens were retrieved successfully! */
//    	    System.out.println("Successfully retrieved an access token! " + authorizationCodeCredentials.getAccessToken());
//    	    System.out.println("The access token expires in " + authorizationCodeCredentials.getExpiresIn() + " seconds");
//    	    System.out.println("Luckily, I can refresh it using this refresh token! " +     authorizationCodeCredentials.getRefreshToken());
//
//    	    /* Set the access token and refresh token so that they are used whenever needed */
//    	    api.setAccessToken(authorizationCodeCredentials.getAccessToken());
//    	    api.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
//    	  }
    	  

    	 // @Override
//    	  public void onFailure(Throwable throwable) {
//    		  System.out.println("fallo");
//    	    /* Let's say that the client id is invalid, or the code has been used more than once,
//    	     * the request will fail. Why it fails is written in the throwable's message. */
//
//    	  }
//    	});
//    	final PlaylistCreationRequest request = api.createPlaylist("errezeta1","title")
//    			  .publicAccess(true)
//    			  .build();
//
//    			try {
//    			  final Playlist playlist = request.get();
//
//    			  System.out.println("You just created this playlist!");
//    			  System.out.println("Its title is " + playlist.getName());
//    			} catch (Exception e) {
//    			   System.out.println("Something went wrong!" + e.getMessage());
//    			}
//    	
//    }
    
    }
}