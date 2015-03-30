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
		System.out.println("CUANTO ES EL SIZE?" + plst.size());
		while (plst.size() > inc) {
			Song tempobj = new Song();
			String[] textElements = plst.get(inc).split("_");
			int inc2 = 0;

			while (availableSongs.size() > inc2) {
				

				System.out.println("EL 1" + textElements[1]);
				String newsgx = availableSongs.get(inc2).getId().replace("_", "'");
				//if (textElements[1].equals(availableSongs.get(inc2).getId())) {
				if (textElements[1].equals(newsgx)) {
					tempobj = availableSongs.get(inc2);
					
					System.out.println("VALE " + textElements[0]);
					//
					if (textElements[0].equals("M")) {
//						tempobj.setGenre("MAIN");
						Song main = new Song();
						main.setAlbum(availableSongs.get(inc2).getAlbum());
						main.setArtist(availableSongs.get(inc2).getArtist());
						main.setDuration(availableSongs.get(inc2).getDuration());
						main.setGenre("MAIN");
						main.setId(availableSongs.get(inc2).getId());
						main.setImage(availableSongs.get(inc2).getImage());
						main.setName(availableSongs.get(inc2).getName());
						playlstsngs.add(main);

					}
					else{
						//tempobj.setGenre("AUX");
					playlstsngs.add(tempobj);}
				}

				inc2++;

			}

			inc++;
		}
		return playlstsngs;

	}

	public ArrayList<Song> search(String term) {
		ArrayList<Song> songssrch = new ArrayList<Song>();

		songssrch = dbo.searchSong(term);

		// if (availableSongs == null) {
		// try {
		// availableSongs = is.getSongsObject();
		// } catch (UnsupportedTagException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvalidDataException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// System.out.println("VAMOS A BUSCAR EN LOCAL" + term);
		// int in = 0;
		// while (in < availableSongs.size()) {
		// String cadena = availableSongs.get(in).getName();
		// System.out.println("CADENA:     " + cadena);
		// int resultado = cadena.indexOf(term);
		// System.out.println("COINCIDENCIAS:     " + resultado);
		// if (resultado != -1) {
		//
		// songssrch.add(availableSongs.get(in));
		// }
		// in++;
		// }
		System.out.println("LA LONG EN LA IMPLEMENTACION: " + songssrch.size());
		return songssrch;

	}

	public int addToPlaylist(String songId) {
		System.out.println("Queremos añadir " + songId
				+ "a la lista de reproduccion");
		String newsg = songId.replace("_", "'");
		// String newsg2 = newsg.replace(":", "&");
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
