package objects;

public class Song {
	
	String Artist;
	String Name;
	String genre;
	String duration;
	String image;
	String album;
	String id;
	
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Song() {
		super();
	}
	public Song(String artist, String name, String genre, String image,
			String album) {
		super();
		Artist = artist;
		Name = name;
		this.genre = genre;
		this.image = image;
		this.album = album;
	}
	public Song(String artist, String name) {
		super();
		this.Artist = artist;
		Name = name;
	
	}
	public String getArtist() {
		return Artist;
	}
	public void setArtist(String artist) {
		this.Artist = artist;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}

}
