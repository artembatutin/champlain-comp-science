package game.battlefield;

import game.battlefield.core.GameManager;
import javafx.animation.AnimationTimer;

/**
 * The main game loop that loops and returns a delta time between two iterations.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class GameLoop extends AnimationTimer {
	
	/**
	 * The delta difference of two updates.
	 */
	private float delta;
	
	/**
	 * The start time in nano seconds.
	 */
	private long start = System.nanoTime();
	
	/**
	 * The game manager where we push our updates.
	 */
	private final GameManager manager;
	
	/**
	 * Creating our {@link GameLoop}..
	 * @param manager {@link #manager}.
	 */
	public GameLoop(GameManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void handle(long now) {
		delta = (float) ((now - start) / 1E9);
		System.out.println(delta);
		manager.logic(delta);
		manager.graphic(delta);
		
		start = now;
	}
	
}
