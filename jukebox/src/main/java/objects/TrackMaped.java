package objects;

public class TrackMaped {
	
	String Artist;
	String Name;
	String duration;
	String id;
	String image;
	
	public TrackMaped(String artist, String name, String duration, String id,
			String image) {
		super();
		Artist = artist;
		Name = name;
		this.duration = duration;
		this.id = id;
		this.image = image;
	}

	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TrackMaped() {
		
	}
	

}
