package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;

@Entity
public interface IHasThumbnail {

	public List<Thumbnail> getThumbs();
}
