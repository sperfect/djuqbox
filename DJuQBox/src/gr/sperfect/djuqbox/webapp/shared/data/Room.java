package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;

//einai OK
@Entity
public class Room extends BaseDataClass {

	private List<User> users;
	private User userCreator;
	private PlayList currentPlayList;
	
	private RoomStatus roomStatus;
	

	public Room() {
		// needed by json
		// needed by Objectify
	}

	public Room(String aName) {
		super("Room " + aName);

	}

	public List<User> getUsers() {

		if (users == null) {
			users = new ArrayList<User>();
		}
		return users;
	}

	public void setUsers(List<User> aUsers) {

		users = aUsers;
	}

	User getUserCreator() {
		return userCreator;
	}

	void setUserCreator(User userCreator) {
		this.userCreator = userCreator;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

//	public PlayList getCurrentPlayList() { 
//		return currentPlayList;
//	}
//
//	public void setCurrentPlayList(PlayList currentPlayList) {
//		this.currentPlayList = currentPlayList;
//	}


}
