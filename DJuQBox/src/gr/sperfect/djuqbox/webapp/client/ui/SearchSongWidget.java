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
		Window.alert("Hello!");
	}

	public void setText(String text) {
		buttonSearch.setText(text);
	}

	public String getText() {
		return buttonSearch.getText();
	}

}
