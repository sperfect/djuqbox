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
	@Path("rooms/{id}")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRoom(@PathParam("id") String aRoomId,  MethodCallback<Room> callback);
	
	
	@POST
	@Path("rooms")  
	public void createRoom(Room r, MethodCallback<Room> callback);
	
	@PUT
	@Path("rooms/{id}")  
	public void updateRoom(@PathParam("id") @Attribute("id") Room r, MethodCallback<Room> callback);
	
	@DELETE
	@Path("rooms/{id}")  
	public void deleteRoom(@PathParam("id") @Attribute("id") Room r, MethodCallback<Void> callback );
	
		
	@GET
	@Path("rooms/{id}/users")  
	public void getRoomUsers(@PathParam("id") @Attribute("id") Room r, MethodCallback<List<User>> callback );
	
	
	
	//paizei!!
	@POST
	@Path("rooms/{id}/users")  
	public void addUserToRoom(@PathParam("id") String aRoomId, User r, MethodCallback<Void> callback );
	
	
	
	
	
//	@PUT
//	@Path("room/users") 
//	public void createRoom(Room r, User u,  MethodCallback<Room> callback);
	//den ginetai Methods can optionally declare one method argument before the callback to pass via the request body
	
//	@PUT
//	@Path("room/users") 
//	public void addUserToRoom(RoomUser ru,  MethodCallback<Room> callback);

	
	@GET
	@Path("users")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getUsers( MethodCallback<List<User>> callback);
	
	
	@GET
	@Path("users/{id}")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getUser(@PathParam("id") String aUserId,  MethodCallback<User> callback);
	
}

