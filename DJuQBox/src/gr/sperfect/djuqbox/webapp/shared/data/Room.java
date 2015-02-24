package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;

//einai OK
@Entity
public class Room extends BaseDataClass {

	// test dates //baraei
	private Date d;

	List<User> users;

	public Room() {
		// needed by json
		// needed by Objectify
	}

	public Room(String aName) {
		super("Room " + aName);

	}

	public Date getDate1() {
		return d;
	}

	public void setDate1(Date d) {
		this.d = d;
	}

	public List<User> getUsers() {

		return users;
	}

	public void setUsers(List<User> aUsers) {

		users = aUsers;
	}

}
