package player;

import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javazoom.jlgui.player.amp.playlist.Playlist;
import javazoom.jlgui.player.amp.playlist.PlaylistFactory;
import javazoom.jlgui.player.amp.playlist.PlaylistItem;

/**
 * PlayIT - BasicPlayer sample.
 * This sample code plays item in a playlist.
 */
public class playIT implements BasicPlayerListener
{
  public static BasicPlayer thePlayer = null;
  private PlaylistFactory plf = null;
  private Playlist playlist = null;

  /**
   * Constructor.
 * @return 
   */
  public  playIT()
  {
    thePlayer = new BasicPlayer();
    plf = PlaylistFactory.getInstance();
    playlist = plf.getPlaylist();
    PlaylistItem pli =  new PlaylistItem("Item1","e:\\mp3\\zombie.mp3",-1,true);
    playlist.appendItem(pli);
    pli =  new PlaylistItem("Item2","e:\\mp3\\bodyfunk.mp3",-1,true);
  
    playlist.appendItem(pli);
    // ...
    
    // Add all others items manually or use loadPlaylist (m3u) from jlGui Player.
    // ...
    playSong();
  }

  /**
   * Main.
   */
  public static void main(String[] args)
  {
    playIT bp = new playIT();
  }

  // BasicPlayer listener
  public void updateCursor(int secondsAmount, int total)
  {
    // Manage "elapsed time" for the current song here
  }
  public void updateMediaData(byte[] parm1)
  {
    // Spectrum analyzer for the current song could take place here
  }
  public void updateMediaState(String state)
  {
    // Check if song is completed.
    if (state.equals("EOM"))
    {
      // End of song reached ? So play next song.
      playSong();
    }
  }

  /**
   * Play next song from playlist.
   */
  private void playSong()
  {
    try
    {
      PlaylistItem currentItem = nextSong();
      if (currentItem != null)
      {
        //thePlayer.setDataSource(new File(currentItem.getLocation()));
        //thePlayer.startPlayback();
        System.out.println("Playing : "+currentItem.getName());
      }
      else
      {
        System.exit(0);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  /**
   * Ask the next song from playlist.
   */
  private PlaylistItem nextSong()
  {
    playlist.nextCursor();
    PlaylistItem pli = playlist.getCursor();
    return pli;
  }

public void opened(Object arg0, Map arg1) {
	// TODO Auto-generated method stub
	
}

public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
	// TODO Auto-generated method stub
	
}

public void setController(BasicController arg0) {
	// TODO Auto-generated method stub
	
}

public void stateUpdated(BasicPlayerEvent arg0) {
	// TODO Auto-generated method stub
	
}
}