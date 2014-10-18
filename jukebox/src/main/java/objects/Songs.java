package objects;

import java.util.List;

public class Songs {
	List<Song> canciones;

	public List<Song> getCanciones() {
		return canciones;
	}

	public void setCanciones(List<Song> canciones) {
		this.canciones = canciones;
	}

	public Songs(List<Song> canciones) {
		super();
		this.canciones = canciones;
	}
	


}
