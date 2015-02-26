package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.ArrayList;
import java.util.List;

public class Song extends BaseDataClass {

	public Song() {

	}

	private String uri;

	private String title;

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
