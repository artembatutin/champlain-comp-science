package game.asteroid_fx;

import game.asteroid_fx.node.SpaceNode;
import game.asteroid_fx.node.impl.Rock;
import game.asteroid_fx.node.impl.Ship;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
	
	public static final int WIDTH = 1000;
	
	public static final int HEIGHT = 600;
	
	private final Group root = new Group();
	
	private final Set<SpaceNode> nodes = new HashSet<>();
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);
		
		stage.setTitle("SpaceCraft by Artem Batutin");
		stage.setResizable(false);
		stage.setScene(scene);
		
		Ship ship = new Ship(true);
		Ship ship2 = new Ship( false);
		InputManager input = new InputManager(ship, ship2);
		scene.setOnKeyPressed(input);
		scene.setOnKeyReleased(input);
		add(ship);
		add(ship2);
		
		//Random gen = new Random();
		//for(int i = 0; i < 20; i++)
			//add(new Rock(gen.nextInt(WIDTH), gen.nextInt(HEIGHT)));
		
		//Drawing loop of all space nodes, each 20 milliseconds(for 50 frames per seconds).
		Timeline draw = new Timeline(new KeyFrame(Duration.millis(20), event -> nodes.forEach(SpaceNode::draw)));
		draw.setCycleCount(Animation.INDEFINITE);
		draw.play();
		
		//Drawing loop of all space nodes, each 150 milliseconds.
		Timeline pulse = new Timeline(new KeyFrame(Duration.millis(150), event -> nodes.forEach(n -> {
			n.pulse();
			if(n.isColliding()) {
				System.out.println(nodes.size());
				for(SpaceNode other : nodes) {
					if(n.getLayoutBounds().intersects(other.getLayoutBounds())) {
						n.collide(other);
					}
				}
			}
		})));
		pulse.setCycleCount(Animation.INDEFINITE);
		pulse.play();
		
		//Displaying the stage.
		stage.show();
	}
	
	/**
	 * Adding a {@link SpaceNode} to the space.
	 * @param node the node to be added.
	 * @return {@code true} if it was successfully added, {@code false} otherwise.
	 */
	public boolean add(SpaceNode node) {
		if(nodes.add(node)) {
			root.getChildren().add(node);
			return true;
		}
		return false;
	}
	
	/**
	 * Removing a {@link SpaceNode} from the space.
	 * @param node the node to be removed.
	 */
	public void remove(SpaceNode node) {
		nodes.remove(node);
		root.getChildren().remove(node);
	}
	
}
