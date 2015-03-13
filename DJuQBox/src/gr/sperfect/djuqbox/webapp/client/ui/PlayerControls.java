/**
 * 
 */
package gr.sperfect.djuqbox.webapp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author sperfect
 *
 */
public class PlayerControls extends Composite implements HasHTML {

	private static PlayerControlsUiBinder uiBinder = GWT.create(PlayerControlsUiBinder.class);

	interface PlayerControlsUiBinder extends UiBinder<Widget, PlayerControls> {
	}

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public PlayerControls(ControlHandler aControlHandler) {
		initWidget(uiBinder.createAndBindUi(this));
		
		controlHandler = aControlHandler;
	}
	
	public PlayerControls() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	@UiField
	Button buttonPlay;
	@UiField
	Button buttonPause;
	@UiField
	Button buttonStop;
	
	
	@UiField
	TextBox volField;
	@UiField
	Button buttonSetVol;


	
	/*
	 * mhpws xreiazetai addListener
	 */
	private ControlHandler controlHandler;

//	public PlayerControls(String text) {
//		initWidget(uiBinder.createAndBindUi(this));
//
//		// Can access @UiField after calling createAndBindUi
//		buttonPlay.setText(firstName);
//	}

	@UiHandler("buttonPlay")
	void onClickPlay(ClickEvent e) {
		Window.alert("onClickPlay!");
		controlHandler.onPlay();
	}
	
	@UiHandler("buttonPause")
	void onClickPause(ClickEvent e) {
		Window.alert("onClickPause!");
		controlHandler.onPause();
	}
	
	@UiHandler("buttonStop")
	void onClickStop(ClickEvent e) {
		Window.alert("onClickStop");
		controlHandler.onStop();
	}

	public void Play()
	{
		controlHandler.onPlay();
		
	}
	public void Pause()
	{
		controlHandler.onPause();
		
	}
	
	public void Stop()
	{
		controlHandler.onStop();
		
	}
	
	/**
	 * Gets invoked when the default constructor is called
	 * and a string is provided in the ui.xml file.
	 */
	public String getText() {
		return buttonPlay.getText(); //???
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		buttonPlay.setText(text);
	}

	@Override
	public String getHTML() {
		return "testHTML";
	}

	@Override
	public void setHTML(String html) {
		buttonPause.setText(html); //??
		
	}

}
