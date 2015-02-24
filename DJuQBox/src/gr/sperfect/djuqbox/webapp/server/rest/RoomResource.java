package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.server.db.DBHelper;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;
import gr.sperfect.djuqbox.webapp.shared.data.UserRoomRole;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/rooms")
public class RoomResource extends BaseResource {

	public RoomResource() {

	}

	// private static IDB db = DbOjectify.getInstance();
	// IDB GetDB() {
	// //make it super
	// return DbOjectify.getInstance();
	// }

	private static final Logger logger = java.util.logging.Logger.getLogger("RoomResource");

	IDB<Room> db = DBHelper.getDB(Room.class);

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getRooms(@Context HttpHeaders headers) {

		Log();
		
		List<Room> ret = db.getAllObjects(null);

		return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Room createRoom(Room aRoomParam) {

		Log();
		
		Room newRoom = db.createObject(aRoomParam);

		// save assign...
		
		servletResponse.setStatus(201);
		return newRoom;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Room updateRoom(Room aRoomParam) {

		Log();
		
		Room newRoom = db.updateObject(aRoomParam);

		// save assign...
		return newRoom;
	}

	@DELETE
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRoom(Room aRoomParam) {

		Log();
		
		db.deleteObject(aRoomParam);

	}

	@DELETE
	@Path("{id}")
	public void deleteRoom(@PathParam("id") Long aRoomId) {

		Log();
		
		db.deleteObject(db.getObjectById(aRoomId));

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/users")
	public List<User> getRoomUsers(@PathParam("id") String aRoomId) {

		Log();
		
		return getRoom(aRoomId).getUsers();
		// List<User> ret = new ArrayList<User>();
		// ret.add(new User(aRoomId + " user123"));
		// ret.add(new User(aRoomId + " user1"));
		//
		// //UserResource ur = new UserResource();
		// //ur.getUsers("room_id")
		// return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/users")
	public void addUserToRoom(@PathParam("id") Long aRoomId, User aUser) {
		
		Log();
		
		Room r = db.getObjectById(aRoomId);
		r.getUsers().add(aUser);
		db.updateObject(r);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/users/{user_id}/roles")
	public List<UserRoomRole> getUserRoomRoles(@PathParam("id") Long aRoomId, @PathParam("user_id") Long aUserId) {

		Log();
		
		UserRoomRole urr = new UserRoomRole();
		urr.setRoomId(aRoomId);
		urr.setUserId(aUserId);
		urr.setRoleId(0L);

		ArrayList<UserRoomRole> ret = new ArrayList<UserRoomRole>();
		ret.add(urr);
		return ret;
	}

	// check pws 8a ginei otan pataei to link / room/id sto url
	// epistrefei sto /room/
	// argotera to blepoume an xreaizetai
	// @GET
	// @Produces(MediaType.TEXT_HTML)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// @Path("{id}")
	// public void getRoomHTML(@PathParam("id") String id, @Context
	// HttpServletResponse servletResponse) {
	// try {
	// servletResponse.sendRedirect("http://127.0.0.1:8888/DJuQBox.html");
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // response.seeOther("../teset.html");
	//
	// }



	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Room getRoom(@PathParam("id") String id) {

		Log();

		Room r = new Room("room0 " + id);
		r = db.createObject(r);

		Long idL = r.id;

		Room rr = db.getObjectById(idL);

		r = db.getObject(r);

		r = db.updateObject(r);

		db.deleteObject(r);

		return r;
	}

	

}
