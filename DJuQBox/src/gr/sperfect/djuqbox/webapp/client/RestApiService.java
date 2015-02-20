package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.List;

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
	@Path("room")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRooms( MethodCallback<List<Room>> callback);
	
	@GET
	@Path("room/{id}")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRoom(@PathParam("id") String aRoomId,  MethodCallback<List<Room>> callback);
	
	
	@POST
	@Path("room")  
	public void createRoom(Room r, MethodCallback<Room> callback);
	
	@PUT
	@Path("room")  
	public void updateRoom(Room r, MethodCallback<Room> callback);
	
	
		
	@GET
	@Path("room/{id}/users")  
	public void getRoomUsers(@PathParam("id") @Attribute("id") Room r, MethodCallback<List<User>> callback );
	
	
	
	//check an ginetai?
	@POST
	@Path("room/{id}/users")  
	public void addUserToRoom(@PathParam("id") String aRoomId, User r, MethodCallback<User> callback );
	
	
	
//	@PUT
//	@Path("room/users") 
//	public void createRoom(Room r, User u,  MethodCallback<Room> callback);
	//den ginetai Methods can optionally declare one method argument before the callback to pass via the request body
	
//	@PUT
//	@Path("room/users") 
//	public void addUserToRoom(RoomUser ru,  MethodCallback<Room> callback);

	
	@GET
	@Path("user")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getUsers( MethodCallback<List<User>> callback);
	
	
	@GET
	@Path("user/{id}")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getUser(@PathParam("id") String aUserId,  MethodCallback<User> callback);
	
}

