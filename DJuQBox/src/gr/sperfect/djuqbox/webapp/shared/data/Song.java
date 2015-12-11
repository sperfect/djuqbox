package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Song extends BaseDataClass {

	public Song() {

	}

	private String uri;

	private String title;
	
	private String artist;	
	private String album;
	private String track;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private List<Thumbnail> thumbs;

	public List<Thumbnail> getThumbs() {
		if (thumbs == null) {
			thumbs = new ArrayList<Thumbnail>();
		}
		return thumbs;
	}
	
	public void setThumbs(List<Thumbnail>  thumbs) {
		this.thumbs = thumbs;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
