package gr.sperfect.djuqbox.webapp.client.ui;

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

public class PlaylistWidget extends Composite implements HasText {

	private static PlaylistWidgetUiBinder uiBinder = GWT.create(PlaylistWidgetUiBinder.class);

	interface PlaylistWidgetUiBinder extends UiBinder<Widget, PlaylistWidget> {
	}

	public PlaylistWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button2;
	
	//panel youtubeplayer
	//panel controls

	public PlaylistWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button2.setText(firstName);
	}

	@UiHandler("button2")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button2.setText(text);
	}

	public String getText() {
		return button2.getText();
	}

}
