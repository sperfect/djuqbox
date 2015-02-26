package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;
import java.util.List;

public class PlayList extends BaseDataClass {

	public PlayList() {

	}


	private Room roomOwner;
	private User userCreated;
	
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


}
