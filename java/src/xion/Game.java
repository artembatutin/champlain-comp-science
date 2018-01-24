package xion;

import xion.core.GameLoop;
import xion.core.GameManager;
import xion.core.InputManager;
import xion.node.NodeState;
import xion.node.SpaceNode;
import xion.node.impl.Meteor;
import xion.node.impl.PowerUp;
import xion.node.impl.ship.Ship;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * The main game of asteroids.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class Game extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static final int WIDTH = 1200;
	
	public static final int HEIGHT = 800;
	
	public final static Group ROOT = new Group();
	
	private final static Set<SpaceNode> NODES = new HashSet<>();
	
	private final static Set<SpaceNode> added = new HashSet<>();
	
	private final static Set<SpaceNode> removed = new HashSet<>();
	
	@Override
	public void start(Stage stage) {
		//Preparing the game.
		Scene scene = new Scene(Game.ROOT, WIDTH, HEIGHT, Color.BLACK);
		scene.setFill(new ImagePattern(new Image("file:data/space/bg.png")));
		stage.setTitle("BattleField by Artem Batutin");
		stage.setResizable(false);
		stage.setScene(scene);
		
		Ship ship = new Ship(true);
		Ship ship2 = new Ship(false);
		InputManager input = new InputManager(ship, ship2);
		scene.setOnKeyPressed(input);
		scene.setOnKeyReleased(input);
		add(ship);
		add(ship2);
		
		Random gen = new Random();
		for(int i = 0; i < 40; i++)
			add(new Meteor(gen.nextInt(WIDTH), gen.nextInt(HEIGHT)));
		
		//Starting the game engine.
		GameManager manager = new GameManager(this);
		GameLoop loop = new GameLoop(manager);
		loop.start();
		
		//Spawning randomly power ups.
		Timeline spawn = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
			add(new PowerUp(PowerUp.PowerType.random(), gen.nextInt(WIDTH - 40) + 20, gen.nextInt(HEIGHT - 40) + 20));
		}));
		spawn.setCycleCount(Animation.INDEFINITE);
		spawn.play();
		
		//Displaying the stage.
		stage.show();
	}
	
	/**
	 * Adding a {@link SpaceNode} to the space.
	 * @param node the node to be added.
	 * @return {@code true} if it was successfully added, {@code false} otherwise.
	 */
	public static boolean add(SpaceNode node) {
		if(added.add(node)) {
			boolean flag = Game.ROOT.getChildren().add(node);
			if(!flag)
				added.remove(node);
			return flag;
		}
		return false;
	}
	
	/**
	 * Removing a {@link SpaceNode} from the space.
	 * @param node the node to be removed.
	 */
	public static boolean remove(SpaceNode node) {
		if(removed.add(node)) {
			boolean flag = Game.ROOT.getChildren().remove(node);
			if(!flag)
				added.remove(node);
			return flag;
		}
		return false;
	}
	
	/**
	 * Handling the adding and removal of our nodes.
	 */
	public void handleNodes() {
		Platform.runLater(() -> {
			//adding nodes to the game.
			if(added.size() > 0) {
				added.forEach(a -> a.setState(NodeState.ALIVE));
				NODES.addAll(added);
				added.clear();
			}
			//removing nodes from the game.
			if(removed.size() > 0) {
				removed.forEach(a -> a.setState(NodeState.DEAD));
				NODES.removeAll(removed);
				removed.clear();
			}
		});
	}
	
	/**
	 * Gets the active nodes on the scene.
	 * @return the active {@link SpaceNode}s.
	 */
	public Set<SpaceNode> getNodes() {
		return NODES;
	}
	
}
