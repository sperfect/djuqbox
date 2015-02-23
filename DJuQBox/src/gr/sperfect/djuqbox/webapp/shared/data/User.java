package gr.sperfect.djuqbox.webapp.shared.data;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class User extends BaseDataClass {

	public User() {
		// json needed
	}
	public User(String aName) {
		super("User "+ aName);
		
	}
	
	
	//test git
	//test conflicts
}
