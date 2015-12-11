package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Client extends BaseDataClass {

	public Client() {
	
	}
	
	private String clientId;
	private Date clientDateTime;
	private User user;
	private Song currentSong;
	private int currentSongPosition;
	private int playerStatus; // 0 stopped, 1 playing, 2 paused
	private int volume; // 0-100
	
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
	public Song getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}

	public int getCurrentSongPosition() {
		return currentSongPosition;
	}

	public void setCurrentSongPosition(int currentSongPosition) {
		this.currentSongPosition = currentSongPosition;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	/*
	 * 0 stopped, 1 playing, 2 paused
	 */
	public int getPlayerStatus() {
		return playerStatus;
	}

	/*
	 * 0 stopped, 1 playing, 2 paused
	 */
	public void setPlayerStatus(int playerStatus) {
		this.playerStatus = playerStatus;
	}

	
}
