package jukebox;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import objects.Song;

public interface Jukebox {
	
	public ArrayList<Song> getSongs();

	public ArrayList<Song> getPlaylist();
	
	public ArrayList<Song> search(String term);
	
	public Song getSong(String id);
	
	public int addToPlaylist(String songId, String code);
}
