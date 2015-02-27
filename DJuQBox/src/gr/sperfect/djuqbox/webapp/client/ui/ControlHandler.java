package gr.sperfect.djuqbox.webapp.client.ui;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventHandler;

/**
 * 8a xeirizetai to ti kanoun ta controls
 * analoga ta dikaiwmata 8a dinw allo handler pou 8a tis ylopoiei diaforetika
 * px o station 8a pataei pause kai 8a stamataei o player
 * o client 8a stelne ston station na stamathsei o player 
 * */
public interface ControlHandler extends EventHandler{

	void onStop();

	void onPause();

	void onPlay();

}
