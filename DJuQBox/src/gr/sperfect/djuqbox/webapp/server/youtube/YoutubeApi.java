package gr.sperfect.djuqbox.webapp.server.youtube;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

//http://developers.google.com/apis-explorer/?hl=en_US#p/youtube/v3/
public class YoutubeApi {

	//static 'h 8a xanetai???
	public static final YouTube youtubeInst = new YouTube.Builder(UrlFetchTransport.getDefaultInstance(),
			JacksonFactory.getDefaultInstance(), new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("djuqbox").build();

	public static String getKey() {
		//prepei na ginei parametriko apo file
		return "AIzaSyBUfgJRmOS7loeG1EPaK9gLxdSqXltCfGg"; // created on 25/02/2015
	}

	
	public static List<PlaylistItem> getRelatedList(String ybId) throws IOException
	{
		List<PlaylistItem> playList = new ArrayList<PlaylistItem>();
		
		YouTube.PlaylistItems.List playlistItemRequest = youtubeInst.playlistItems().list("id,contentDetails,snippet");
		playlistItemRequest.setPlaylistId("RD" + ybId);
		playlistItemRequest.setKey(YoutubeApi.getKey());
		playlistItemRequest.setMaxResults((long) 50);

		PlaylistItemListResponse playlistItemResult = playlistItemRequest.execute();
		playList.addAll(playlistItemResult.getItems());
		
		return playList;
	}
	
	public static Video getVideoInfo(String ybId) throws Exception{
		
		YouTube.Videos.List list = youtubeInst.videos().list("snippet").setId(ybId).setKey(getKey()).setFields("items(kind,id,snippet(title,categoryId,publishedAt,thumbnails)),pageInfo");
		
		List<Video> res = list.execute().getItems();
		if (res.isEmpty())
		{
			throw new Exception("video not found:" + ybId);
		}
				
		return res.get(0);
		
	}


	public static List<SearchResult> searchSong(String q) throws Exception {
		
		YouTube.Search.List search = youtubeInst.search().list("snippet");

		

		search.setKey(YoutubeApi.getKey()); // created on 25/02/2015
		search.setQ(q);
		
		search.setType("video");
		search.setVideoCategoryId("10");

		search.setFields("items(id/kind,id/videoId,snippet(publishedAt,title,thumbnails/default/url))");
		search.setMaxResults((long) 20);

		return search.execute().getItems();

		
	}
	
	public static List<PlaylistItem> getPlayList(String aPlaylistId) throws Exception{
		
		YouTube.PlaylistItems.List playlistItemRequest = youtubeInst.playlistItems().list(
				"id,contentDetails,snippet");
		playlistItemRequest.setPlaylistId(aPlaylistId);//"RD" + searchResult.getId().getVideoId());
		playlistItemRequest.setKey(getKey());
		playlistItemRequest.setMaxResults((long) 50);
		playlistItemRequest.setFields("items(snippet(resourceId(videoId),publishedAt,title,thumbnails/default/url))");
		
		return playlistItemRequest.execute().getItems();
		
	}
	
	

	
}
