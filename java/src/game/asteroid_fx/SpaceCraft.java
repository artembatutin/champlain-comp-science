package game.asteroid_fx;

import game.asteroid_fx.node.NodeState;
import game.asteroid_fx.node.SpaceNode;
import game.asteroid_fx.node.impl.Meteor;
import game.asteroid_fx.node.impl.PowerUp;
import game.asteroid_fx.node.impl.ship.Ship;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
public class SpaceCraft extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static final int WIDTH = 1400;
	
	public static final int HEIGHT = 900;
	
	public final static Group ROOT = new Group();
	
	private final static Set<SpaceNode> NODES = new HashSet<>();
	
	private final static Set<SpaceNode> added = new HashSet<>();
	
	private final static Set<SpaceNode> removed = new HashSet<>();
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(SpaceCraft.ROOT, WIDTH, HEIGHT, Color.BLACK);
		scene.setFill(new ImagePattern(new Image("file:data/space/bg.png")));
		stage.setTitle("SpaceCraft by Artem Batutin");
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
		
		//Drawing loop of all space nodes, each 20 milliseconds(for 50 frames per seconds).
		Timeline draw = new Timeline(new KeyFrame(Duration.millis(20), event -> NODES.forEach(SpaceNode::draw)));
		draw.setCycleCount(Animation.INDEFINITE);
		draw.play();
		
		//Drawing loop of all space nodes, each 150 milliseconds.
		Timeline pulse = new Timeline(new KeyFrame(Duration.millis(100), event -> {
			handleNodes();
			NODES.forEach(n -> {
				n.pulse();
				if(n.isColliding()) {
					for(SpaceNode other : NODES) {
						if(n != other && n.colliding(other)) {
							n.collide(other);
						}
					}
				}
			});
		}));
		pulse.setCycleCount(Animation.INDEFINITE);
		pulse.play();
		
		//Spawning randomely power ups.
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
			SpaceCraft.ROOT.getChildren().add(node);
			return true;
		}
		return false;
	}
	
	/**
	 * Removing a {@link SpaceNode} from the space.
	 * @param node the node to be removed.
	 */
	public static void remove(SpaceNode node) {
		if(removed.add(node)) {
			SpaceCraft.ROOT.getChildren().remove(node);
		}
	}
	
	private void handleNodes() {
		added.forEach(a -> a.setState(NodeState.ALIVE));
		NODES.addAll(added);
		added.clear();
		removed.forEach(a -> a.setState(NodeState.DEAD));
		NODES.removeAll(removed);
		removed.clear();
	}
	
}
