package jukebox;

import java.util.ArrayList;

import com.wrapper.spotify.models.Track;

import objects.Song;
import spotify.SpotifyOperations;

public class JukeboxSpotifyImpl implements Jukebox {
	SpotifyOperations so;

	public JukeboxSpotifyImpl() {
		so = SpotifyOperations.getInstance();
	}

	public ArrayList<Song> getSongs() {
		// Not implemented
		return null;
	}

	public ArrayList<Song> getPlaylist() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Song> search(String term) {
		
		
		
		
		
		return null;
	}

	public int addToPlaylist(String songId, String code) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Song getSong(String id) {
		Track tk = new Track();
		Song sg = new Song();
		tk = so.getTrack(id);
		//Mapping track to song below
		
		
		sg.setAlbum(tk.getAlbum().getName());
		sg.setArtist(tk.getArtists().get(0).getName());
		sg.setDuration(Integer.toString(tk.getDuration()));
		System.out.println("TYPE????????"+tk.getAlbum().getType().name());
		sg.setGenre(tk.getAlbum().getType().name());
		sg.setId(tk.getId());
		sg.setImage(tk.getAlbum().getImages().get(0).getUrl());
		sg.setName(tk.getName());
		
		
		return sg;
	}

}
