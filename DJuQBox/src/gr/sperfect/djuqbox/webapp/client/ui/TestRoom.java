package gr.sperfect.djuqbox.webapp.client.ui;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import gr.sperfect.djuqbox.webapp.client.DJuQBox;
import gr.sperfect.djuqbox.webapp.shared.data.Room;
import gr.sperfect.djuqbox.webapp.shared.data.RoomStatus;

import com.bramosystems.oss.player.core.event.client.PlayStateEvent;
import com.bramosystems.oss.player.core.event.client.PlayStateHandler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jooink.experiments.mqtt.Client;
import com.jooink.experiments.mqtt.ConnectionLostEvent;
import com.jooink.experiments.mqtt.Destination;
import com.jooink.experiments.mqtt.MessageArrivedEvent;
import com.jooink.experiments.mqtt.Subscription;
import com.jooink.experiments.mqtt.ConnectionLostEvent.Handler;
import com.jooink.experiments.mqtt.lowlevel.ConnectionHandler;
import com.jooink.experiments.mqtt.lowlevel.MqttMessage;
import com.jooink.experiments.mqtt.lowlevel.SubscriptionHandler;

public class TestRoom extends Composite implements HasText , PlayStateHandler{

	private static TestRoomUiBinder uiBinder = GWT
			.create(TestRoomUiBinder.class);

	interface TestRoomUiBinder extends UiBinder<Widget, TestRoom> {
	}

	private Room fRoom;

	public TestRoom() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	@UiField
	PlayerWidget player;

	@UiField
	VerticalPanel mqttLog;

	@UiField
	VerticalPanel clientsList;

	public TestRoom(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);

	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		// Window.alert("Hello!");

		// DJuQBox.SendMqtt("gr/sperfect/djuqbox/"+ fRoom.getName()+"/now",
		// "3gxNW2Ulpwk");
		//player.initPlayer("3gxNW2Ulpwk");

		// player.loadByURL();
		
		sendMqttMessage(mqttRoomDest +"now/" , "3gxNW2Ulpwk", true, 2);
		
		
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

	public Room getRoom() {
		return fRoom;
	}

	public void load(Room room) {

		this.fRoom = room;

		GWT.log("load Room");

		DJuQBox.API.getRoom(room.getUID(), new MethodCallback<Room>() {

			@Override
			public void onSuccess(Method method, Room response) {
				// Window.alert("OK");
				GWT.log("got Room " + response.getName());
				button.setText(response.getName());
				// player.initPlayer("JlYXp_3A64k");

				connectMqtt();
			}

			@Override
			public void onFailure(Method method, Throwable exception) {
				Window.alert("error");
			}
		});

	}

	private void LogMqtt(String msg) {

		mqttLog.insert(new Label(msg), 0);

	}

	static Client mqttClient;// =

	String mqttRoomDest;
	String mqttClientId;

	private void connectMqtt() {

		mqttRoomDest = "/gr/sperfect/djuqbox/room/" + fRoom.getName() + "/";

		int counter = ((int) (Math.random() * 100));
		mqttClientId = "testClientId" + counter;
		mqttClient = new Client("test.mosquitto.org", 8080, mqttClientId);

		mqttClient.addConnectionLostHandler(new Handler() {

			@Override
			public void onConnectionLost(ConnectionLostEvent e) {
				// TODO Auto-generated method stub
				LogMqtt("onConnectionLost");
				connectMqtt();
			}

		});

		final MessageArrivedEvent.Handler myh = new MessageArrivedEvent.Handler() {

			@Override
			public void onMessageArrived(MessageArrivedEvent e) {
				// TODO Auto-generated method stub
				LogMqtt("onMessageArrived: "
						+ e.getMessage().getDestinationName() + " | " + e.getMessage().getPayloadString())  ;
				
				handleMqttMessage(e.getMessage());

			}

		};

		ConnectionHandler ca = new ConnectionHandler() {

			@Override
			public void onSuccess() {
				// update the header status
				LogMqtt("onSuccess");
				// can go ... it is connected

				Destination topic = new Destination(mqttRoomDest + "#");

				final Subscription subscription = new Subscription(mqttClient,
						topic);

				SubscriptionHandler sh = new SubscriptionHandler() {

					@Override
					public void onSubscriptionSuccess() {
						// TODO Auto-generated method stub
						LogMqtt("onSubscriptionSuccess");

						sendMqttPresense();

					}

					@Override
					public void onSubscriptionFailure(int errorCode,
							String errorText) {

						LogMqtt("onSubscriptionFailure " + errorText);
						// try again
						connectMqtt();
					}
				};

				LogMqtt("wait sub ");

				subscription.addMessageArrivedHandler(myh);
				subscription.subscribe(sh);
			}

			@Override
			public void onFailure(int errorCode, String errorText) {
				// update the header status
				LogMqtt("onFailure " + errorText);
				// try again
				connectMqtt();

			}
		};

		mqttClient.connect(ca, "", "", 60);

	}

	protected void handleMqttMessage(MqttMessage mqttMsg) {
		
				
		if(mqttMsg.getDestinationName() == mqttRoomDest + "now/")
		{
			GWT.log(mqttMsg.getPayloadString());
			player.initPlayer(mqttMsg.getPayloadString(), this);
		}
		
	}

	private void sendMqttPresense() {

		sendMqttMessage(mqttRoomDest + "clients/" + mqttClientId.toString()
				+ "/presense/", "1", true, 0);

	}

	private void sendMqttMessage(String dest, String msg, boolean retain,
			int qos) {

		if (!mqttClient.isConnected()) {
			LogMqtt("not connected");
			return;
		}

		// subscription.addMessageArrivedHandler(myh);
		// oxi edw giati 8a xasw to retained
		MqttMessage m = MqttMessage.create(msg);
		m.setDestinationName(dest);
		m.setQos(qos);
		m.setRetained(false);

		mqttClient.send(m);

	}

	@Override
	public void onPlayStateChanged(PlayStateEvent event) {
		
		sendMqttMessage(mqttRoomDest + "clients/" + mqttClientId.toString()
				+ "/player/", event.getPlayState().name(), true, 0);
		
	}

}
