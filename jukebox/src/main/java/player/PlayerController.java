package player;

import java.util.ArrayList;
import java.util.List;

import jukebox.IndexSongs;

public class PlayerController {

	static Player player =Player.getInstance();
	static IndexSongs index ;
	static List<String> playlist = new ArrayList<String>();
	static String folder = IndexSongs.sDirectorio;
	static int playlistlongitude,currentsong =0;
	
	public PlayerController() {
		
		index = new IndexSongs();
		
		playlist = index.GetSongsName();
		playlistlongitude = playlist.size();
		
	}
	
	public void nextSong(String event)
	{
		if(event.equals("STOPPED:-1") && currentsong<playlistlongitude )
		{
			player.play(folder+playlist.get(currentsong));
			currentsong++;
		}
	}
	
	public String initalizePlayer()
	{
		
		player.play(folder+playlist.get(0));
		currentsong++;
		return null;
	}

	
	public String addSongToPlayList(String nwsong)
	{
		playlist.add(nwsong);
		playlistlongitude++;
		return null;
	}
}
