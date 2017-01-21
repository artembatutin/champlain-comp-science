package game.asteroid_fx.core;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

/**
 * Game loop implementation using fixed time steps with adapted step skipper.
 */
public class GameLoop extends AnimationTimer {
	
	/**
	 * The step time aiming for to obtain 60FPS.
	 */
	private static final float STEP = 0.0166f;
	
	/**
	 * The main {@link GameManager} handling the game steps.
	 */
	private final GameManager manager;
	
	/**
	 * The time of the previous update.
	 */
	private long previousTime = 0;
	
	/**
	 * The accumulated time between two updates.
	 */
	private float accumulatedTime = 0;
	
	/**
	 * The accumulated time between for fps counter.
	 */
	private float accumulatedTimeFPS = 0f;
	
	/**
	 * How many frames were parsed for fps counter.
	 */
	private int frames = 0;
	
	/**
	 * Creates the {@link GameLoop}.
	 * @param manager the game manager.
	 */
	public GameLoop(GameManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void handle(long currentTime) {
		if(previousTime == 0) {
			previousTime = currentTime;
			return;
		}
		
		//timing between updates.
		float secondsElapsed = (currentTime - previousTime) / 1e9f;
		float secondsElapsedCapped = Math.min(secondsElapsed, STEP);//max step.
		accumulatedTime += secondsElapsedCapped;
		previousTime = currentTime;
		
		//eating steps depending on time.
		while(accumulatedTime >= STEP) {
			manager.pulse();//pulsing a step.
			accumulatedTime -= STEP;
		}
		
		//rendering the scene.
		manager.render();
		
		//frames per second counter.
		accumulatedTimeFPS += secondsElapsed;
		frames++;
		if(accumulatedTimeFPS >= 0.5f) {
			int fps = Math.round(frames / accumulatedTimeFPS);
			accumulatedTimeFPS = 0;
			frames = 0;
			System.out.println("Fps: " + fps);
		}
	}
	
}