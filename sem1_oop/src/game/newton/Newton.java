package game.newton;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class Newton extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private final int RADIUS = 25;
	private final Random gen = new Random();
	
	private int width;
	private int height;
	
	private int catches;
	private int dropped;
	
	private int speed = 5;
	
	private Circle apple;
	private Circle grape;
	private Rectangle newton;
	
	private boolean play = true;
	
	private Label info = new Label("Catch the orange ball! Don't touch the blue ball.");
	
	@Override
	public void start(Stage primaryStage) {
		width = 800;
		height = 500;
		
		newton = new Rectangle(width / 2 - 40, height - 10, 80, 10);
		newton.setFill(Color.BLUEVIOLET);
		
		info.setTranslateX(20);
		info.setTranslateY(30);
		info.setFont(new Font("Times New Roman", 32));
		
		Group root = new Group();
		root.getChildren().add(newton);
		root.getChildren().add(info);
		
		Scene scene = new Scene(root, width, height, Color.DARKSLATEGRAY);
		scene.setOnKeyPressed(event -> {
			if(event.getCode() == LEFT || event.getCode() == RIGHT) {
				if(newton.getX() - speed < 0 && event.getCode() == LEFT) {
					newton.setX(0);
					return;
				}
				if(newton.getX() + speed + newton.getWidth() > width && event.getCode() == RIGHT) {
					newton.setX(width - newton.getWidth());
					return;
				}
				newton.setX(newton.getX() + (event.getCode() == LEFT ? -speed : +speed));
				speed += 2;
			}
		});
		scene.setOnKeyReleased(event -> speed = 5);
		
		primaryStage.setTitle("Newton's Apple!");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Timeline time = new Timeline(new KeyFrame(new Duration(6), event -> {
			if(!play) {
				info.setText("You lost");
				info.setTextFill(Color.RED);
				return;
			}
			//if no apple, create
			if(apple == null && grape == null) {
				dropped += 1;
				int x = gen.nextInt(width - RADIUS * 2);
				apple = new Circle(x, 0, RADIUS, Color.ORANGERED);
				root.getChildren().add(apple);
				
				boolean right = gen.nextBoolean();
				int move = right ? gen.nextInt(width - x) : gen.nextInt(Math.abs(x - width));
				grape = new Circle(right ? x + move + 50 : x - move - 50, 0, RADIUS, Color.CADETBLUE);
				root.getChildren().add(grape);
			}
			
			//check bounds
			if(apple != null && grape != null) {
				apple.setCenterY(apple.getCenterY() + 2);
				grape.setCenterY(grape.getCenterY() + 2);
				
				boolean clear = false;
				
				if(newton.intersects(grape.getLayoutBounds())) {
					play = false;
					return;
				} else if(newton.intersects(apple.getLayoutBounds())) {
					catches += 1;
					clear = true;
				} else if(apple.getCenterY() > height) {
					clear = true;
				}
				
				if(clear) {
					info.setText(catches + " / " + dropped);
					root.getChildren().remove(apple);
					root.getChildren().remove(grape);
					apple = null;
					grape = null;
				}
				
			}
		}));
		time.setCycleCount(Animation.INDEFINITE);
		time.play();
	}
}
