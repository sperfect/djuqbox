package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;


//einai OK
@Entity
public class Room extends BaseDataClass {

	//test dates
	public Date d ;

	public Room() {
		// needed by json
	}

	public Room(String aName) {
		super("Room "+ aName);
		
	}

	

}
