package gr.sperfect.djuqbox.webapp.server.rest;



import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;


@Path("/room")
public class RoomResource {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getUserRooms(@Context HttpHeaders headers) {
		
		List<Room>  ret = new ArrayList<Room>();
		ret.add(new Room("room0"));
		ret.add(new Room("room1"));
		
		for(String header : headers.getRequestHeaders().keySet()){
			System.out.println(header +": " + headers.getRequestHeader(header));
		}

		return ret; 
	}
	
	@POST	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Room createRoom (Room aRoomParam) {
		Room newRoom = new Room(aRoomParam.getName());
		//save assign...
		return newRoom;
	}
}
