package gr.sperfect.djuqbox.webapp.shared.data;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class RoomStatus extends BaseDataClass {
	
	public RoomStatus() {
		// needed by json
		// needed by Objectify
	}
	
	private boolean needsReload;
	private Song currentSong;
	private Long currentSongPosition;
	private int playerStatus; // 0 stopped, 1 playing, 2 paused
	private int volume; // 0-100
	

	public Song getCurrentSong() {
		return currentSong;
	}

	public void setCurrentSong(Song currentSong) {
		this.currentSong = currentSong;
	}

	public Long getCurrentSongPosition() {
		return currentSongPosition;
	}

	public void setCurrentSongPosition(Long currentSongPosition) {
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

	public boolean isNeededReload() {
		return needsReload;
	}

	public void setNeedsReload(boolean needsReload) {
		this.needsReload = needsReload;
	}
	
}
