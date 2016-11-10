package game.asteroid_fx;

import game.asteroid_fx.node.impl.Ship;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager implements EventHandler<KeyEvent> {
	
	private final Ship ship;
	
	public InputManager(Ship ship) {
		this.ship = ship;
	}
	
	@Override
	public void handle(KeyEvent event) {
		KeyCode press = event.getCode();
		System.out.println(press);
		if(press == KeyCode.A) {
			ship.rotateLeft(4);
		} else if(press == KeyCode.D) {
			ship.rotateRight(4);
		}
		if(press == KeyCode.W) {
			ship.increaseSpeed(2);
		} else if(press == KeyCode.S) {
			ship.decreaseSpeed(2);
		}
	}
}
