package gr.sperfect.djuqbox.webapp.shared;

import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.DirectRestService;
import org.fusesource.restygwt.client.MethodCallback;

public interface RestApiInterface extends DirectRestService {

	//http://resty-gwt.github.io/documentation/restygwt-user-guide.html
	//Reusing server interfaces.
	//REST.withCallback().call()
	
	@GET
	@Path("room") 
	//h parametros 8a einai panta null ston client. kaleite
	public List<Room> getRooms();
	
	
}
