package gr.sperfect.test.restygwy.client;

import gr.sperfect.test.restygwy.shared.FieldVerifier;
import gr.sperfect.test.restygwy.shared.TestOrder;
import gr.sperfect.test.restygwy.shared.TestOrderConfirmation;

import java.util.List;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.bramosystems.oss.player.core.client.AbstractMediaPlayer;
import com.bramosystems.oss.player.core.client.PlayException;
import com.bramosystems.oss.player.core.client.PlayerUtil;
import com.bramosystems.oss.player.core.client.PluginNotFoundException;
import com.bramosystems.oss.player.core.client.PluginVersionException;
import com.bramosystems.oss.player.core.client.spi.PlayerWidget;
import com.bramosystems.oss.player.core.event.client.DebugEvent;
import com.bramosystems.oss.player.core.event.client.DebugHandler;
import com.bramosystems.oss.player.core.event.client.LoadingProgressEvent;
import com.bramosystems.oss.player.core.event.client.LoadingProgressHandler;
import com.bramosystems.oss.player.core.event.client.PlayerStateEvent;
import com.bramosystems.oss.player.core.event.client.PlayerStateHandler;
import com.bramosystems.oss.player.youtube.client.YouTubePlayer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TestRestyGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */

	private AbstractMediaPlayer player = null;

	public void onModuleLoad() {

		// Window.alert("onModuleLoad start");

		Defaults.setServiceRoot(GWT.getHostPageBaseURL() + "rest/");

		final Button sendButton = new Button("Send4");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				// sendNameToServer();

				// Window.alert("pre");
				try {
					player.playMedia();
					// Window.alert("ok");
				} catch (PlayException e) {

					// e.printStackTrace();
					// Window.alert(e.getMessage());
				}
				// Window.alert("after");

				TestSevice ts = GWT.create(TestSevice.class);

				TestOrder to = new TestOrder("t1");
				ts.order(to, new MethodCallback<TestOrderConfirmation>() {

					@Override
					public void onSuccess(Method method, TestOrderConfirmation response) {

						// Window.alert(response.getName());
						// test
						// test2
					}

					@Override
					public void onFailure(Method method, Throwable exception) {

						// Window.alert(exception.getMessage());
					}
				});

				ts.getAll(new MethodCallback<List<TestOrderConfirmation>>() {

					@Override
					public void onSuccess(Method method, List<TestOrderConfirmation> response) {

						// Window.alert(response.get(1).getName());
					}

					@Override
					public void onFailure(Method method, Throwable exception) {

						// Window.alert(exception.getMessage());
					}
				});

			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);

		AddPlayer();

		// //Window.alert("end");
	}

	Timer t;

	private void AddPlayer() {

		final SimplePanel panel = new SimplePanel(); // create panel to hold the
		// player

		try {

			// create the player, specifing URL of media
			// player = new YouTubePlayer("video-id", "width", "height");
			// player = new YouTubeIPlayer("JlYXp_3A64k", "100%", "350px");
			// player = new ChromelessPlayer("JlYXp_3A64k", "100%", "100%");
			// QbwZL-EK6CY
			//player = new YouTubeIPlayer("I-2i2SR_OsU", "350px", "350px");
			
			//player = new YouTubePlayer("I-2i2SR_OsU", "350px", "350px");
			player = new YouTubePlayer("", "350px", "350px");
			
			PlayerWidget pw ;
			//player.

			//PlayerInfo pi = PlayerUtil.getPlayerInfo("bst.youtube", "IYouTube");

			//player = PlayerUtil.getPlayer(pi, "I-2i2SR_OsU", false, "350px", "350px");

			// player.
			// player.add

			// t = new Timer() {
			//
			// @Override
			// public void run() {
			// if (player.getClass() == YouTubeIPlayer.class) {
			// //Window.alert("YouTubeIPlayer ");
			//
			// YouTubePlayerProvider p = new YouTubePlayerProvider();
			//
			// if (p.isIFrameAPIReady())
			// {
			// //Window.alert("YouTubeIPlayer isIFrameAPIReady");
			// }
			// else {
			// //Window.alert("YouTubeIPlayer not isIFrameAPIReady");
			// t.schedule(1);
			// }
			// }
			// }
			// };
			// t.schedule(1);

			// YouTubeIPlayer iplayer = (YouTubeIPlayer) player;
			
			player.addDebugHandler(new DebugHandler() {
				
				@Override
				public void onDebug(DebugEvent event) {
					// TODO Auto-generated method stub
					 //System.out.println("This should print something here " + event.toDebugString());
					// Window.alert("This should print something here " + event.toDebugString());
					 GWT.log(event.toDebugString() + " " + event.getMessage());
				}
			});
			

			player.addPlayerStateHandler(new PlayerStateHandler() {

				@Override
				public void onPlayerStateChanged(PlayerStateEvent event) {

					// Window.alert( event.toDebugString() +
					// " PlayerStateEvent pre");

					if (event.getPlayerState() == PlayerStateEvent.State.Ready) {

						// //Window.alert("PlayerStateEvent pre");
						try {
							player.playMedia();
						} catch (PlayException e) {

							// Window.alert(e.getMessage());
						}
						// //Window.alert("after");

					}

				}
			});

			player.addAttachHandler(new Handler() {

				@Override
				public void onAttachOrDetach(AttachEvent event) {

					// Window.alert(event.toDebugString() + " att");
					// //Window.alert("pre");
					// try {
					// player.playMedia();
					// } catch (PlayException e) {
					//
					// //Window.alert(e.getMessage());
					// }
					// //Window.alert("after");

				}
			});

			player.addLoadingProgressHandler(new LoadingProgressHandler() {

				@Override
				public void onLoadingProgress(LoadingProgressEvent event) {

					// Window.alert(event.toDebugString() + " setWidget pre");
				}
			});

			panel.addAttachHandler(new Handler() {

				@Override
				public void onAttachOrDetach(AttachEvent event) {

					// RootPanel.get("playerPanel").add(panel);

					// Window.alert("panel.addAttachHandler");
					// panel.setWidget(player); // add player to panel.
					// //Window.alert("setWidget after");
				}
			});

			Button b = new Button("test");
			RootPanel.get("playerPanel").add(b);
			b.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// //Window.alert("pre");
					try {
						//player.loadMedia(mediaURL);
						
						player.playMedia();
					} catch (PlayException e) {

						// e.printStackTrace();
						// Window.alert(e.getMessage());
					}
					// //Window.alert("after");
				}
			});

			// //Window.alert("setWidget pre");
			panel.setWidget(player); // add player to panel.
			// //Window.alert("setWidget after");

			// //Window.alert("add panel pre");
			RootPanel.get("playerPanel").add(panel);
			// Window.alert("add panel after");

		} catch (PluginVersionException e) {
			// required Flash plugin version is not available,
			// alert user possibly providing a link to the plugin download page.
			panel.setWidget(new HTML(".. some nice message telling the " + "user to download plugin first .."));
		} catch (PluginNotFoundException e) {
			// required Flash plugin not found, display a friendly notice.
			panel.setWidget(PlayerUtil.getMissingPluginNotice(e.getPlugin()));
		} catch (Exception e) {
			// required Flash plugin version is not available,
			// alert user possibly providing a link to the plugin download page.
			panel.setWidget(new HTML(".. some nice message telling the " + "user to download plugin first . . "
					+ e.getMessage()));
		}

	}
}
