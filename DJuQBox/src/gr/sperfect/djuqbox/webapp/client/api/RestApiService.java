package gr.sperfect.djuqbox.webapp.client.api;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubePlayList;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

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
	
	@POST
	@Path("users")  
	public void createUser(User u, MethodCallback<User> callback);
	
	@PUT
	@Path("users/{user_id}")  
	public void updateUser(@PathParam("user_id") @Attribute("id") User u, MethodCallback<User> callback);
	
	
	@DELETE
	@Path("users/{user_id}")  
	public void deleteUser(@PathParam("user_id") @Attribute("id") User u, MethodCallback<Void> callback );
	
	//YOUTUBE
	
	@GET
	@Path("/youtube/video/{video_id}") 
	public void getYoutubeVideoInfo(@PathParam("video_id") String video_id,  MethodCallback<YoutubeSong> callback);
	
	
	@GET
	@Path("/youtube/search/title/{q}") 
	public void searchYoutubeVideo(@PathParam("q") String q,  MethodCallback<List<YoutubeSong>> callback);
	
	@GET
	@Path("/youtube/playlist/{pl_id}") 
	public void getYoutubePlayList(@PathParam("pl_id") String aPlayListId,  MethodCallback<YoutubePlayList> callback);
	
	
	@GET
	@Path("/youtube/video/{video_id}/mix") 
	public void getYoutubeMixForSong(@PathParam("video_id") String video_id,  MethodCallback<YoutubePlayList> callback);
}

