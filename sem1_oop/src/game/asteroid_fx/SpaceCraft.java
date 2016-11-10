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
	
	private final Group root = new Group();
	
	private final Set<SpaceNode> nodes = new HashSet<>();
	
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(root, 1000, 600, Color.BLACK);
		
		stage.setTitle("SpaceCraft by Artem Batutin");
		stage.setResizable(false);
		stage.setScene(scene);
		
		Ship ship = new Ship();
		add(ship);
		scene.setOnKeyPressed(new InputManager(ship));
		
		Random gen = new Random();
		for(int i = 0; i < 20; i++)
			add(new Rock(gen.nextInt(1000), gen.nextInt(600)));
		
		//Displaying the stage.
		stage.show();
		
		//Drawing loop of all space nodes, each 20 milliseconds(for 50 frames per seconds).
		Timeline draw = new Timeline(new KeyFrame(Duration.millis(20), event -> nodes.forEach(SpaceNode::draw)));
		draw.setCycleCount(Animation.INDEFINITE);
		draw.play();
		
		//Drawing loop of all space nodes, each 150 milliseconds.
		Timeline pulse = new Timeline(new KeyFrame(Duration.millis(150), event -> nodes.forEach(SpaceNode::pulse)));
		draw.setCycleCount(Animation.INDEFINITE);
		draw.play();
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
