package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.server.db.DBHelper;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

@Path("/users")
public class UserResource {

	private static final Logger logger = java.util.logging.Logger.getLogger("RoomResource");

	IDB<User> db = DBHelper.getDB(User.class);

	@Context
	HttpHeaders headers;
	@Context
	Request req;
	@Context
	UriInfo uri;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {

		List<User> ret = db.getAllObjects(null);
		

		return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User aUserParam) {
		User newUser = db.createObject(aUserParam);
		// save assign...
		return newUser;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateRoom(User aUserParam) {
		
		User newUser =  db.updateObject(aUserParam);
		
		// save assign...
		return newUser;
	}
	
	@DELETE
	//@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRoom(User aUserParam) {
		
		db.deleteObject(aUserParam);
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{user_id}")
	public User getUser(@PathParam("user_id") String aUserId) {

		Log(aUserId.toString());
		
		User u = new User(aUserId);
		u = db.createObject(u);
		Long idL = u.id;
		u  = db.getObjectById(idL);
		u = db.updateObject(u);

		db.deleteObject(u);
	
		return u;
	}

	


	
	private void Log(String message) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, req.getMethod() + " " + message);
	}

}
