package player;

import java.util.ArrayList;
import java.util.List;

import jukebox.IndexSongs;

public class Test {
	
	
	public String PlayerOn()
	{

		try {
		  Reproductor mi_reproductor = new Reproductor();
		  //BasicPlayerTest test = new BasicPlayerTest();
		  IndexSongs index = new IndexSongs();
		  List<String> songslist2 = new ArrayList<String>();
		  songslist2=index.GetSongsName();
		  mi_reproductor.AbrirFichero("C:/Users/Victorz/jukeboxsongs/"+songslist2.get(4));
		  mi_reproductor.Play();
		  //test.play("C:/Users/Victorz/jukeboxsongs/50 Cent-Window Shopper.mp3");
		  //test.Stopsong();
		 
		} catch (Exception ex) {
		  System.out.println("Error: " + ex.getMessage());
		}
		return "";
		
	}
	
	
}
