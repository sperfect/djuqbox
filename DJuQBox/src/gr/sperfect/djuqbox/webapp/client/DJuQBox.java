package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.client.api.RestApiService;
import gr.sperfect.djuqbox.webapp.client.api.RoomStatusDataCodec;
import gr.sperfect.djuqbox.webapp.client.ui.ControlHandler;
import gr.sperfect.djuqbox.webapp.client.ui.PlayerControls;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.RoomStatus;
import gr.sperfect.djuqbox.webapp.shared.data.User;

import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
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

		// class MyRestHandler implements MethodCallback {
		//
		// @Override
		// public void onFailure(Method method, Throwable exception) {
		// Log("", method, exception);
		//
		// }
		//
		// @Override
		// public void onSuccess(Method method, Object response) {
		//
		// //select what to do
		// Window.alert("OK " + response.toString());
		// //douleuei, alla 8a 8elei poly iffff
		// }
		// }

		// final MyRestHandler restHandler = new MyRestHandler();

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler /* , MethodCallback */{
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {

				GWT.log(event.getSource().toString());

				TestResty();

				//TestCodeDecodeJson();

			}

			private void TestCodeDecodeJson() {
				//https://resty-gwt.github.io/documentation/restygwt-user-guide.html
				//JSON Encoder/Decoders
					
				RoomStatus r1 = new RoomStatus();
				r1.setCode("tttt");

				RoomStatusDataCodec codec = GWT.create(RoomStatusDataCodec.class);

				JSONValue json = codec.encode(r1);
				Window.alert(json.toString());

				JSONValue jsonV = JSONParser.parseStrict(json.toString());

				RoomStatus r2 = codec.decode(jsonV);

				Window.alert(r2.getCode());

			}

			private void TestResty() {
				final RestApiService api = GWT.create(RestApiService.class);

				api.createUser(new User("test user"), new MethodCallback<User>() {

					@Override
					public void onSuccess(Method method, User uRes) {

						Window.alert("OK " + uRes.getUID());

						api.getUser(uRes.getUID(), new MethodCallback<User>() {

							@Override
							public void onSuccess(Method method, User u) {
								Window.alert("OK2 " + u.getUID());

							}

							@Override
							public void onFailure(Method method, Throwable exception) {
								Log("getUser ", method, exception);

							}
						});

					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						Log("createUser ", method, exception);

					}
				});

				// api.getYoutubeMixForSong(searchField.getText(), new
				// MethodCallback<YoutubePlayList>() {
				//
				// @Override
				// public void onSuccess(Method method, YoutubePlayList pl) {
				// Window.alert("OK " + pl.getSongs().get(2).getTitle());
				// Log("getYoutubeMixForSong size:" +pl.getSongs().size());
				// }
				//
				// @Override
				// public void onFailure(Method method, Throwable exception) {
				// // TODO Auto-generated method stub
				// Log("getYoutubeMixForSong ", method, exception);
				// }
				// });

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

		RootPanel.get("playerContainer").add(playerOut);

		class MyControlHandler implements ControlHandler {

			@Override
			public void onStop() {
				playerOut.setText("Clicked Stop");
			}

			@Override
			public void onPause() {

				playerOut.setText("Clicked Pause");
				getRooms();
			}

			@Override
			public void onPlay() {

				playerOut.setText("Clicked Play");

			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		searchField.addKeyUpHandler(handler);

		MyControlHandler controlHandler = new MyControlHandler();

		DOM.getElementById("loading").removeFromParent();

		PlayerControls pc = new PlayerControls(controlHandler);
		// pc.setText("test");

		RootPanel.get("playerControlsContainer").add(pc);

		Timer t = new Timer() {

			@Override
			public void run() {

				getRooms();

			}
		};

		t.scheduleRepeating(5000);

	}

	final Label playerOut = new Label();

	RestApiService api = GWT.create(RestApiService.class);

	protected void getRooms() {

		api.getRooms(new MethodCallback<List<Room>>() {

			@Override
			public void onSuccess(Method method, List<Room> rooms) {
				// TODO Auto-generated method stub
				playerOut.setText("got " + rooms.size() + "rooms, updated " + new Date().toString());
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				// TODO Auto-generated method stub
				Log("getRooms ", method, exception);
			}
		});

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
