package gr.sperfect.djuqbox.webapp.server.rest;

import gr.sperfect.djuqbox.webapp.server.youtube.YoutubeApi;
import gr.sperfect.djuqbox.webapp.shared.data.Thumbnail;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubePlayList;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;

@Path("/youtube")
public class YoutubeResource extends BaseResource {

	@GET
	@Path("/video/{video_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public YoutubeSong getSong(@PathParam("video_id") String video_id) throws Exception {

		Video v = YoutubeApi.getVideoInfo(video_id);
		if (v == null) {
			return null;
		}

		YoutubeSong s = new YoutubeSong();
		s.setTitle(v.getSnippet().getTitle());
		s.setUri(v.getId());

		Thumbnail t = new Thumbnail();
		t.setUrl(v.getSnippet().getThumbnails().getDefault().getUrl());
		//t.setWidth(v.getSnippet().getThumbnails().getDefault().getWidth());
		//t.setHeight(v.getSnippet().getThumbnails().getDefault().getHeight());
		s.getThumbs().add(t);

		return s;

	}

	@GET
	@Path("/search/title/{q}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<YoutubeSong> searchSong(@PathParam("q") String q) throws Exception {

		List<YoutubeSong> searchList = new ArrayList<YoutubeSong>();

		List<SearchResult> searchRes = YoutubeApi.searchSong(q);

		if (searchRes == null) {
			return null;
		}

		for (SearchResult res : searchRes) {
			YoutubeSong s = new YoutubeSong();
			s.setTitle(res.getSnippet().getTitle());
			s.setUri(res.getId().getVideoId());
			s.setCode(res.getId().getVideoId());

			Thumbnail t = new Thumbnail();
			t.setUrl(res.getSnippet().getThumbnails().getDefault().getUrl());
			//t.setWidth(res.getSnippet().getThumbnails().getDefault().getWidth());
			//t.setHeight(res.getSnippet().getThumbnails().getDefault().getHeight());
			s.getThumbs().add(t);
			searchList.add(s);
		}

		return searchList;

	}

	@GET
	@Path("/playlist/{pl_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public YoutubePlayList getPlayList(@PathParam("pl_id") String aPlayListId) throws Exception {
		
		YoutubePlayList ypl = new YoutubePlayList();
		
		List<PlaylistItem> res = YoutubeApi.getPlayList(aPlayListId);
		for (PlaylistItem song : res) {
			YoutubeSong ys = new YoutubeSong();
			
			ys.setTitle(song.getSnippet().getTitle());
			ys.setUri(song.getSnippet().getResourceId().getVideoId());
			ys.setCode(song.getSnippet().getResourceId().getVideoId());

			Thumbnail t = new Thumbnail();
			t.setUrl(song.getSnippet().getThumbnails().getDefault().getUrl());
			//t.setWidth(res.getSnippet().getThumbnails().getDefault().getWidth());
			//t.setHeight(res.getSnippet().getThumbnails().getDefault().getHeight());
			ys.getThumbs().add(t);
			
			ypl.addSong(ys);
		}
		
		return ypl;
		
	}

	@GET
	@Path("/video/{video_id}/mix")
	@Produces(MediaType.APPLICATION_JSON)
	public YoutubePlayList getYoutubeMixForSong(@PathParam("video_id") String video_id) throws Exception {

		return getPlayList("RD" + video_id);

	}

}
