package clases;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
	@Id
	String idticket;
	String date;
	String category;
	
	public Ticket(String idticket, String date, String category) {
		super();
		this.idticket = idticket;
		this.date = date;
		this.category = category;
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
