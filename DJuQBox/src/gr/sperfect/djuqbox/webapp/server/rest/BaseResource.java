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
//	@Context
//	protected HttpServletResponse servletResponse;
	@Context
	protected Response response;

	private static final Logger logger = java.util.logging.Logger.getLogger("BaseResource");

	protected void Log() {
		Log(null);
		
	}

	protected void Log(String message) {
		try {
			logger.log(Level.INFO, req.getMethod() + " " + this.uri.getPath() + (message == null ? "" : ": " + message));
		} catch (Exception ex) {
		}
	}

	protected void LogError(String message) {
		try {
			logger.log(Level.SEVERE, req.getMethod() + " " + this.uri.getPath()
					+ (message == null ? "" : ": " + message));
		} catch (Exception ex) {
		}
	}

}
