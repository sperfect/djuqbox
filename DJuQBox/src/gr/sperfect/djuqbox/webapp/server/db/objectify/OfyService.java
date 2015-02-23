package gr.sperfect.djuqbox.webapp.server.db.objectify;

import gr.sperfect.djuqbox.webapp.shared.data.Playlist;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.Song;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

	static {
		
		//xreiazetai
		//factory().register(BaseDataClass.class);

		factory().register(Room.class);
		factory().register(User.class);
//		factory().register(Song.class);
//		factory().register(Playlist.class);

		// ...etc
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
