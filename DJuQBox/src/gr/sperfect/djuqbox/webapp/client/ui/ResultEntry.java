package gr.sperfect.djuqbox.webapp.client.ui;

import gr.sperfect.djuqbox.webapp.client.DJuQBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResultEntry extends Composite implements HasText, ClickHandler {

	private static ResultEntryUiBinder uiBinder = GWT
			.create(ResultEntryUiBinder.class);

	interface ResultEntryUiBinder extends UiBinder<Widget, ResultEntry> {
	}

	public ResultEntry() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	// @UiField
	Hyperlink hlink;

	// @UiField
	PushButton thumb;

	@UiField
	HorizontalPanel par;

	VerticalPanel vp;
	
	String url;

	public ResultEntry(String caption, String aUrl, String thumbUrl) {
		initWidget(uiBinder.createAndBindUi(this));

		try {
			if (thumbUrl == "") {
				thumbUrl = "https://i.ytimg.com/"; //default
			}
			
			url = aUrl;

			PushButton pb = new PushButton(new Image(thumbUrl));
			pb.addClickHandler(this);
			par.add(pb);
			vp = new VerticalPanel();
			
			Hyperlink hl = new Hyperlink(caption, aUrl);
			hl.addClickHandler(this);			
			vp.add(hl);
			par.add(vp);

		} catch (Exception ex) {
			GWT.log("problem loading thumbnail " + aUrl, ex);
		}
	}

	
	@Override
	public void onClick(ClickEvent event) {
		 
		 TestRoom.setSong(url);
		
	}

	

	public void setText(String text) {
		// hlink.setText(text);
	}

	public String getText() {
		return "test";// button.getText();
	}


	

}
