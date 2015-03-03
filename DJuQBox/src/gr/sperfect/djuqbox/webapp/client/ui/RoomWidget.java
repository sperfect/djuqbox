package gr.sperfect.djuqbox.webapp.client.ui;

import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.RoomStatus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;


/*
import com.jooink.experiments.mqtt.Client;
import com.jooink.experiments.mqtt.ConnectionLostEvent;
import com.jooink.experiments.mqtt.Destination;
import com.jooink.experiments.mqtt.MessageArrivedEvent;
import com.jooink.experiments.mqtt.Subscription;
import com.jooink.experiments.mqtt.ConnectionLostEvent.Handler;
import com.jooink.experiments.mqtt.lowlevel.ConnectionHandler;
import com.jooink.experiments.mqtt.lowlevel.MqttMessage;
import com.jooink.experiments.mqtt.lowlevel.SubscriptionHandler;
*/


public class RoomWidget extends Composite implements HasText {

	private static RoomWidgetUiBinder uiBinder = GWT.create(RoomWidgetUiBinder.class);

	interface RoomWidgetUiBinder extends UiBinder<Widget, RoomWidget> {
	}

	public RoomWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	private Room fRoom;
	private PlayerControls playerControls;
	
	@UiField
	Button button;
	
	
	@UiField
	PlayerWidget player;

	
	
	private void informClients(RoomStatus roomStatus) {
		//send mqtt
		
	}
	
	class MyControlHandler implements ControlHandler {

		@Override
		public void onStop() {
			getRoom().getRoomStatus().setPlayerStatus(0);
			informClients(getRoom().getRoomStatus());
		}

		

		@Override
		public void onPause() {
			getRoom().getRoomStatus().setPlayerStatus(2);
			
			informClients(getRoom().getRoomStatus());
		}

		@Override
		public void onPlay() {
			getRoom().getRoomStatus().setPlayerStatus(1);
			informClients(getRoom().getRoomStatus());

		}
	}
	
	private MyControlHandler aControlHandler = new MyControlHandler() ;
	

	public RoomWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
		playerControls = new PlayerControls(aControlHandler);
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}
	
	public void updateRoomStatus(RoomStatus rs)
	{
		//check if needs reload
		if (rs.isNeededReload()) {
			//Window.reload kati tetoio
		}
		//update player
		
		//update playlist
		//update users
		//update chat/comments
	}
	
	

	public Room getRoom() {
		return fRoom;
	}

	public void setRoom(Room fRoom) {
		this.fRoom = fRoom;
	}
	
	
	/*
	//listen to room status changes with mqtt
	
	//send
	Client client ;//= new Client("test.mosquitto.org", 8080, "testClientId" + counter );
	
	MqttMessage m = MqttMessage.create(nameField.getText());
	m.setDestinationName("testtt");
	m.setQos(0);
	m.setRetained(false);					

	client.send(m);
	
	//connlost
	
	client.addConnectionLostHandler(new Handler() {

		@Override
		public void onConnectionLost(ConnectionLostEvent e) {
			// TODO Auto-generated method stub
			Window.alert("onConnectionLost");
		}
	});
	
	
	//listen
	
	ConnectionHandler ca = new ConnectionHandler() {

		@Override
		public void onSuccess() {
			// update the header status
			Window.alert("onSuccess");
			// can go ... it is connected

			Destination topic = new Destination("testtt");
			final Subscription subscription = new Subscription(client, topic);

			subscription.subscribe(new SubscriptionHandler() {

				@Override
				public void onSubscriptionSuccess() {
					// TODO Auto-generated method stub
					Window.alert("onSubscriptionSuccess");
					
					subscription.addMessageArrivedHandler(myh);
							
					subscription.addMessageArrivedHandler(new MessageArrivedEvent.Handler() {

						@Override
						public void onMessageArrived(MessageArrivedEvent e) {
							// TODO Auto-generated method stub
							//Window.alert("onMessageArrived" + e.getMessage().getDestinationName());
							listBox.addItem(e.getMessage().getDestinationName() + ": "
									+ e.getMessage().getPayloadString());
						
						}
					});
				}

				@Override
				public void onSubscriptionFailure(int errorCode, String errorText) {
					// TODO Auto-generated method stub
					Window.alert("onSubscriptionFailure " + errorText);
				}
			});
		}

		@Override
		public void onFailure(int errorCode, String errorText) {
			// update the header status
			Window.alert("onFailure " + errorText);

		}
	};

	client.connect(ca, "", "", 60);
	*/

}
