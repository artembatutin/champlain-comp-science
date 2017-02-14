package xion.core;

import xion.Game;
import xion.node.SpaceNode;

/**
 * This class in charge to pulse and render the whole game as the loop iterates it.
 */
public class GameManager {
	
	/**
	 * The game instance to control it.
	 */
	private final Game game;
	
	/**
	 * Creates the {@link GameManager}.
	 * @param game game instance.
	 */
	public GameManager(Game game) {
		this.game = game;
	}
	
	public void pulse() {
		game.handleNodes();
		game.getNodes().forEach(n -> {
			n.pulse();
			//collision
			if(n.isColliding()) {
				for(SpaceNode other : game.getNodes()) {
					if(n != other && n.colliding(other)) {
						n.collide(other);
					}
				}
			}
		});
	}
	
}