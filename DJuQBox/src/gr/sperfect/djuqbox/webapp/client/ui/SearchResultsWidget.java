package gr.sperfect.djuqbox.webapp.client.ui;

import gr.sperfect.djuqbox.webapp.shared.data.YoutubeSong;

import java.util.Iterator;
import java.util.List;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultsWidget extends Composite {

	private static SearchResultsWidgetUiBinder uiBinder = GWT
			.create(SearchResultsWidgetUiBinder.class);

	interface SearchResultsWidgetUiBinder extends
			UiBinder<Widget, SearchResultsWidget> {
	}

	@UiField
	VerticalPanel resultList;

	public SearchResultsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void clearResults() {
		resultList.clear();

	}

	public void add(List<YoutubeSong> response) {

		for (YoutubeSong youtubeSong : response) {
			
			
			String _thumbUrl ="";
			if (youtubeSong.getThumbs() != null
					&& youtubeSong.getThumbs().size() > 0) {
				_thumbUrl = youtubeSong.getThumbs().get(0).getUrl();
			}
			
			
			ResultEntry re = new ResultEntry(youtubeSong.getTitle(), youtubeSong.getCode(), _thumbUrl);
			resultList.add(re );
			

		}

	}

	// @UiField
	// Button button;
	//
	// public SearchResultsWidget(String firstName) {
	// initWidget(uiBinder.createAndBindUi(this));
	// button.setText(firstName);
	// }
	//
	// @UiHandler("button")
	// void onClick(ClickEvent e) {
	// Window.alert("Hello!");
	// }
	//
	// public void setText(String text) {
	// button.setText(text);
	// }
	//
	// public String getText() {
	// return button.getText();
	// }

}
