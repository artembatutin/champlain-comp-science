package game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Newton extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private final int RADIUS = 50;
	
	private final Random gen = new Random();
	
	private int width;
	
	private int height;
	
	private int catches;
	
	private int missed;
	
	private Circle apple;
	
	private Rectangle newton;
	
	private Label info = new Label("0 / 0");
	
	@Override
	public void start(Stage primaryStage) {
		width = 800;
		height = 500;
		
		newton = new Rectangle(width / 2 - 40, height - 10, 80, 10);
		newton.setFill(Color.BLUEVIOLET);
		
		info.setTranslateX(30);
		info.setTranslateY(40);
		info.setFont(new Font("Times New Roman", 24));
		
		Group root = new Group();
		root.getChildren().add(newton);
		root.getChildren().add(info);
		
		Scene scene = new Scene(root, width, height, Color.DARKSLATEGRAY);
		scene.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.LEFT) {
				newton.setX(newton.getX() - 8);
			} else if(event.getCode() == KeyCode.RIGHT) {
				newton.setX(newton.getX() + 8);
			}
		});
		
		primaryStage.setTitle("Newton's Apple!");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Timeline time = new Timeline(new KeyFrame(new Duration(6), event -> {
			//if no apple, create
			if(apple == null) {
				apple = new Circle(gen.nextInt(width - RADIUS * 2), 0, RADIUS, Color.OLIVE);
				root.getChildren().add(apple);
			} else {
				apple.setCenterY(apple.getCenterY() + 1);
			}
			
			//check bounds
			if(apple != null) {
				if (newton.intersects(apple.getLayoutBounds())) {
					catches += 1;
					info.setText(catches + " / " + missed);
					root.getChildren().remove(apple);
					apple = null;
				} else if(apple.getCenterY() > height) {
					missed += 1;
					root.getChildren().remove(apple);
					apple = null;
					info.setText(catches + " / " + missed);
				}
			}
		}));
		time.setCycleCount(Animation.INDEFINITE);
		time.play();
	}
}
