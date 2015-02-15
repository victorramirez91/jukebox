package jukebox;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;



import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

import com.google.gson.Gson;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import dboperations.DBOperations;
import objects.Song;
import objects.Songs;
import objects.TrackMaped;
import player.PlayerController;

public class IndexSongs {
	public static String sDirectorio = "C:/Users/Victorz/jukeboxsongs/AllSongs/";
	static IndexSongs instance;
	DBOperations dbo =DBOperations.getInstance();
	public String getcurrentfolfer() {
		return sDirectorio;
	}
	public static IndexSongs getInstance() {
		if (instance == null) {
			instance = new IndexSongs();
		}

		return instance;
	}
	private IndexSongs() {
		System.out.println("INITIALIZE INDEXXXXXXX");
		// TODO Auto-generated constructor stub
	}
	ArrayList<Song> listsong = new ArrayList<Song>();
	ArrayList<TrackMaped> listtracks = new ArrayList<TrackMaped>();

	// Metodo para buscar las canciones que hay en un determinado directorio
	public List<String> GetSongsName() {
		List<String> songslist = new ArrayList<String>();
		
		
		

		File f = new File(sDirectorio);


		if (f.exists()) {
			System.out.println("Directorio existe");

		} else {
			System.out.println("Directorio no existe ");
		}

		File[] ficheros = f.listFiles();

		int i = 0;
		 songslist = new ArrayList<String>();
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
		
		List<String> songs;
		boolean chech1 = checkChangesinFolfer();
		if(chech1==false)
		{
			System.out.println("AL SER FALSE LO PILLAMOS DE LA BBDD");
			listsong=dbo.getSongsfromDB();
			System.out.println("EN INDEXSONGS TENEMOS UNA LISTA CON"+listsong.size()+"ELEMENTOS");
			return listsong;
		}
		else
		{
			 songs = GetSongsName();
			int i = 0;
			while (i < songs.size()) {
				Mp3File mp3file = new Mp3File(sDirectorio + songs.get(i));
				if (mp3file.hasId3v2Tag()) {
					ID3v2 id3v2Tag = mp3file.getId3v2Tag();
					Song sg = new Song();
					sg.setAlbum(id3v2Tag.getAlbum());
					sg.setArtist(id3v2Tag.getArtist());
					
					sg.setName(id3v2Tag.getTitle());
					
					
					System.out.println("AQUI EL LENGHT "+mp3file.getLengthInSeconds());
					long segundos =mp3file.getLengthInSeconds();
					
					
					segundos = segundos%3600;
					long minutos = segundos / 60;
					segundos = segundos%60;
					
					System.out.println("Minutos: " + minutos);
					System.out.println("Segundos: " + segundos);
					
					String sminutos = Long.toString(minutos);
					String ssegundos = Long.toString(segundos);
					
					if(sminutos.length()<2)
					{
						sminutos="0"+sminutos;
					}
					if(ssegundos.length()<2)
					{
						ssegundos="0"+ssegundos;
					}
					String timemin= sminutos+":"+ssegundos;
					
					System.out.println("AQUI TENEMOS EL TIME"+timemin);
					
					sg.setDuration(timemin);
					
				
					
					String idx = songs.get(i).replace("'", "_");
					sg.setId(idx);
					
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
						String im =id3v2Tag.getTitle().replace(" ", "");
						System.out.println("nuevo nombre de imagen....................:"+im);
						sg.setImage("/jukebox/images/"+im+".jpg");
						getimage(albumImageData, im);
						System.out.println("Have album image data, length: "
								+ albumImageData.length + " bytes");
						System.out.println("Album image mime type: "
								+ id3v2Tag.getAlbumImageMimeType());
					}
					else{
						sg.setImage("/jukebox/images/default.jpg");
						sg.setName(idx);
					}
					listsong.add(sg);
				}
				i++;

			}
			dbo.saveSongstoDB(listsong);	
			return listsong;
		}
		
		
		
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

		File imageFile = new File("C:/Users/Victorz/git/tfg/jukebox/src/main/webapp/images/"+ name + ".jpg");
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
	
	
	
	
	
	public boolean checkChangesinFolfer()
	
	{
		File f = new File(sDirectorio);
		long ms=f.lastModified();
		Date d = new Date(ms);
		Calendar c = new GregorianCalendar(); 
		c.setTime(d);
		String dia, annio, hora, minuto, segundo, mes;
		dia = Integer.toString(c.get(Calendar.DATE));
		mes = Integer.toString(c.get(Calendar.MONTH));
		annio = Integer.toString(c.get(Calendar.YEAR));
		hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
		minuto = Integer.toString(c.get(Calendar.MINUTE));
		segundo = Integer.toString(c.get(Calendar.SECOND));
		System.out.println(dia+"_"+mes+"__"+annio+"_"+hora+"_"+minuto+"_"+segundo);
		String lastmod = dia+mes+annio+hora+minuto+segundo;
		
		Boolean respx=dbo.checkFolderModified(lastmod);
		
		return respx;
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
