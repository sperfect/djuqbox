package gr.sperfect.djuqbox.webapp.server.rest;

import java.io.IOException;

import gr.sperfect.djuqbox.webapp.server.youtube.YoutubeApi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.api.services.youtube.model.Video;

@Path("/youtube")
public class YoutubeResource extends BaseResource {

	@GET
	@Path("/search/video/{video_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Video getRoom(@PathParam("video_id") String video_id) throws Exception {

		
		return YoutubeApi.getVideoInfo(video_id);
	}
}
