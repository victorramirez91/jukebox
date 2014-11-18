package jukebox;

import java.io.IOException;
import java.util.ArrayList;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import objects.Song;

public class JukeboxLocalImp implements Jukebox {
	ArrayList<Song> availableSongs;

	public ArrayList<Song> getSongs() {
		IndexSongs is = new IndexSongs();
		try {
			availableSongs = is.getSongsObject();
		} catch (UnsupportedTagException e) {

			e.printStackTrace();
		} catch (InvalidDataException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return availableSongs;
	}

	public ArrayList<Song> getPlaylist() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Song> search(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	public int addToPlaylist(String songId, String code) {
		// TODO Auto-generated method stub
		
		
		return 0;
	}

	public Song getSong(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
