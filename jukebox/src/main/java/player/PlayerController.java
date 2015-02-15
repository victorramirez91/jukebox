package player;

import java.util.ArrayList;
import java.util.List;

import jukebox.IndexSongs;

public class PlayerController {

	Player player;
	IndexSongs index;
	public static List<String> playlist = new ArrayList<String>();
	static String folder = IndexSongs.sDirectorio;
	private static PlayerController instance;
	public static int playlistlongitude, currentsong = 0;

	public static synchronized PlayerController getInstance() {
		if (instance == null) {
			System.out.println("ES null");
			instance = new PlayerController();
		}
		return instance;
	}

	private PlayerController() {
		index = IndexSongs.getInstance();
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		playlist = index.GetSongsName();
		playlistlongitude = playlist.size();
		//player = Player.getInstance();
		player = new Player();

	}

	public List<String> getPlayList() {
		return playlist;
	}

	public void nextSong(String event) {
		if (event.equals("STOPPED:-1") && currentsong < playlistlongitude) {
			player.play(folder + playlist.get(currentsong));
			currentsong++;
		}
	}

	public String initalizePlayer() {

		player.play(folder + playlist.get(playlistlongitude -1));
		currentsong++;
		return null;
	}

	public String addSongToPlayList(String nwsong) {
		playlist.add(nwsong);
		playlistlongitude++;
		return null;
	}
}
