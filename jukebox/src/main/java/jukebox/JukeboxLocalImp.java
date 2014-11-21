package jukebox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import objects.Song;
import player.Player;
import player.PlayerController;

public class JukeboxLocalImp implements Jukebox {
	ArrayList<Song> availableSongs=null;
	 
	static JukeboxLocalImp instance;
	PlayerController playerc;
	public static JukeboxLocalImp getInstance(){
		if( instance == null ) {
			instance = new JukeboxLocalImp();
		}
			
		return instance;
	}
  private JukeboxLocalImp()
     {
	   playerc = PlayerController.getInstance();
      
     }
	public ArrayList<Song> getSongs() {
		IndexSongs is = IndexSongs.getInstance();
		try {
			
			if (availableSongs==null){
			availableSongs = is.getSongsObject();
			System.out.println(availableSongs.size());}
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

		
		List<String> plst = playerc.getPlayList();
		System.out.println("PLAYLIST SIZE"+plst.size());
		ArrayList<Song> playlstsngs = new ArrayList<Song>();
		int inc=0; 
		while(plst.size()>inc)
		{ 
			int inc2=0;
			while(availableSongs.size()>inc2)
			{ 
				
				if(plst.get(inc).equals(availableSongs.get(inc2).getId()))	
				{
					
					playlstsngs.add(availableSongs.get(inc2));
				}
				
				inc2++;
				
			}
			
			System.out.println(inc);
			inc++;
		}
		return playlstsngs;

		
		
	}

	public ArrayList<Song> search(String term) {
		ArrayList<Song> songssrch = new ArrayList<Song>();

		for (int x2 = 1; x2 < availableSongs.size(); x2++) {
			String cadena = availableSongs.get(x2).getName();
			int resultado = cadena.indexOf(term);
			if (resultado != -1) {
				
				songssrch.add(availableSongs.get(x2));
			}
		}
		return songssrch;

		
	}

	public int addToPlaylist(String songId) {

		String newsg = songId ;
		playerc.addSongToPlayList(newsg);

		return 0;
	}

	public Song getSong(String id) {

		String name = id;

		for (int x = 1; x < availableSongs.size(); x++) {
			if (availableSongs.get(x).getId().equals(name)) {
				System.out.println("ha encontrado la cancion");
				return availableSongs.get(x);
			}

		}
		return null;
	}

}
