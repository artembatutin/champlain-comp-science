package game.sillynaming;/**
 * Created by Artem Batutin on 11/25/2016.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SillyName extends Application {
	
	private static final String[] FIRST = {"Runny", "Buttercup", "Dinky", "Stinky", "Crusty", "Greasy", "Gidget", "Cheesypoof", "Lumpy", "Wacky", "Tiny", "Flunky", "Fluffy", "Zippy", "Doofus", "Gobsmacked", "Slimy", "Grimy", "Salamander", "Oily", "Burrito", "Bumpy", "Loopy", "Snotty", "Irving", "Egbert"};
	private static final String[] MIDDLE = {"Snicker", "Buffalo", "Gross", "Bubble", "Sheep", "Corset", "Toilet", "Lizard", "Waffle", "Kumquat", "Burger", "Chimp", "Liver", "Gorilla", "Rhino", "Emu", "Pizza", "Toad", "Gerbil", "Pickle", "Tofu", "Chicken", "Potato", "Hamster", "Lemur", "Vermin"};
	private static final String[] LAST = {"Face", "Dip", "Nose", "Brain", "Head", "Breath", "Pants", "Shorts", "Lips", "Mouth", "Muffin", "Butt", "Bottom", "Elbow", "Honker", "Toes", "Buns", "Spew", "Kisser", "Fanny", "Squirt", "Chunks", "Brains", "Wit", "Juice", "Shower"};
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		Group root = new Group();
		
		Scene scene = new Scene(root, 400, 200);
		stage.setScene(scene);
		stage.setTitle("Silly Name");
		
		TextField first = new TextField("First Name");
		first.setPrefWidth(350);
		first.setLayoutX(20);
		first.setLayoutY(40);
		root.getChildren().add(first);
		
		TextField last = new TextField("Last Name");
		last.setPrefWidth(350);
		last.setLayoutX(20);
		last.setLayoutY(80);
		root.getChildren().add(last);
		
		Label result = new Label("");
		result.setLayoutY(150);
		result.setLayoutX(120);
		result.setTextAlignment(TextAlignment.CENTER);
		root.getChildren().add(result);
		
		Button submit = new Button("Submit");
		submit.setLayoutX(150);
		submit.setLayoutY(120);
		submit.setOnMouseClicked(event -> result.setText(submit(first, last)));
		root.getChildren().add(submit);
		
		stage.show();
	}
	
	private static String submit(TextField first, TextField last) {
		String sillyFirst = "", sillyMiddle = "", sillyLast = "";
		System.out.println("first: " + first.getText());
		char f = first.getText().toLowerCase().charAt(0);
		char l = last.getText().toLowerCase().charAt(0);
		char ll = last.getText().toLowerCase().charAt(last.getText().length() - 1);
		
		int num = (int) f;
		if(num <= 122 & num >= 97)
			sillyFirst = FIRST[num - 97];
		
		num = (int) l;
		if(num <= 122 & num >= 97)
			sillyMiddle = MIDDLE[num - 97];
		
		num = (int) ll;
		if(num <= 122 & num >= 97)
			sillyLast = LAST[num - 97];
		
		return sillyFirst + " " + sillyMiddle + " " + sillyLast;
	}
}
