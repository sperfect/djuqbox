package gr.sperfect.djuqbox.webapp.server.rest;



import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.ArrayList;
import java.util.List;


@Path("/room")
public class RoomResource {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getUserRooms(@Context HttpHeaders headers) {
		
		List<Room>  ret = new ArrayList<Room>();
		ret.add(new Room("room0"));
		ret.add(new Room("room1"));
		

		return ret;
	}
}
