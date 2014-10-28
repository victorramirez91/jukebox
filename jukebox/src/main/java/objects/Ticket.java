package objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	String idticket;
	String date;
	String category;
	Boolean used;
	
	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public Ticket(String idticket, String date, String category) {
		super();
		this.idticket = idticket;
		this.date = date;
		this.category = category;
	}
	public Ticket(String idticket, String date, String category, Boolean used) {
		super();
		this.idticket = idticket;
		this.date = date;
		this.category = category;
		this.used = used;
	}
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdticket() {
		return idticket;
	}
	public void setIdticket(String idticket) {
		this.idticket = idticket;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
