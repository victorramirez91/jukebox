package jukebox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import dboperations.DBOperations;
import objects.Song;
import player.Player;
import player.PlayerController;

public class JukeboxLocalImp implements Jukebox {
	ArrayList<Song> availableSongs = null;

	static JukeboxLocalImp instance;
	PlayerController playerc;
	IndexSongs is;
	DBOperations dbo;

	public static JukeboxLocalImp getInstance() {
		if (instance == null) {
			instance = new JukeboxLocalImp();
		}

		return instance;
	}

	private JukeboxLocalImp() {
		playerc = PlayerController.getInstance();
		is = IndexSongs.getInstance();
		dbo = DBOperations.getInstance();
		
	}

	public ArrayList<Song> getSongs() {

		try {

			if (availableSongs == null) {
				availableSongs = is.getSongsObject();
				System.out.println(availableSongs.size());
			}
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
		
		
		if (availableSongs == null) {
			try {
				availableSongs = is.getSongsObject();
			} catch (UnsupportedTagException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(availableSongs.size());
		}
		
		List<String> plst = playerc.getPlayList();
		System.out.println("PLAYLIST SIZE" + plst.size());
		ArrayList<Song> playlstsngs = new ArrayList<Song>();
		int inc = 0;
		System.out.println("CUANTO ES EL SIZE?" +plst.size());
		while (plst.size() > inc) {
			System.out
					.println("dentro del primer  while  y esto vale el inc   "
							+ inc);

			int inc2 = 0;
			System.out.println("DECLARAMOS INCC2 " +inc2);
			System.out.println( "CUANTO ES AVAILABLE SONGS "+ availableSongs.size());
			while (availableSongs.size() > inc2) {
				System.out.println("dentro del segundo  while");

				if (plst.get(inc).equals(availableSongs.get(inc2).getId())) {

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
		
		songssrch = dbo.searchSong(term);

//		if (availableSongs == null) {
//			try {
//				availableSongs = is.getSongsObject();
//			} catch (UnsupportedTagException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvalidDataException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		System.out.println("VAMOS A BUSCAR EN LOCAL" + term);
//		int in = 0;
//		while (in < availableSongs.size()) {
//			String cadena = availableSongs.get(in).getName();
//			System.out.println("CADENA:     " + cadena);
//			int resultado = cadena.indexOf(term);
//			System.out.println("COINCIDENCIAS:     " + resultado);
//			if (resultado != -1) {
//
//				songssrch.add(availableSongs.get(in));
//			}
//			in++;
//		}
		System.out.println("LA LONG EN LA IMPLEMENTACION: "+songssrch.size());
		return songssrch;

	}

	public int addToPlaylist(String songId) {
		System.out.println("Queremos añadir " + songId
				+ "a la lista de reproduccion");
		String newsg = songId.replace("_", "'");
		System.out.println("Despues de substituir tenemos " + newsg + "...");
		playerc.addSongToPlayList(newsg);

		return 0;
	}

	public Song getSong(String id) {

		String name = id;
		System.out.println("EN IMPLEMENTACION QUEREMOS   " + id);

		int x = 0;
		while (x < availableSongs.size()) {
			if (availableSongs.get(x).getId().equals(name)) {
				System.out.println("ha encontrado la cancion");
				return availableSongs.get(x);
			}
			x++;
		}
		return null;
	}

}
