package game.vertra;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is a simple game written by Artem Batutin.
 * By term of condition you may only use this code in educative purposes.
 */
public class Game extends Application {
	
	/**
	 * The {@link Scene} of this application.
	 */
	private final Scene scene;
	
	/**
	 * The {@link Group} of this application.
	 */
	private final Group parent;
	
	/**
	 * Creates our main game application instance.
	 */
	public Game() {
		this.parent = new Group();
		this.scene = new Scene(parent);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
 	
	@Override
	public void start(Stage stage) {
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Vertra - Game");
		stage.show();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Group getParent() {
		return parent;
	}
	
}
