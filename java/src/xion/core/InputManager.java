package xion.core;

import xion.node.impl.ship.Ship;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager implements EventHandler<KeyEvent> {
	
	private final Ship first;
	
	private final Ship second;
	
	public InputManager(Ship first, Ship second) {
		this.first = first;
		this.second = second;
	}
	
	@Override
	public void handle(KeyEvent event) {
		KeyCode code = event.getCode();
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			first.keyPress(code);
			second.keyPress(code);
		} else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			first.keyRelease(code);
			second.keyRelease(code);
		}
	}
}
