package game.battlefield.core;

import game.battlefield.BattleField;
import game.battlefield.node.Node;

/**
 * The main manager of the game which handles all logical and graphical updates.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class GameManager {
	
	/**
	 * The game instance.
	 */
	private final BattleField game;
	
	/**
	 * Creates a new {@link GameManager}.
	 * @param game
	 */
	public GameManager(BattleField game) {
		this.game = game;
	}
	
	public void logic(float delta) {
		game.handleNodes();
		game.getNodes().forEach(n -> {
			n.pulse(delta);
			if(n.isColliding()) {
				for(Node other : game.getNodes()) {
					if(n != other && n.colliding(other)) {
						n.collide(other);
					}
				}
			}
		});
	}
	
	public void graphic(float delta) {
		game.getNodes().forEach(n -> n.draw(delta));
	}
	
}
