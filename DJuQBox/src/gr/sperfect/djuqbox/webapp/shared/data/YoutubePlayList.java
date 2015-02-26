package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class YoutubePlayList extends PlayList implements IHasThumbnail{

	public YoutubePlayList() {

		
	}
	
	
	public void addSong(YoutubeSong ys) {
		
		super.addSong(ys);
	}


	private List<Thumbnail> thumbs;
	
	@Override
	public List<Thumbnail> getThumbs() {
		if (thumbs == null) {
			thumbs = new ArrayList<Thumbnail>();
		}
		return thumbs;
	}
	
	

}
