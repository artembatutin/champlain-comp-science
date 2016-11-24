package game.asteroid_fx.node.impl.ship;

import game.asteroid_fx.SpaceCraft;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import game.asteroid_fx.node.impl.Bullet;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

public class Ship extends SpaceNode {
	
	private final static Image SHIELD = new Image("file:data/space/Power/1.png");
	
	private final static String SHIP_1 = "file:data/space/Ship/1.png";
	private final static String SHIP_2 = "file:data/space/Ship/2.png";
	
	/**
	 * Conditiong if it should use WASD or arrows otherwise.
	 */
	private final boolean primary;
	
	/**
	 * Pressed keys on keyboard.
	 */
	private Set<KeyCode> pressed = new HashSet<>();
	
	/**
	 * The life of our ship.
	 */
	private int life = 400;
	private Label lifeText = new Label("100");
	private final Rectangle lifeBar;
	private final Rectangle full;
	
	/**
	 * The shield of our
	 */
	private ShipShield shield;
	
	/**
	 * The count of power shots remaining.
	 */
	private int powerShot;
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship(boolean primary) {
		super(NodeType.SHIP, new Image(primary ? SHIP_1 : SHIP_2), primary ? 100 : 800, 120);
		this.primary = primary;
		setColliding(true);
		if(primary) {
			full = new Rectangle(100, 30, 200, 20);
			full.setFill(Color.RED);
			lifeBar = new Rectangle(100, 30, 200, 20);
			lifeBar.setFill(Color.GREEN);
			lifeText.setLayoutX(150);
			lifeText.setLayoutX(30);
		} else {
			full = new Rectangle(SpaceCraft.WIDTH - 300, 30, 200, 20);
			full.setFill(Color.RED);
			lifeBar = new Rectangle(SpaceCraft.WIDTH - 300, 30, 200, 20);
			lifeBar.setFill(Color.GREEN);
			lifeText.setLayoutX(SpaceCraft.WIDTH - 200);
			lifeText.setLayoutX(30);
		}
	}
	
	@Override
	public void draw() {
		handleKeys();
		setLayoutX(getLayoutX() + getVelocityX());
		setLayoutY(getLayoutY() + getVelocityY());
		setVelocityX(getVelocityX() - (getVelocityX() * (getMoveSpeed() / 30)));
		setVelocityY(getVelocityY() - (getVelocityY() * (getMoveSpeed() / 30)));
		decreaseMoveSpeed(0.001);
	}
	
	@Override
	public void pulse() {
		//bounds
		int bound = 20;
		if(getLayoutX() < -bound) {
			setLayoutX(SpaceCraft.WIDTH - bound);
		} else if(getLayoutX() > SpaceCraft.WIDTH + bound) {
			setLayoutX(bound);
		}
		
		if(getLayoutY() < -bound) {
			setLayoutY(SpaceCraft.HEIGHT - bound);
		} else if(getLayoutY() > SpaceCraft.HEIGHT + bound) {
			setLayoutY(bound);
		}
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.METEOR) {
			SpaceCraft.remove(other);
			updateLife(life - 5);
		}
		if(other.getType() == NodeType.BULLET) {
			if(shield == null && !(((Bullet) other).getShip() == this)) {
				updateLife(life - 1);
				SpaceCraft.remove(other);
			}
		}
	}
	
	private void rotateRight(int amount) {
		setRotate(getRotate() + amount);
		if(getRotate() > 360)
			setRotate(amount);
	}
	
	private void rotateLeft(int amount) {
		setRotate(getRotate() - amount);
		if(getRotate() < 0)
			setRotate(360 - amount);
	}
	
	private void handleKeys() {
		for(KeyCode key : pressed) {
			if((primary && key == KeyCode.A) || (!primary && key == KeyCode.LEFT)) {
				rotateLeft(4);
			} else if((primary && key == KeyCode.D) || (!primary && key == KeyCode.RIGHT)) {
				rotateRight(4);
			}
			
			if((primary && key == KeyCode.W) || (!primary && key == KeyCode.UP)) {
				setMoveAngle(getRotate());
				double angle = getMoveAngle() - 90;
				if(angle < 0)
					angle = 360 + angle;
				double y = getMoveSpeed() * Math.sin(Math.toRadians(angle));
				double x = getMoveSpeed() * Math.cos(Math.toRadians(angle));
				setVelocityX(getVelocityX() + x);
				setVelocityY(getVelocityY() + y);
				increaseMoveSpeed(0.005);
			}
			
			if((primary && key == KeyCode.F) || (!primary && key == KeyCode.M)) {
				double x = getLayoutX() + getImage().getWidth() / 2 - 5;
				double y = getLayoutY() + getImage().getHeight() / 2 - 5;
				shoot(x, y);
				if(powerShot > 0) {
					Timeline spawn = new Timeline(new KeyFrame(Duration.millis(20), event -> shoot(x, y)));
					spawn.play();
					powerShot--;
				}
				
			}
		}
	}
	
	private void shoot(double x, double y) {
		Bullet bul = new Bullet(this, x, y, getRotate());
		SpaceCraft.add(bul);
		bul.toBack();
	}
	
	public void keyPress(KeyCode code) {
		pressed.add(code);
	}
	
	public void keyRelease(KeyCode code) {
		pressed.remove(code);
	}
	
	@Override
	public void onAdd() {
		SpaceCraft.ROOT.getChildren().add(full);
		SpaceCraft.ROOT.getChildren().add(lifeText);
		SpaceCraft.ROOT.getChildren().add(lifeBar);
	}
	
	@Override
	public void onRemove() {
		SpaceCraft.ROOT.getChildren().remove(full);
		SpaceCraft.ROOT.getChildren().remove(lifeText);
		SpaceCraft.ROOT.getChildren().remove(lifeBar);
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
	public void addPowerShots(int power) {
		this.powerShot += power;
	}
	
	public void updateLife(int life) {
		if(life <= 0)
			SpaceCraft.remove(this);
		if(life > 400)
			life = 400;
		this.life = life;
		lifeBar.setWidth((life / 4) * 2);
		lifeText.setText(life + "");
	}
	
	public int getLife() {
		return life;
	}
	
	public void setShield(ShipShield shield) {
		if(shield == null && this.shield != null)
			SpaceCraft.remove(this.shield);
		else if(shield != null)
			SpaceCraft.add(shield);
		this.shield = shield;
	}
	
	public ShipShield getShield() {
		return shield;
	}
	
}
