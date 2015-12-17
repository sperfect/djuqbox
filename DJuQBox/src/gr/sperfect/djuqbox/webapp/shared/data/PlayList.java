package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class PlayList extends BaseDataClass {

	public PlayList() {

	}


	private HATEOASLink roomOwnerLink; // OXI! looparei gia panta kata to OfyService.factory().register(Room.class);
	private HATEOASLink userCreatedLink;
	
	//private bool playlistLocked; 8a einai sthn playlist 'h sto room? 
	
	protected List<Song> songs = null;

	public List<Song> getSongs() {
		if (songs == null) {
			songs = new ArrayList<Song>();
		}
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song s) {
		getSongs().add(s);
	}

	public HATEOASLink getRoomOwner() {
		return roomOwnerLink;
	}

	public void setRoomOwner(HATEOASLink link) {
		this.roomOwnerLink = link;
	}

	


}
