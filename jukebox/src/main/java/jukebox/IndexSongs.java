package jukebox;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.google.gson.Gson;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import objects.Song;
import objects.Songs;
import objects.TrackMaped;

public class IndexSongs {
	public static String sDirectorio = "C:/Users/Victorz/jukeboxsongs/AllSongs/";

	public String getcurrentfolfer() {
		return sDirectorio;
	}

	ArrayList<Song> listsong = new ArrayList<Song>();
	ArrayList<TrackMaped> listtracks = new ArrayList<TrackMaped>();

	// Metodo para buscar las canciones que hay en un determinado directorio
	public List<String> GetSongsName() {

		File f = new File(sDirectorio);

		if (f.exists()) {
			System.out.println("Directorio existe");

		} else {
			System.out.println("Directorio no existe ");
		}

		File[] ficheros = f.listFiles();

		int i = 0;
		List<String> songslist = new ArrayList<String>();
		while (i < ficheros.length) {
			if ((ficheros[i].toString().toLowerCase().endsWith(".mp3"))) {

				songslist.add(ficheros[i].getName());
			}

			System.out.println(songslist.size());
			i++;
		}
		for (int x = 0; x < ficheros.length; x++) {

			System.out.println(ficheros[x].getName());
		}

		return songslist;

	}

	public ArrayList<Song> getSongsObject() throws UnsupportedTagException, InvalidDataException, IOException {
		List<String> songs = GetSongsName();
		int i = 0;
		while (i < songs.size()) {
			Mp3File mp3file = new Mp3File(sDirectorio + songs.get(i));
			if (mp3file.hasId3v2Tag()) {
				ID3v2 id3v2Tag = mp3file.getId3v2Tag();
				Song sg = new Song();
				sg.setAlbum(id3v2Tag.getAlbum());
				sg.setArtist(id3v2Tag.getArtist());
				sg.setName(id3v2Tag.getTitle());
				if(id3v2Tag.getGenre()==-1)
				{
					sg.setGenre("no info");
				}
				if(id3v2Tag.getGenre()!=-1)
				{
					sg.setGenre(id3v2Tag.getGenreDescription());
				}
				
				byte[] albumImageData = id3v2Tag.getAlbumImage();
				if (albumImageData != null) {
					sg.setImage(id3v2Tag.getTitle()+".jpg");
					getimage(albumImageData, id3v2Tag.getTitle());
					System.out.println("Have album image data, length: "
							+ albumImageData.length + " bytes");
					System.out.println("Album image mime type: "
							+ id3v2Tag.getAlbumImageMimeType());
				}
				else{
					sg.setImage("default.jpg");
				}
				listsong.add(sg);
			}
			i++;

		}
		return listsong;
	}

	public static void getimage(byte[] bytes, String name) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");

		// ImageIO is a class containing static methods for locating
		// ImageReaders
		// and ImageWriters, and performing simple encoding and decoding.

		ImageReader reader = (ImageReader) readers.next();
		Object source = bis;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();

		Image image = reader.read(0, param);
		// got an image file

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// bufferedImage is the RenderedImage to be written

		Graphics2D g2 = bufferedImage.createGraphics();
		g2.drawImage(image, null, null);

		File imageFile = new File("C:/Users/Victorz/jukeboxsongs/" +"AllSongsImages/"+ name + ".jpg");
		ImageIO.write(bufferedImage, "jpg", imageFile);

		System.out.println(imageFile.getPath());
	}

	public String returnSongsJson() throws UnsupportedTagException, InvalidDataException, IOException
	{
		
		if(listsong==null)
		{
			listsong= getSongsObject();
			String json = new Gson().toJson(listsong);

			return json;
		}
		else{
			String json = new Gson().toJson(listsong);

			return json;
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSongsJson() {

		List<String> songs = GetSongsName();
		int i = 0;
		while (i < songs.size()) {
			String temp = songs.get(i);
			System.out.println("---------" + temp);

			if (temp.contains("-")) {
				String[] parts = temp.split("-");
				Song sng = new Song(parts[0], parts[1]);
				System.out
						.println("Artist: " + parts[0] + " Song: " + parts[1]);
				listsong.add(sng);
			} else {
				throw new IllegalArgumentException("String " + temp
						+ " does not contain -");
			}

			i++;
		}

		Songs response = new Songs(listsong);

		String json = new Gson().toJson(response);

		return json;
	}

}
