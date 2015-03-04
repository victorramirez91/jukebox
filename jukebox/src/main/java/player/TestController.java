package player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import jukebox.IndexSongs;



public class TestController {
	AdvancedPlayer player;
	private static TestController instance;
	public static synchronized TestController getInstance() {
		if (instance == null) {
			System.out.println("ES null");
			instance = new TestController();
		}
		return instance;
	}
	private TestController() {
		

	}
	PlayerController pc = PlayerController.getInstance();
	public String play (String song) throws FileNotFoundException, JavaLayerException
	{
		//FileInputStream fis = new FileInputStream("C:/Users/Victorz/jukeboxsongs/AllSongs/testing2-2.mp3");
		FileInputStream fis = new FileInputStream(song);
		 player = new AdvancedPlayer(fis);
		
		player.setPlayBackListener(new PlaybackListener() {
		    @Override
		    public void playbackFinished(PlaybackEvent event) {
		    	System.out.println("SE HA ACABADO");
		    	 int pausedOnFrame = 0;
		    	pausedOnFrame = event.getFrame();
		    	try {
					pc.nextSong("");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		player.play();
		return song;
		
	}

	
	
	public String stopPlayer (String song) throws FileNotFoundException, JavaLayerException
	{
		//FileInputStream fis = new FileInputStream("C:/Users/Victorz/jukeboxsongs/AllSongs/testing2-2.mp3");
		System.out.println("AQUI PARAMOS EL PLAYER");
		// player.stop();
		 player.close();
		
		
		return "";
		
	}

	
	
//	public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
//		
//		
//		
//		 //int pausedOnFrame = 0;
//		FileInputStream fis = new FileInputStream("C:/Users/Victorz/jukeboxsongs/AllSongs/testing2-2.mp3");
//		AdvancedPlayer player = new AdvancedPlayer(fis);
//		player.setPlayBackListener(new PlaybackListener() {
//		    @Override
//		    public void playbackFinished(PlaybackEvent event) {
//		    	System.out.println("SE HA ACABADO");
//		    	 int pausedOnFrame = 0;
//		    	pausedOnFrame = event.getFrame();
//		    }
//		});
//		player.play();
//		
////		System.out.println("ES EL MAIN");
////		Player apl = null;
////		
////		try {
////			apl = new Player(new FileInputStream(
////			        "C:/Users/Victorz/jukeboxsongs/AllSongs/10 Sam Smith - I'm Not the Only One.mp3"));
////		} catch (FileNotFoundException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		} catch (JavaLayerException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	 
////	      try {
////			apl.play();
////			
////		} catch (JavaLayerException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////
//}

}
