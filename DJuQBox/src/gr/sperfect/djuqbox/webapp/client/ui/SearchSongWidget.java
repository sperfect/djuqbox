package gr.sperfect.djuqbox.webapp.client.ui;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import gr.sperfect.djuqbox.webapp.client.DJuQBox;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SearchSongWidget extends Composite implements HasText {

	private static SearchSongWidgetUiBinder uiBinder = GWT.create(SearchSongWidgetUiBinder.class);

	interface SearchSongWidgetUiBinder extends UiBinder<Widget, SearchSongWidget> {
	}

	public SearchSongWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button buttonSearch;
	
	@UiField
	TextBox textSearch;
	@UiField
	SearchResultsWidget  searchResults;

	public SearchSongWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		buttonSearch.setText(firstName);
	}

	@UiHandler("buttonSearch")
	void onClick(ClickEvent e) {
		
		if (textSearch.getText().trim() == "")
		{
			Window.alert("empty search!");
			return;
		}
		
		DJuQBox.API.searchYoutubeVideo(textSearch.getText(), new MethodCallback<List<YoutubeSong>>() {
			
			@Override
			public void onSuccess(Method method, List<YoutubeSong> response) {
				
				GWT.log("searct got " + response.size() );
				searchResults.clearResults();
				
				searchResults.add(response);
			}
			
			@Override
			public void onFailure(Method method, Throwable exception) {
				
				GWT.log(exception.getMessage(), exception);
				//8elei mia global sthn DJuqbox
			}
		});
	}

	public void setText(String text) {
		buttonSearch.setText(text);
	}

	public String getText() {
		return buttonSearch.getText();
	}

}
