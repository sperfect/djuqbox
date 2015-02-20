package gr.sperfect.djuqbox.webapp.shared.data;

import com.googlecode.objectify.annotation.Entity;


//test xtypaei ston client?
@Entity
public class Room extends BaseDataClass {

	

	public Room() {
		// needed by json
	}

	public Room(String aName) {
		super("Room "+ aName);
		
	}

	

}
