package player;

import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;




import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

import objects.Song;
import jukebox.IndexSongs;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TestEtiquetasMp3 {
	static String path = IndexSongs.sDirectorio;
	
	public static void getimage (byte[] bytes, String name) throws IOException
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
 
        //ImageIO is a class containing static methods for locating ImageReaders
        //and ImageWriters, and performing simple encoding and decoding. 
 
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; 
        ImageInputStream iis = ImageIO.createImageInputStream(source); 
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        Image image = reader.read(0, param);
        //got an image file
 
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //bufferedImage is the RenderedImage to be written
 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
 
        File imageFile = new File(path+name+".jpg");
        ImageIO.write(bufferedImage, "jpg", imageFile);
 
        System.out.println(imageFile.getPath());
	}

	
	private static void getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException {

	    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
	    if (fileFormat instanceof TAudioFileFormat) {
	        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        int sec = (mili / 1000) % 60;
	        int min = (mili / 1000) / 60;
	        System.out.println("time = " + min + ":" + sec);
	    } else {
	        throw new UnsupportedAudioFileException();
	    }

	}
	
	
	
	
	
	
	public static void main(String[] args) throws UnsupportedTagException, InvalidDataException, IOException {

		
		IndexSongs is = new IndexSongs();
		List<Song> aa=is.getSongsObject();
		int i=0;
		System.out.println("--------------------------------");
		while(i< aa.size())
		{
			
			System.out.println(aa.get(i).getName());
			i++;
		}
		
		
		
				// TODO Auto-generated method stub
		Mp3File mp3file = new Mp3File("C:/Users/Victorz/jukeboxsongs/songsshort/14 Ed Sheeran - Don't.mp3");
		File fl = new File("C:/Users/Victorz/jukeboxsongs/songsshort/14 Ed Sheeran - Don't.mp3");
		try {
			getDurationWithMp3Spi(fl);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mp3file.hasId3v1Tag()) {
		
			  ID3v1 id3v1Tag = mp3file.getId3v1Tag();
			  System.out.println("Track: " + id3v1Tag.getTrack());
			  System.out.println("Artist: " + id3v1Tag.getArtist());
			  System.out.println("Title: " + id3v1Tag.getTitle());
			  System.out.println("Album: " + id3v1Tag.getAlbum());
			  System.out.println("Year: " + id3v1Tag.getYear());
			  System.out.println("Genre: " + id3v1Tag.getGenre() + " (" + id3v1Tag.getGenreDescription() + ")");
			  System.out.println("Comment: " + id3v1Tag.getComment());
			  
			}
		System.out.println("------------------------------------------------");
		if (mp3file.hasId3v2Tag()) {
			  ID3v2 id3v2Tag = mp3file.getId3v2Tag();
			  System.out.println("Track: " + id3v2Tag.getTrack());
			  System.out.println("Artist: " + id3v2Tag.getArtist());
			  System.out.println("Title: " + id3v2Tag.getTitle());
			  System.out.println("Album: " + id3v2Tag.getAlbum());
			  System.out.println("Year: " + id3v2Tag.getYear());
			  System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
			  System.out.println("Comment: " + id3v2Tag.getComment());
			  System.out.println("Composer: " + id3v2Tag.getComposer());
			  System.out.println("Publisher: " + id3v2Tag.getPublisher());
			  System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
			  System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
			  System.out.println("Copyright: " + id3v2Tag.getCopyright());
			  System.out.println("URL: " + id3v2Tag.getUrl());
			  System.out.println("Encoder: " + id3v2Tag.getEncoder());
			  byte[] albumImageData = id3v2Tag.getAlbumImage();
			 // getimage(albumImageData,id3v2Tag.getTitle());
			  
			  if (albumImageData != null) {
			    System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
			    System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
			  }
			}
	}

}
