package game.money;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

class Sprite {
	
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double height;
	private double width;
	private Image image;
	
	private boolean right;
	private boolean left;
	private boolean down;
	private boolean up;
	
	Sprite() {
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}
	
	void SetImage(String f) {
		image = new Image(f);
		width = image.getWidth();
		height = image.getHeight();
	}
	
	void SetPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}
	
	void SetVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	void setDown(boolean down) {
		this.down = down;
	}
	
	void setRight(boolean right) {
		this.right = right;
	}
	
	void setLeft(boolean left) {
		this.left = left;
	}
	
	private void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}
	
	void update(double time) {
		if(down)
			addVelocity(0, +50);
		else if(up)
			addVelocity(0, -50);
		if(left)
			addVelocity(-50, 0);
		else if(right)
			addVelocity(50, 0);
		positionX += velocityX * time;
		positionY += velocityY * time;
	}
	
	void drawSprite(GraphicsContext gc) {
		gc.drawImage(image, positionX, positionY);
	}
	
	private Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}
	
	boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}
	
	double getX() {
		return positionX;
	}
	
	double getY() {
		return positionY;
	}
}

public class MoneyGame extends Application {
	
	private Random rand = new Random();
	private Sprite briefcase;
	private GraphicsContext gc;
	private final ArrayList<Sprite> imageList = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Scene theScene = new Scene(root);
		primaryStage.setScene(theScene);
		primaryStage.setTitle("Money  Game Version - fixed by Artem Batutin");
		Canvas canvas = new Canvas(512, 512);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillRect(0, 0, 512, 512);
		Sprite moneybag = new Sprite();
		moneybag.SetImage("file:data/game/money/moneybag.png");
		briefcase = new Sprite();
		briefcase.SetImage("file:data/game/money/briefcase.png");
		briefcase.SetPosition(200, 200);
		briefcase.SetVelocity(0, 0);
		briefcase.drawSprite(gc);
		for(int i = 0; i < 10; i++) {
			moneybag = new Sprite();
			moneybag.SetImage("file:data/game/money/moneybag.png");
			moneybag.SetPosition(rand.nextInt(400), rand.nextInt(400));
			imageList.add(moneybag);
		}
		for(Sprite anImageList : imageList) {
			anImageList.drawSprite(gc);
		}
		
		theScene.setOnKeyPressed(e -> {
			briefcase.SetVelocity(0, 0);
			switch(e.getCode()) {
				case DOWN:
					briefcase.setDown(true);
					briefcase.setUp(false);
					break;
				case UP:
					briefcase.setUp(true);
					briefcase.setDown(false);
					break;
				case LEFT:
					briefcase.setLeft(true);
					briefcase.setRight(false);
					break;
				case RIGHT:
					briefcase.setRight(true);
					briefcase.setLeft(false);
					break;
			}
		});
		
		theScene.setOnKeyReleased(event -> {
			switch(event.getCode()) {
				case DOWN:
					briefcase.setDown(false);
					break;
				case UP:
					briefcase.setUp(false);
					break;
				case LEFT:
					briefcase.setLeft(false);
					break;
				case RIGHT:
					briefcase.setRight(false);
					break;
			}
		});
		
		new AnimationTimer() {
			long prevTime = System.nanoTime();
			@Override
			public void handle(long currentTime) {
				double diff = (currentTime - prevTime) / 1000000000.0;
				briefcase.update(diff);
				gc.clearRect(0, 0, 512, 512);
				gc.fillRect(0, 0, 512, 512);
				for(int k = 0; k < imageList.size(); k++) {
					if(imageList.get(k).intersects(briefcase)) {
						imageList.remove(k);
					}
				}
				drawMoneyBags();
				briefcase.drawSprite(gc);
				if(briefcase.getX() < -31 || briefcase.getX() > 481 || briefcase.getY() < -31 || briefcase.getY() > 481) {
					Label text = new Label("You lost");
					text.setFont(new Font("Arial", 32));
					text.setLayoutX(200);
					text.setLayoutY(250);
					root.getChildren().add(text);
					this.stop();
				}
				prevTime = currentTime;
			}
		}.start();
		primaryStage.show();
		
	}
	
	private void drawMoneyBags() {
		for(Sprite anImageList : imageList) {
			anImageList.drawSprite(gc);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
