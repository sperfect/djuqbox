package gr.sperfect.djuqbox.webapp.client;

import gr.sperfect.djuqbox.webapp.shared.data.User;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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

		final Button sendButton = new Button("Send test3");
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

				User u = new User("test");
				u.id = 2L;

				api.addUserToRoom(1L, u, new MethodCallback<Void>() {

					@Override
					public void onSuccess(Method method, Void response) {
						Window.alert("OK");

					}

					@Override
					public void onFailure(Method method, Throwable exception) {

						Log("test ", method, exception);

					}

				});

			}

			@Override
			public void onKeyUp(KeyUpEvent event) {

			}

		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);

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
