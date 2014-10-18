package jukebox;

import java.util.ArrayList;

import objects.Song;

public interface Jukebox {

	
	public ArrayList<Song> GetPlayList();
	public ArrayList<Song> GetCurrentPlaylist();
	public Song SearchSong();
}
