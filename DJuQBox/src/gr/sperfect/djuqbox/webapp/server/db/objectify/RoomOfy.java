package gr.sperfect.djuqbox.webapp.server.db.objectify;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class RoomOfy {

	@Id
	public Long id;
	@Index
	public String name;

	public RoomOfy(String name) {

	}

}
