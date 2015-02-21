package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;


//einai OK
@Entity
public class Room extends BaseDataClass {

	//test dates //baraei 
	private Date d ;

	public Room() {
		// needed by json
	}

	public Room(String aName) {
		super("Room "+ aName);
		
	}

	public Date getDate1() {
		return d;
	}

	public void setDate1(Date d) {
		this.d = d;
	}

	

}
