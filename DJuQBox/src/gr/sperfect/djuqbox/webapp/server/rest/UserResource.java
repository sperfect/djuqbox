package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.server.db.DBHelper;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.User;

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

@Path("/users")
public class UserResource extends BaseResource {

	static final IDB<User> db = DBHelper.getDB(User.class);

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {

		init();
		
		List<User> ret = db.getAllObjects(null);

		return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User aUserParam) throws Exception {
		
		init();
		
		if (aUserParam.getName() == null || aUserParam.getName() == "")
		{
			throw new Exception("name needed for user");
		}
		
		//check if user exists
		if(db.findObjectWithValue("name", aUserParam.getName()) != null)
		{
			throw new Exception("user "+ aUserParam.getName()+" already existrs");
		}
			
		User newUser = db.createObject(aUserParam);
		// save assign...

		Response.status(201);
		return newUser;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateRoom(User aUserParam) {

		init();
		
		User user = db.updateObject(aUserParam);

		// save assign...
		return user;
	}

	@DELETE
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteRoom(User aUserParam) {

		init();
		
		db.deleteObject(aUserParam);

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{user_id}")
	public User getUser(@PathParam("user_id") Long aUserId) {

		init();

		User u = db.getObjectById(aUserId);
				
		return u;		

	}

}
