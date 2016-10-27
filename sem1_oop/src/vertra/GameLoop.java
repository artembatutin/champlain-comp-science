package vertra;

import javafx.animation.AnimationTimer;

/**
 * A {@link AnimationTimer} which handles as our game loop.
 */
public class GameLoop extends AnimationTimer {
	
	/**
	 * The the {@link Game} instance for reference.
	 */
	private final Game game;
	
	/**
	 * Creates our main game loop.
	 * @param game the game reference.
	 */
	public GameLoop(Game game) {
		this.game = game;
	}
	
	@Override
	public void handle(long now) {
		
	}
	
	
	
}
