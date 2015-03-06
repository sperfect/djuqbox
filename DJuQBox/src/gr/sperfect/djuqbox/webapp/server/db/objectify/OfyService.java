package gr.sperfect.djuqbox.webapp.server.db.objectify;

import gr.sperfect.djuqbox.webapp.shared.data.PlayList;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.Song;
import gr.sperfect.djuqbox.webapp.shared.data.Thumbnail;
import gr.sperfect.djuqbox.webapp.shared.data.User;
import gr.sperfect.djuqbox.webapp.shared.data.UserRoomRole;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubePlayList;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {

	static {

		// xreiazetai
		// factory().register(BaseDataClass.class);

		factory().register(Room.class); //to Room has PlayList has Room tou gamaei...
		factory().register(User.class);
		factory().register(Song.class);
		factory().register(PlayList.class);  
		factory().register(UserRoomRole.class);
		
		//factory().register(HATEOASLink.class); 
		factory().register(YoutubePlayList.class);
		factory().register(YoutubeSong.class);
		
		
		factory().register(Thumbnail.class);

		// ...etc
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
