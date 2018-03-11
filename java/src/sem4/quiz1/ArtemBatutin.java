package sem4.quiz1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Slot machines are thirsty.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public class ArtemBatutin extends Application {

	/** Samia's defined price. */
	final static double DEFINED_PRICE = 0.75;
	/** Samia's defined intial drink stock.*/
	final static int INITIAL_DEFINED_STOCK = 20;
	/** Width and height of the images in slot machine. */
	final static double IMAGE_SIZE = 100D;
	/** Selected drink label suffix. */
	final static String SELECT_SUFFIX = "Selected drink : ";
	/** Text color. */
	final static Color TEXT_COLOR = Color.web("0xe17938");

	/** How much slots can be in a width. */
	final static int ROW_WIDTH = 2;

	/** An array of pre-declared drinks. */
	public final static Drink[] DRINKS = {
			new Drink("Cola", "cola"),
			new Drink("Grape Soda", "grape"),
			new Drink("Root Beer", "rootbeer"),
			new Drink("Bottled Water", "water")
	};


	/** Singleton instance to this {@link Application}. */
	private static ArtemBatutin singleton;
	/** {@link Drink} selected by user. */
	private Drink selected = null;
	/** The user field / their money. */
	private TextField usrField;
	/** Selected {@link Drink} {@link Label}. */
	private Label drinkLabel;
	/** The processing status. */
	private Label status;
	/** The entered user amount. */
	public double userAmount;
	/** If we can submit our payment. */
	public boolean canSubmit;

	@Override
	public void start(Stage stage) {
		singleton = this;//initialize our instance.
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10D, 10D, 20D, 20D));
		//setting up background.
		grid.setBackground(new Background(new BackgroundFill(Color.web("0x2b3d39"), CornerRadii.EMPTY, Insets.EMPTY)));
		//setting up drinks grid.
		int height = 0, width = 0;
		for(Drink d : DRINKS) {
			width++;
			grid.add(d.getBox(), width, height);
			if(width == ROW_WIDTH) {
				width = 0;
				height++;
			}
		}
		//setting up 'payment-user"
		VBox usrBox = new VBox(10D);
		//payment field.
		Label payLabel = new Label("Enter amount to pay.");
		payLabel.setTextFill(TEXT_COLOR);
		usrField = new TextField();
		// force the field to be numeric only
		usrField.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				userAmount = Double.parseDouble(newValue);
				if(singleton.getDrink() != null) {
					double price = singleton.getDrink().getPrice();
					if(userAmount >= price) {
						status.setText("You can buy it!");
					} else {
						status.setText("Not enough $.");
					}
				} else {
					status.setText("Select a drink!");
				}
				canSubmit = true;
			} catch(Exception e) {
				//cannot cast.
				canSubmit = false;
				status.setText("Entered value not valid.");
			}
		});
		//purchase field
		Label select = new Label(SELECT_SUFFIX);
		select.setTextFill(TEXT_COLOR);
		drinkLabel = new Label("None");
		drinkLabel.setTextFill(TEXT_COLOR);
		usrBox.getChildren().addAll(payLabel, usrField, select, drinkLabel);
		Button submit = new Button("Pay");
		//payment button handling.
		submit.setOnMouseClicked(e -> {
			if(singleton.getDrink() != null) {
				double price = singleton.getDrink().getPrice();
				if(userAmount >= price) {
					int stock = singleton.getDrink().decraseAndStock();
					if(stock < 0) {
						status.setText("No more stock left.");
						return;
					}
					userAmount -= price;
					usrField.setText(userAmount + "");
					status.setText(stock + " " + singleton.getDrink().getText() + " left.");
				} else {
					status.setText("Not enough money loser.");
				}
			} else {
				status.setText("Select a drink!");
			}
		});
		status = new Label("Awaiting payment.");
		status.setTextFill(TEXT_COLOR);
		usrBox.getChildren().addAll(submit, status);
		//setting user control, centered and starting application.
		grid.add(usrBox, ROW_WIDTH + 1, (int) (height / 2D));
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.show();
	}

	public Drink getDrink() {
		return selected;
	}

	/**
	 * Setting our selected drink.
	 * @param drink drink to select.
	 */
	public void setDrink(Drink drink) {
		this.selected = drink;
		this.drinkLabel.setText(drink.getText() + " - $" + drink.getPrice());
	}

	/**
	 * Represents an single slot of a {@code Drink}.
	 * @author Artem Batutin <artembatutin@gmail.com>
	 */
	static class Drink {
		private final String text;
		private final double price;
		private final String img;

		private int stock = INITIAL_DEFINED_STOCK;

		Drink(String text, String img) {
			this.text = text;
			this.price = DEFINED_PRICE;
			this.img = img;
		}

		Drink(String text, double price, String img) {
			this.text = text;
			this.price = price;
			this.img = img;
		}

		public int decraseAndStock() {
			return --stock;
		}

		public VBox getBox() {
			VBox box = new VBox();
			box.setAlignment(Pos.CENTER);
			//setting image.
			ImageView view = new ImageView(getImage());
			view.setFitWidth(IMAGE_SIZE);
			view.setFitHeight(IMAGE_SIZE);
			box.getChildren().add(view);
			//Button to select
			Button button = new Button("Select drink");
			button.setOnMouseClicked(e -> singleton.setDrink(this));
			box.getChildren().add(button);
			button.setStyle(buttonStyle);
			//setting font.
			Text text = new Text(getText());
			text.setFont(new Font("Arial", 18));
			box.getChildren().add(text);
			//setting price.
			Text price = new Text("$" + getPrice());
			price.setFont(new Font("Arial", 16));
			box.getChildren().add(price);
			//returning created box.
			return box;
		}

		public String getText() {
			return text;
		}

		public double getPrice() {
			return price;
		}

		public Image getImage() {
			return new Image(getClass().getResource(img + ".png").toString());
		}
	}

	private static final String buttonStyle = "-fx-background-radius: 8;\n" + "    -fx-background-color: \n" + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" + "        #9d4024,\n" + "        #d86e3a,\n" + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" + "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );";
}
