package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.fusesource.restygwt.client.Attribute;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface RestApiService extends RestService {


	//http://resty-gwt.github.io/documentation/restygwt-user-guide.html
	
	@GET
	@Path("rooms")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRooms( MethodCallback<List<Room>> callback);
	
	@GET
	@Path("rooms/{room_id}")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRoom(@PathParam("room_id") Long aRoomId,  MethodCallback<Room> callback);
	
	
	@POST
	@Path("rooms")  
	public void createRoom(Room r, MethodCallback<Room> callback);
	
	@PUT
	@Path("rooms/{room_id}")  
	public void updateRoom(@PathParam("room_id") @Attribute("id") Room r, MethodCallback<Room> callback);
	
	@DELETE
	@Path("rooms/{room_id}")  
	public void deleteRoom(@PathParam("room_id") @Attribute("id") Room r, MethodCallback<Void> callback );
	
		
	@GET
	@Path("rooms/{room_id}/users")  
	public void getRoomUsers(@PathParam("room_id") @Attribute("id") Room r, MethodCallback<List<User>> callback );
	
	
	
	//paizei!!
	@POST
	@Path("rooms/{room_id}/users")  
	public void addUserToRoom(@PathParam("room_id") Long aRoomId, User r, MethodCallback<Void> callback );
	
	
	
	
	
	@GET
	@Path("users") 
	public void getUsers( MethodCallback<List<User>> callback);
	
	
	@GET
	@Path("users/{user_id}") 
	public void getUser(@PathParam("user_id") Long aUserId,  MethodCallback<User> callback);
	
}

