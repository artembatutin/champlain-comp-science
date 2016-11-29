package game.battlefield.core;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager implements EventHandler<KeyEvent> {
	
	public InputManager() {
	}
	
	@Override
	public void handle(KeyEvent event) {
		KeyCode code = event.getCode();
		/*if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			first.keyPress(code);
			second.keyPress(code);
		} else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			first.keyRelease(code);
			second.keyRelease(code);
		}*/
	}
}
