package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(@Context HttpHeaders headers) {

		List<User> ret = new ArrayList<User>();
		ret.add(new User("user0"));
		ret.add(new User("user1"));

		return ret;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{user_id}")
	public User getUser(@Context HttpHeaders headers, @PathParam("user_id") String aUserId) {

		User u =  new User("user0" + aUserId);
		u.setId(aUserId);
		return u;
	}

}
