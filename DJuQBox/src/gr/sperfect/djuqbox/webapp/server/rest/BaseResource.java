package gr.sperfect.djuqbox.webapp.server.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public abstract class BaseResource {

	@Context
	protected HttpHeaders headers;
	@Context
	protected Request req;
	@Context
	protected UriInfo uri;
	// @Context
	// protected HttpServletResponse servletResponse;
	
	//@Context
	//protected Response response; //menei panta null
	
	@Context
	private HttpServletResponse response;

	private static final Logger logger = java.util.logging.Logger
			.getLogger("BaseResource");

	protected void init() {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, UPDATE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "x-http-method-override");

		
		Log();

	}

	protected void Log() {
		Log(null);

	}

	protected void Log(String message) {
		try {
			logger.log(Level.INFO,
					this.getClass().getSimpleName() + " " + req.getMethod()
							+ " " + this.uri.getPath()
							+ (message == null ? "" : ": " + message));
		} catch (Exception ex) {
		}
	}

	protected void LogError(String message) {
		try {
			logger.log(Level.SEVERE, this.getClass().getSimpleName() + " "
					+ req.getMethod() + " " + this.uri.getPath()
					+ (message == null ? "" : ": " + message));
		} catch (Exception ex) {
		}
	}

}
