package objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Folder {
	@Id
	String id;
	String lastmodified;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
}
