package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.server.db.DBHelper;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;
import gr.sperfect.djuqbox.webapp.shared.data.UserRoomRole;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/rooms")
public class RoomResource extends BaseResource {

	public RoomResource() {

	}

	

	static final IDB<Room> db = DBHelper.getDB(Room.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getRooms() {

		Log();

		List<Room> ret = db.getAllObjects(null);

		return ret;
	}

	@GET
	@Path("{room_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Room getRoom(@PathParam("room_id") Long aRoomId) {

		Log();		

		Room r = db.getObjectById(aRoomId);
		
		return r;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Room createRoom(Room aRoomParam) throws Exception {

		Log();
		
		if (aRoomParam.getName() == null || aRoomParam.getName() == "")
		{
			throw new Exception("room needed for user");
		}
		
		//check if room exists
		if(db.findObjectWithValue("name", aRoomParam.getName()) != null)
		{
			throw new Exception("room "+ aRoomParam.getName()+" already existrs");
		}

		Room newRoom = db.createObject(aRoomParam);

		// save assign...

		response.status(201);
		return newRoom;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{room_id}")
	public Room updateRoom(Room aRoomParam) {

		Log();

		Room newRoom = db.updateObject(aRoomParam);

		// save assign...
		return newRoom;
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRoom(Room aRoomParam) {

		Log();

		db.deleteObject(aRoomParam);

	}

	@DELETE
	public void deleteRoom() throws Exception {

		Log();

		throw new Exception("not allowed");

	}

	@DELETE
	@Path("{room_id}")
	public void deleteRoom(@PathParam("room_id") Long aRoomId) {

		Log();

		db.deleteObject(db.getObjectById(aRoomId));

	}

	// /users
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{room_id}/users")
	public List<User> getRoomUsers(@PathParam("room_id") Long aRoomId) {

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

	// den thn briskei h Resty
	@POST
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{room_id}/users")
	public void addUserToRoom(@PathParam("room_id") Long aRoomId, User aUser) throws Exception {

		Log();
		
		response.status(Status.CREATED);

		Room r = db.getObjectById(aRoomId);
		if (r == null) {

			throw new Exception("room not exists " + aRoomId);
		}

		// if not contains
		if (r.getUsers().contains(aUser.getUID())) {
			// javax.ws.rs.core.Response. Status
			response.status(Status.NOT_MODIFIED);
			return;
		}
		r.getUsers().add(aUser);
		db.updateObject(r);
		response.status(Status.CREATED);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{room_id}/users/{user_id}/roles")
	public List<UserRoomRole> getUserRoomRoles(@PathParam("room_id") Long aRoomId, @PathParam("user_id") Long aUserId) {

		Log();

		UserRoomRole urr = new UserRoomRole();
		urr.setRoomId(aRoomId);
		urr.setUserId(aUserId);
		urr.setRoleId(0L);

		ArrayList<UserRoomRole> ret = new ArrayList<UserRoomRole>();
		ret.add(urr);
		return ret;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/by/{field}/{value}") //mporei k to name na ginei
	public Room getRoomByValue(@PathParam("field") String aField, @PathParam("value") String aValue) {

		Log();

		Room ret = db.findObjectWithValue(aField, aValue);

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

	// @GET
	// @Path("{id}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public Room getRoom(@PathParam("id") String id) {
	//
	// // den ginetai me allo typo (string anti gia Long)
	// // org.glassfish.jersey.server.model.ModelValidationException:
	// // Validation of the application resource model has failed during
	// // application initialization.
	// // [[FATAL] A resource model has ambiguous (sub-)resource method for
	// // HTTP method GET and input mime-types as defined by"@Consumes" and
	// // "@Produces" annotations at Java methods public
	// // gr.sperfect.djuqbox.webapp.shared.data.Room
	// // gr.sperfect.djuqbox.webapp.server.rest.RoomResource.getRoom(java.lang.String)
	// // and public gr.sperfect.djuqbox.webapp.shared.data.Room
	// // gr.sperfect.djuqbox.webapp.server.rest.RoomResource.getRoom(java.lang.Long)
	// // at matching regular expression /([^/]+). These two methods produces
	// // and consumes exactly the same mime-types and therefore their
	// // invocation as a resource methods will always fail.;
	// // source='org.glassfish.jersey.server.model.RuntimeResource@1b2a81']
	//
	// }
}
