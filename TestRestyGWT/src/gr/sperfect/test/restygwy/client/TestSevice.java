package gr.sperfect.test.restygwy.client;

import gr.sperfect.test.restygwy.shared.TestOrder;
import gr.sperfect.test.restygwy.shared.TestOrderConfirmation;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;


public interface TestSevice extends RestService {

	@POST
	@Path("test1")  // prepei na einai javax.ws.rs.Path !!!!!
	public void order(TestOrder request, MethodCallback<TestOrderConfirmation> callback);
} 