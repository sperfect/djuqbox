package gr.sperfect.djuqbox.webapp.shared.data;

public class RoomStatus extends BaseDataClass {
	
	public RoomStatus() {
		// needed by json
		// needed by Objectify
	}
	
	private boolean needsReload;
	private Song currentSong;
	private Long currentSongPosition;
	private int playerStatus; // 1  playing, 2 stopped
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

	public int getPlayerStatus() {
		return playerStatus;
	}

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
