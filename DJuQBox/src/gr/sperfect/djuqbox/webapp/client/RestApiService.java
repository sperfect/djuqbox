package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface RestApiService extends RestService {


	//http://resty-gwt.github.io/documentation/restygwt-user-guide.html
	
	@GET
	@Path("room")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getRooms( MethodCallback<List<Room>> callback);
	
	@GET
	@Path("room/users")  
	public void getRoomUsers(Room r, MethodCallback<List<Room>> callback );
	
	@POST
	@Path("room")  
	public void createRoom(Room r, MethodCallback<Room> callback);
	
	@PUT
	@Path("room/users") 
	public void createRoom(Room r, User u,  MethodCallback<Room> callback);
}
