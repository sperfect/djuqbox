package gr.sperfect.djuqbox.webapp.client.ui;

import com.bramosystems.oss.player.core.client.AbstractMediaPlayer;
import com.bramosystems.oss.player.core.client.PlayException;
import com.bramosystems.oss.player.core.client.PlayerUtil;
import com.bramosystems.oss.player.core.client.PluginNotFoundException;
import com.bramosystems.oss.player.core.client.PluginVersionException;
import com.bramosystems.oss.player.core.event.client.DebugEvent;
import com.bramosystems.oss.player.core.event.client.DebugHandler;
import com.bramosystems.oss.player.core.event.client.LoadingProgressEvent;
import com.bramosystems.oss.player.core.event.client.LoadingProgressHandler;
import com.bramosystems.oss.player.core.event.client.MediaInfoEvent;
import com.bramosystems.oss.player.core.event.client.MediaInfoHandler;
import com.bramosystems.oss.player.core.event.client.PlayStateEvent;
import com.bramosystems.oss.player.core.event.client.PlayStateHandler;
import com.bramosystems.oss.player.core.event.client.PlayerStateEvent;
import com.bramosystems.oss.player.core.event.client.PlayerStateEvent.State;
import com.bramosystems.oss.player.core.event.client.PlayerStateHandler;
import com.bramosystems.oss.player.youtube.client.ChromelessPlayer;
import com.bramosystems.oss.player.youtube.client.YouTubePlayer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlayerWidget extends Composite implements HasText {

	private static PlayerWidgetUiBinder uiBinder = GWT.create(PlayerWidgetUiBinder.class);

	interface PlayerWidgetUiBinder extends UiBinder<Widget, PlayerWidget> {
	}

	public PlayerWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;
	@UiField
	SimplePanel playerPanel;
	

	public PlayerWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText(firstName);
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
	
	private AbstractMediaPlayer player = null;
	
	class MyPlayerEventsHandler implements LoadingProgressHandler, DebugHandler, KeyDownHandler, MouseDownHandler, PlayerStateHandler, PlayStateHandler, MediaInfoHandler {

		@Override
		public void onMediaInfoAvailable(MediaInfoEvent event) {
			GWT.log(event.toDebugString());
			
		}

		@Override
		public void onPlayStateChanged(PlayStateEvent event) {
			GWT.log(event.toDebugString() + event.getPlayState().toString());
			
		}

		@Override
		public void onPlayerStateChanged(PlayerStateEvent event) {
			GWT.log(event.toDebugString() + event.getPlayerState().toString());
			if (event.getPlayerState() == State.Ready){
				try {
					player.playMedia();
				} catch (PlayException e) {
					Window.alert(e.getMessage());
				}
			}
			
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			GWT.log(event.toDebugString());
			
		}

		@Override
		public void onKeyDown(KeyDownEvent event) {
			GWT.log(event.toDebugString());
			
		}

		@Override
		public void onDebug(DebugEvent event) {
			GWT.log(event.toDebugString() + event.getMessage());
			
		}

		@Override
		public void onLoadingProgress(LoadingProgressEvent event) {
			GWT.log(event.toDebugString());
			
		}
		
		
		
	}

	public void initPlayer(String aVideoId) {

		
		MyPlayerEventsHandler myHandler = new MyPlayerEventsHandler();
		
		try {
			//player = new ChromelessPlayer(aVideoId, "100%", "100%"); //"JlYXp_3A64k"
			player = new YouTubePlayer(aVideoId, "100%", "100%"); //"JlYXp_3A64k"
			
			
			
			player.addLoadingProgressHandler(myHandler);
			player.addDebugHandler(myHandler);
			player.addKeyDownHandler(myHandler);
			player.addMouseDownHandler(myHandler);
			player.addPlayerStateHandler(myHandler);
			player.addPlayStateHandler(myHandler);
			player.addMediaInfoHandler(myHandler);
			
			

			playerPanel.setWidget(player);
		} catch (PluginVersionException e) {
			// required Flash plugin version is not available,
			// alert user possibly providing a link to the plugin download page.
			playerPanel.setWidget(new HTML(".. some nice message telling the " + "user to download plugin first .."));
		} catch (PluginNotFoundException e) {
			// required Flash plugin not found, display a friendly notice.
			playerPanel.setWidget(PlayerUtil.getMissingPluginNotice(e.getPlugin()));
		} catch (Exception e) {
			// required Flash plugin version is not available,
			// alert user possibly providing a link to the plugin download page.
			playerPanel.setWidget(new HTML(".. some nice message telling the " + "user to download plugin first . . "
					+ e.getMessage()));
		}

		

	}

}
