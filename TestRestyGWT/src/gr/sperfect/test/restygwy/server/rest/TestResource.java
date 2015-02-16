package gr.sperfect.test.restygwy.server.rest;

import gr.sperfect.test.restygwy.shared.TestOrder;
import gr.sperfect.test.restygwy.shared.TestOrderConfirmation;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test1")
public class TestResource {

	@POST
	//@Produces("application/json")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TestOrderConfirmation sayJSONHello(TestOrder to) {
		TestOrderConfirmation toc = new TestOrderConfirmation(to.getName());

		return toc;
	}
}
