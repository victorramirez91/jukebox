package player;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class BasicPlayerTest implements BasicPlayerListener
{
  private PrintStream out = null;
  BasicController control;
  /**
   * Entry point.
   * @param args filename to play.
   */
  public static void main(String[] args)
  {
    BasicPlayerTest test = new BasicPlayerTest();
    test.play(args[0]); 
  }

    /**
     * Contructor.
     */
  public BasicPlayerTest()
     {	
      out = System.out;
     }
  public void Stopsong() throws BasicPlayerException
  {
   control.stop();
  }

  public void play(String filename)
     {
       // Instantiate BasicPlayer.
      BasicPlayer player = new BasicPlayer();
      // BasicPlayer is a BasicController.
       control = (BasicController) player;
      // Register BasicPlayerTest to BasicPlayerListener events.
      // It means that this object will be notified on BasicPlayer
      // events such as : opened(...), progress(...), stateUpdated(...)
      player.addBasicPlayerListener(this);

  try
     { 
      // Open file, or URL or Stream (shoutcast, icecast) to play.
      control.open(new File(filename));

      // control.open(new URL("http://yourshoutcastserver.com:8000"));

      // Start playback in a thread.
      control.play();
      
      
      // If you want to pause/resume/pause the played file then
      // write a Swing player and just call control.pause(),
      // control.resume() or control.stop(). 
      // Use control.seek(bytesToSkip) to seek file
      // (i.e. fast forward and rewind). seek feature will
      // work only if underlying JavaSound SPI implements
      // skip(...). True for MP3SPI and SUN SPI's
      // (WAVE, AU, AIFF).

      // Set Volume (0 to 1.0).
      control.setGain(0.85);
      // Set Pan (-1.0 to 1.0).
      control.setPan(0.0);
      control.seek(5000);
      
    }
    catch (BasicPlayerException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Open callback, stream is ready to play.
   *
   * properties map includes audio format dependant features such as
   * bitrate, duration, frequency, channels, number of frames, vbr flag, ... 
   *
   * @param stream could be File, URL or InputStream
   * @param properties audio stream properties.
   */


  /**
   * Progress callback while playing.
   * 
   * This method is called severals time per seconds while playing.
   * properties map includes audio format features such as
   * instant bitrate, microseconds position, current frame number, ... 
   * 
   * @param bytesread from encoded stream.
   * @param microseconds elapsed (<b>reseted after a seek !</b>).
   * @param pcmdata PCM samples.
   * @param properties audio stream parameters.
  */
  

  /**
   * Notification callback for basicplayer events such as opened, eom ...
   * 
   * @param event
   */
  

  /**
   * A handle to the BasicPlayer, plugins may control the player through
   * the controller (play, stop, ...)
   * @param controller : a handle to the player
   */ 


  public void display(String msg)
  {
    if (out != null) out.println(msg);
    
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

//@Override
//public void opened(Object stream, Map properties) {
//	// TODO Auto-generated method stub
//	 display("opened : "+properties.toString()); 
//}
//
//@Override
//public void progress(int arg0, long arg1, byte[] arg2, Map properties) {
//	// TODO Auto-generated method stub
//	  // Pay attention to properties. It depends on underlying JavaSound SPI
//    // MP3SPI provides mp3.equalizer.
//	  display("progress : "+properties.toString());
//}
//
//@Override
//public void setController(BasicController controller) {
//	// TODO Auto-generated method stub
//	display("setController : "+controller);
//}
//
//@Override
//public void stateUpdated(BasicPlayerEvent event) {
//	// TODO Auto-generated method stub
//	 display("stateUpdated : "+event.toString());
//}
}