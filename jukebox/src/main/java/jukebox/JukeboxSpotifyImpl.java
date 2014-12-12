package jukebox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Track;

import objects.Song;
import objects.TrackMaped;
import spotify.SpotifyOperations;

public class JukeboxSpotifyImpl implements Jukebox {
	SpotifyOperations so;
	public static int INITIAL_VALUE = -2;

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
		List<Track> tracksArray = new ArrayList<Track>();
		tracksArray =so.searchTrack(term);
		ArrayList<Song> responseSongs = new ArrayList<Song>();
		int i =0;
		while (i < tracksArray.size()) {
				Song s = new Song();
				Track tracktemp = tracksArray.get(i);
				s.setAlbum(tracktemp.getAlbum().getName());
				s.setArtist(tracktemp.getArtists().get(0).getName());
				s.setDuration(Integer.toString(tracktemp.getDuration()));
				s.setGenre("no info");
				s.setId(tracktemp.getId());
				s.setName(tracktemp.getName());
				s.setImage(tracktemp.getAlbum().getImages().get(0).getUrl());
				responseSongs.add(s);
				i++;
			}
		
		return responseSongs;
	}

	public int addToPlaylist(String songId) {
		int state =INITIAL_VALUE;
		System.out.println("Queremos añadir:" +songId);
		Track respuestapet = so.getTrack(songId);
		try {
			state = so.addSong(respuestapet.getUri());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WebApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return state;
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
