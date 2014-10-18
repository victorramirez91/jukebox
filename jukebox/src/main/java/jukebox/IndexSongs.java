package jukebox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import objects.Song;
import objects.Songs;



public class IndexSongs {
	
	
	public String getcurrentfolfer()
	{
		return sDirectorio;
	}
	List<Song> listsong = new ArrayList<Song>();
	public String sDirectorio = "C:/Users/Victorz/jukeboxsongs/songsshort/";
	public List<String> GetSongsName()
	{
		
		File f = new File(sDirectorio);
		 
		if (f.exists()){
			System.out.println("Directorio existe");
		
		}
		else { System.out.println("Directorio no existe ");
			}
		
		 
		File[] ficheros = f.listFiles();
		
		int i =0;
		 List<String> songslist = new ArrayList<String>();
		while (i<ficheros.length)
		{	
			if(( ficheros[i].toString().toLowerCase().endsWith(".mp3"))){
					
			songslist.add(ficheros[i].getName());}
			
			System.out.println(songslist.size());
			i++;
		}
		for (int x=0;x<ficheros.length;x++){
			
		  System.out.println(ficheros[x].getName());
		}
		
		return songslist;
		
	}
	
	public String getSongsJson()
	{	
		
		List<String> songs = GetSongsName();
		int i=0;
		while(i< songs.size())
		{
			String temp = songs.get(i);
			System.out.println("---------"+temp);
			
			if (temp.contains("-")) {
				String[] parts = temp.split("-");
				Song sng = new Song(parts[0], parts[1]);
				System.out.println("Artist: "+parts[0]+ " Song: "+parts[1]);
				listsong.add(sng);
			} else {
			    throw new IllegalArgumentException("String " + temp + " does not contain -");
			}
			
			i++;
		}
			
		Songs response = new Songs(listsong);

		String json = new Gson().toJson(response );
		
		return json; 
	}
	
	
	
	}

