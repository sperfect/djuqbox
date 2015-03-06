package gr.sperfect.djuqbox.webapp.server.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

//http://developers.google.com/apis-explorer/?hl=en_US#p/youtube/v3/
public class YoutubeSearcher extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");

		try {
			// YouTube youtube = new YouTube.Builder(UrlFetchTransport.getDefaultInstance(),
			// JacksonFactory.getDefaultInstance(), new HttpRequestInitializer() {
			// public void initialize(HttpRequest request) throws IOException {
			// }
			// }).setApplicationName("djuqbox").build();

			YouTube youtube = YoutubeApi.youtubeInst;
			YouTube.Search.List search = youtube.search().list("id,snippet");

			String queryTerm = "radiohead";

			search.setKey(YoutubeApi.getKey()); // created on 25/02/2015
			search.setQ(queryTerm);
			
			search.setType("video");
			search.setVideoCategoryId("10");

			// search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
			search.setMaxResults((long) 50);

			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();

			if (searchResultList != null) {
				// resp.getWriter().println(
				// prettyPrint(searchResultList.iterator(), queryTerm));

				for (SearchResult searchResult : searchResultList) {

					resp.getWriter().println("----");
					resp.getWriter().println(searchResult.getSnippet().getTitle());
					
					YoutubeApi.getVideoInfo(searchResult.getId().getVideoId().toString());

					List<PlaylistItem> playlistItemList = new ArrayList<PlaylistItem>();

					YouTube.PlaylistItems.List playlistItemRequest = youtube.playlistItems().list(
							"id,contentDetails,snippet");
					playlistItemRequest.setPlaylistId("RD" + searchResult.getId().getVideoId());
					playlistItemRequest.setKey(YoutubeApi.getKey());
					playlistItemRequest.setMaxResults((long) 50);

					PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();
					playlistItemList.addAll(playlistItemResult.getItems());

					Iterator<PlaylistItem> playlistEntries = playlistItemList.iterator();

					while (playlistEntries.hasNext()) {
						PlaylistItem playlistItem = playlistEntries.next();
						
						resp.getWriter().println(" video name  = " + playlistItem.getSnippet().getTitle());
					}

				}

				Logger.getGlobal().log(Level.INFO, "OK");

			}
		} catch (Exception ex) {

			// resp.getWriter().println(ex.getCause());
			resp.getWriter().println(ex.getMessage());
		}

	}

	
}
