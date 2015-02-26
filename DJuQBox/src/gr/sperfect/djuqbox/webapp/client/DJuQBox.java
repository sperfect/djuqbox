package gr.sperfect.djuqbox.webapp.client;

import java.util.List;

import gr.sperfect.djuqbox.webapp.shared.data.User;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubePlayList;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.api.services.youtube.model.Video;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DJuQBox implements EntryPoint {

	// http://blog.javaforge.net/post/30469901979/gwt-rest
	static {
		// if you don't do this, on JSON response you'll get something like
		// this:
		// "Could not parse response: org.fusesource.restygwt.client.ResponseFormatException: Response was NOT a valid JSON document"
		Defaults.setDateFormat(null);
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// set RestyGWT roor url
		Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "api/v1/");
		// Defaults.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Defaults.setDateFormat(null);

		// loading splash screen
		// https://turbomanage.wordpress.com/2009/10/13/how-to-create-a-splash-screen-while-gwt-loads/
		// http://jamestaylor2000.com/jukebox/spinningrecord.gif
		// http://www.ajaxload.info/

		final Button sendButton = new Button("Send test3");
		final TextBox searchField = new TextBox();
		// searchField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(searchField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		searchField.setFocus(true);
		searchField.selectAll();
		searchField.setText("kd4QMN_lErc"); // kd4QMN_lErc //Belle and sebastian

//		class MyRestHandler implements MethodCallback {
//
//			@Override
//			public void onFailure(Method method, Throwable exception) {
//				Log("", method, exception);
//				
//			}
//
//			@Override
//			public void onSuccess(Method method, Object response) {
//				
//				//select what to do				
//				Window.alert("OK " + response.toString());
//				//douleuei, alla 8a 8elei poly iffff
//			}
//		}
		
		//final MyRestHandler restHandler = new MyRestHandler();

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler /* , MethodCallback */{
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {

				GWT.log(event.getSource().toString());

				TestResty();

			}

			private void TestResty() {
				RestApiService api = GWT.create(RestApiService.class);
				
				

				api.getYoutubeMixForSong(searchField.getText(), new MethodCallback<YoutubePlayList>() {

					@Override
					public void onSuccess(Method method, YoutubePlayList pl) {
						Window.alert("OK " + pl.getSongs().get(2).getTitle());
						Log("getYoutubeMixForSong size:" +pl.getSongs().size());
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						// TODO Auto-generated method stub
						Log("getYoutubeMixForSong ", method, exception);
					}
				});

				// api.searchYoutubeVideo(searchField.getText(), new
				// MethodCallback<List<YoutubeSong>>() {
				//
				// @Override
				// public void onSuccess(Method method, List<YoutubeSong>
				// response) {
				// Window.alert("OK " + response.get(0).getTitle());
				// //kd4QMN_lErc
				//
				// }
				//
				// @Override
				// public void onFailure(Method method, Throwable exception) {
				// Log("searchYoutubeVideo ", method, exception);
				//
				// }
				// });

			}

			@Override
			public void onKeyUp(KeyUpEvent event) {

			}

		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		searchField.addKeyUpHandler(handler);

		DOM.getElementById("loading").removeFromParent();
	}

	private void Log(String message, Method method, Throwable ex) {
		// TODO Auto-generated method stub
		GWT.log("ERROR: " + ex.toString() + " message=" + message + " METHOD= " + method.builder.getHTTPMethod() + " "
				+ method.builder.getUrl() + " " + method.builder.getRequestData());
	}

	@SuppressWarnings("unused")
	private void Log(String message, Method method) {
		// TODO Auto-generated method stub
		GWT.log("INFO: message=" + message + " METHOD= " + method.builder.getHTTPMethod() + " "
				+ method.builder.getUrl() + " " + method.builder.getRequestData());
	}

	@SuppressWarnings("unused")
	private void Log(String message) {
		GWT.log(message);
	}

	@SuppressWarnings("unused")
	private void Log(String message, Throwable ex) {

		GWT.log(message, ex);
	}
}
