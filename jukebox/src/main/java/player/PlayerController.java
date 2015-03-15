package player;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import jukebox.IndexSongs;

public class PlayerController {

	// Player player;
	TestController player;
	IndexSongs index;
	Boolean controlador = false;
	public static List<String> playlistaux = new ArrayList<String>();
	public static List<String> playlist = new ArrayList<String>();
	static String folder = IndexSongs.sDirectorio;
	private static PlayerController instance;
	public static int playlistauxlongitude, currentsong = 0;
	public static int playlistlongitude = 0;
	public static int playlist_initial = 0;

	public static synchronized PlayerController getInstance() {
		if (instance == null) {
			
			instance = new PlayerController();
		}
		return instance;
	}
	

	private PlayerController() {
		index = IndexSongs.getInstance();
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		playlistaux = index.GetSongsName();
		playlistauxlongitude = playlistaux.size();

	}

	public List<String> getPlayList() {

		List<String> newList = new ArrayList<String>();

		if (controlador == true) {

			if (playlist.size() == 1) {
				newList.add("M_"+playlist.get(0));
			} else {
				newList.add("M_"+playlist.get(playlist_initial - 1));
			}
			int i = playlist_initial;
			while (i < playlist.size()) {

				//if (playlist.size() == 1) {
					//newList.add("M_"+playlist.get(0));
			//	} else
					newList.add("M_"+playlist.get(i));
				i++;
			}
			int i2 = currentsong + 1;
			while (i2 < playlistaux.size()) {
				newList.add("A_"+playlistaux.get(i2));
				i2++;
			}
			//newList.addAll(playlistaux);
		}

		if (controlador == false) {
			int i = playlist_initial;
			newList.add("A_"+playlistaux.get(currentsong));
			while (i < playlist.size()) {
				newList.add("M_"+playlist.get(i));
				i++;
			}
			int i2 = currentsong + 1;
			while (i2 < playlistaux.size()) {
				newList.add("A_"+playlistaux.get(i2));
				i2++;
			}
			//newList.addAll(playlistaux);
		}

		return newList;
		// System.out.println("playercontroller getplaylist");
		// ArrayList<String> playcurr = new ArrayList<String>();
		// ArrayList<String> playauxcurr = new ArrayList<String>();
		// int i=0;
		// while(i <playlist.size())
		// { System.out.println("WHILE DE PLAYLUST");
		// System.out.println("playlist initial vale :"+playlist_initial);
		// //if (controlador==true){
		// //if(i<= playlist_initial){
		//
		// playcurr.add(playlist.get(i));
		// //}
		// // }
		// // else{
		// // if(i== playlist_initial){
		// //
		// // playcurr.add(playlist.get(i));
		// // }
		// // }
		//
		// i++;
		// }
		// int ii=0;
		// while(ii <playlistaux.size())
		// {System.out.println("WHILE DE PLAYLISTAUX");
		// if(ii>= currentsong){
		// playauxcurr.add(playlistaux.get(ii));
		// }
		// ii++;
		// }
		// List<String> newList = new ArrayList<String>();
		// System.out.println(" EL CONTROLADOR VALE "+controlador);
		// if((playcurr.size()-1)>=0 &&controlador == true)
		// {
		//
		// newList.addAll(playcurr);
		// newList.addAll(playauxcurr);
		// // if(controlador==true){
		// // newList.remove(playcurr.size());
		// // }
		//
		//
		// }
		// if((playcurr.size()-1)>=0 &&controlador == false)
		// {
		//
		// newList.addAll(playcurr);
		// newList.addAll(playauxcurr);
		// newList.remove(0);
		// // if(controlador==true){
		// // newList.remove(playcurr.size());
		// // }
		//
		//
		// }
		//
		// else{
		// newList.add(playauxcurr.get(0));
		// newList.addAll(playcurr);
		// newList.addAll(playauxcurr);
		// newList.remove(playcurr.size() +1);
		// }
		//
		//
		//
		// // int x=0;
		// // while (x<playauxcurr.size())
		// // {
		// // newList.add(playauxcurr.get(x));
		// // x++;
		// // }
		// //newList.addAll(playauxcurr);
		// System.out.println("LA NEWLIST TIENE "+newList.size()+"POSICIONES");
		//
		//
		// return newList;
	}

	public void stopPlayer() {
		try {
			player.stopPlayer("");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void nextSong(String event) throws FileNotFoundException,
			JavaLayerException {

		// //
		// player.play("C:/Users/Victorz/jukeboxsongs/AllSongs/testing2-2.mp3");
		// boolean chck = false;
		// try {
		// playlistlongitude++;
		// playlist.get(playlistlongitude - 2);
		//
		// chck = true;
		// } catch (IndexOutOfBoundsException e) {
		// System.out.println("ERROR");
		//
		// }

		if (playlist.size() != playlistlongitude) {
			controlador = true;
			System.out.println("HAY CANCIONES EN PLAYLIST");
			playlist_initial++;
			playlistlongitude++;
//			String[] textElements1 = playlist.get(playlist_initial - 1).split("_");
//			player.play(folder + textElements1[1]);
			player.play(folder + playlist.get(playlist_initial - 1));

		} else {
			controlador = false;
			
			System.out.println("current vale" + currentsong + "del size "
					+ playlistauxlongitude);
			if (currentsong == playlistauxlongitude - 1) {
				currentsong = 0;
//				String[] textElements2 = playlistaux.get(currentsong).split("_");
//				player.play(folder + textElements2[1]);
				player.play(folder + playlistaux.get(currentsong));
			} else {
				System.out.println("NO hay de la playlist, nos vamosa "
						+ currentsong);
				currentsong++;
				//String[] textElements3 = playlistaux.get(currentsong).split("_");
				//player.play(folder + textElements3[1]);
				player.play(folder + playlistaux.get(currentsong));
			}
		}

		// if (event.equals("STOPPED:-1") ) {
		// System.out.println("RECIBIMOS EL MENSAJE DE STOPED");
		// //&& currentsong < playlistauxlongitude
		// System.out.println("LA SIZE EN NXT"+playlist.size());
		// //playlist.add("01 Ed Sheeran - Thinking Out Loud.mp3");
		// if(playlist.get(0)!=null)
		// { System.out.println("HAY CANCIONES EN PLAYLIST");
		// player.play(folder+playlist.get(playlistlongitude));
		// }
		// else{
		// player.play(folder + playlistaux.get(currentsong));
		// currentsong++;}
		// }
	}

	public String initalizePlayer() throws FileNotFoundException,
			JavaLayerException {
		player = TestController.getInstance();
		player.play(folder + playlistaux.get(0));
		currentsong++;
		return null;
	}

	public String addSongToPlayList(String nwsong) {
		System.out.println("recibimos esto" + nwsong);

		playlist.add(nwsong);
		System.out.println(playlist.get(0));
		System.out.println("VALE HEMOS AÑADIDO LA PLAYLIST TIENE "
				+ playlist.size() + "POSICIONES");
		// System.out.println("LA LONG ES" + playlistlongitude);
		// playlistlongitude++;
		return null;
	}
}
