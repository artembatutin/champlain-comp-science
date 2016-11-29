package game.battlefield;

import game.battlefield.core.GameManager;
import game.battlefield.core.InputManager;
import game.battlefield.node.NodeState;
import game.battlefield.node.Node;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * The main game.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class BattleField extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private static final int WIDTH = 1200;
	
	private static final int HEIGHT = 800;
	
	private final static Group ROOT = new Group();
	
	private final static Set<Node> NODES = new HashSet<>();
	
	private final static Set<Node> added = new HashSet<>();
	
	private final static Set<Node> removed = new HashSet<>();
	
	@Override
	public void start(Stage stage) {
		//scene and stage
		Scene scene = new Scene(BattleField.ROOT, WIDTH, HEIGHT, Color.BLACK);
		scene.setFill(new ImagePattern(new Image("file:data/space/bg.png")));
		stage.setTitle("BattleField by Artem Batutin");
		stage.setResizable(false);
		stage.setScene(scene);
		
		//input and controls
		InputManager input = new InputManager();
		scene.setOnKeyPressed(input);
		scene.setOnKeyReleased(input);
		
		//game loop and manager
		GameManager manager = new GameManager(this);
		AnimationTimer loop = new GameLoop(manager);
		loop.start();

		//Displaying the stage.
		stage.show();
	}
	
	/**
	 * Adding a {@link Node} to the space.
	 * @param node the node to be added.
	 * @return {@code true} if it was successfully added, {@code false} otherwise.
	 */
	public static boolean add(Node node) {
		if(added.add(node)) {
			BattleField.ROOT.getChildren().add(node);
			return true;
		}
		return false;
	}
	
	/**
	 * Removing a {@link Node} from the space.
	 * @param node the node to be removed.
	 */
	public static void remove(Node node) {
		if(removed.add(node)) {
			BattleField.ROOT.getChildren().remove(node);
		}
	}
	
	public void handleNodes() {
		added.forEach(a -> a.setState(NodeState.ALIVE));
		NODES.addAll(added);
		added.clear();
		removed.forEach(a -> a.setState(NodeState.DEAD));
		NODES.removeAll(removed);
		removed.clear();
	}
	
	public Set<Node> getNodes() {
		return NODES;
	}
	
}
