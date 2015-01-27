package objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientIP {
	@Id
	String ip;
	int counter;
	String timestamp;
	boolean ban;

	public ClientIP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientIP(String ip, int counter, String timestamp, boolean ban) {
		super();
		this.ip = ip;
		this.counter = counter;
		this.timestamp = timestamp;
		this.ban = ban;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isBan() {
		return ban;
	}

	public void setBan(boolean ban) {
		this.ban = ban;
	}

}
