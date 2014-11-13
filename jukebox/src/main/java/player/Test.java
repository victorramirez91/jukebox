package player;

import java.util.ArrayList;
import java.util.List;

import jukebox.IndexSongs;

public class Test {

	static int track, longitude = 0;
	static Player player = Player.getInstance();
	static IndexSongs index = new IndexSongs();
	static List<String> songslist2 = new ArrayList<String>();
	static String folder = IndexSongs.sDirectorio;

	// String folder = "C:/Users/Victorz/jukeboxsongs/songsshort";
	// static String folder = null;
	public String netxSong(String event)

	{

		if (event.equals("STOPPED:-1") && track < longitude - 1) {
			track++;
			System.out.println(track);
			playing(track);
			return null;
		}

		if (track == longitude - 1)
			System.out.println("Lista acabada");
		return null;
	}

	public String playing(int trk)

	{

		System.out.println("estamos en playing");
		player.play(folder + songslist2.get(track));
		return "";
	}

	public static void main(String[] args) {

		
		songslist2 = index.GetSongsName();
		longitude = songslist2.size();
		player.play(folder + songslist2.get(0));

	}

}
