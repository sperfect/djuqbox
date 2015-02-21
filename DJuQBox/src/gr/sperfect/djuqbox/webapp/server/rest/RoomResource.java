package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/room")
public class RoomResource {

	@Context
	HttpHeaders headers;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Room> getRooms(@Context HttpHeaders headers) {

		List<Room> ret = new ArrayList<Room>();
		ret.add(new Room("room0"));
		ret.add(new Room("room1"));

		// for(String header : headers.getRequestHeaders().keySet()){
		// System.out.println(header +": " + headers.getRequestHeader(header));
		// }

		return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Room createRoom(Room aRoomParam) {
		Room newRoom = new Room(aRoomParam.getName());
		// save assign...
		return newRoom;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/users")
	public List<User> getRoomUsers(@PathParam("id") String aRoomId) {

		List<User> ret = new ArrayList<User>();
		ret.add(new User(aRoomId + " user123"));
		ret.add(new User(aRoomId + " user1"));

		return ret;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}/users")
	public void addUserToRoom(@PathParam("id") String aRoomId, User aUser) {
		Room newRoom = new Room(aRoomId + " " + aUser.getName());

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

		Room r = new Room("room0 " + id);
		r.setDate1(new Date());
		return r;
	}

}
