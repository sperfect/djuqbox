package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

public interface RestApiService extends RestService {

//	@POST
//	@Path("test1")  // prepei na einai javax.ws.rs.Path !!!!!
//	public void order(TestOrder request, MethodCallback<TestOrderConfirmation> callback);
	
	@GET
	@Path("room")  // prepei na einai javax.ws.rs.Path !!!!!
	public void getAll( MethodCallback<List<Room>> callback);
}
