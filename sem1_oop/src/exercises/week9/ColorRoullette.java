package exercises.week9;/**
 * Created by artem on 10/21/16.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class ColorRoullette extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		
		Random gen = new Random();
		Group root = new Group();
		
		Rectangle rect = new Rectangle(300, 275);
		Label rgb = new Label("RGB:");
		rgb.setLayoutX(50);
		rgb.setLayoutY(150);
		rgb.setFont(new Font("Arial", 18));
		
		Button but = new Button("Turn the roulette");
		but.setOnMouseClicked(event -> {
			int r = gen.nextInt(100);
			int g = gen.nextInt(100);
			int b = gen.nextInt(100);
			rect.setFill(Color.color(r / 100.0, g / 100.0, b / 100.0));
			rgb.setText("Red: " + r + " Green: " + g + " Blue: " + b);
		});
		but.setLayoutY(275);
		but.setPrefWidth(300);
		
		root.getChildren().add(rect);
		root.getChildren().add(but);
		root.getChildren().add(rgb);
		Scene scene = new Scene(root, 300, 300, Color.BLACK);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
}
