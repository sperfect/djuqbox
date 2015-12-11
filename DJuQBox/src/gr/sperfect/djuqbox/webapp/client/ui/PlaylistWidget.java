package gr.sperfect.djuqbox.webapp.client.ui;

import gr.sperfect.djuqbox.webapp.shared.data.PlayList;
import gr.sperfect.djuqbox.webapp.shared.data.Song;
import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PlaylistWidget extends Composite implements HasText {

	private static PlaylistWidgetUiBinder uiBinder = GWT
			.create(PlaylistWidgetUiBinder.class);

	interface PlaylistWidgetUiBinder extends UiBinder<Widget, PlaylistWidget> {
	}

	public PlaylistWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	VerticalPanel playListPanel;

	// panel youtubeplayer
	// panel controls

	public PlaylistWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	public void setText(String text) {
		// button2.setText(text);
	}

	public String getText() {
		return "";// button2.getText();
	}

	public void loadPlayList(PlayList playList) {

		
		playListPanel.clear();
		
		GWT.log("count" + playList.getSongs().size());
		// Window.alert("count" + playList.getSongs().size());

		if (playList.getSongs() == null) {
			GWT.log("count 0");
			return;
		}

		for (Song youtubeSong : playList.getSongs()) {

			String _thumbUrl = "";
			if (youtubeSong.getThumbs() != null
					&& youtubeSong.getThumbs().size() > 0) {
				_thumbUrl = youtubeSong.getThumbs().get(0).getUrl();
			}

			ResultEntry re = new ResultEntry(youtubeSong.getTitle(),
					youtubeSong.getCode(), _thumbUrl);

			playListPanel.add(re);

		}
	}

}
