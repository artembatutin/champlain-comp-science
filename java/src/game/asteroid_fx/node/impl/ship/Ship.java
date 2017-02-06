package game.asteroid_fx.node.impl.ship;

import game.asteroid_fx.Game;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import game.asteroid_fx.node.impl.Bullet;
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

import static game.asteroid_fx.node.impl.ship.ShipDamage.DamageStage.*;

public class Ship extends SpaceNode {
	
	/**
	 * The primary ship image path.
	 */
	private final static String SHIP_1 = "file:data/space/ship/1.png";
	
	/**
	 * The secondary ship image path.
	 */
	private final static String SHIP_2 = "file:data/space/ship/2.png";
	
	/**
	 * Condition if ship is primary and playing with WASD or secondary with arrows.
	 */
	private final boolean primary;
	
	/**
	 * Pressed keys on keyboard.
	 */
	private Set<KeyCode> pressed = new HashSet<>();
	
	/**
	 * The damage of our ship.
	 */
	private final ShipDamage damage;
	
	/**
	 * The shield of this ship.
	 */
	private ShipShield shield;
	
	/**
	 * The life of our ship.
	 */
	private int life = 400;
	private Label lifeText = new Label("100");
	private final Rectangle lifeBar;
	private final Rectangle full;
	
	/**
	 * The count of power shots remaining.
	 */
	private int powerShot;
	
	/**
	 * The waiting time before shooting another bullet.
	 */
	private int waitShoot;
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship(boolean primary) {
		super(NodeType.SHIP, new Image(primary ? SHIP_1 : SHIP_2), primary ? 100 : 800, 120);
		this.primary = primary;
		this.damage = new ShipDamage();
		setColliding(true);
		if(primary) {
			full = new Rectangle(100, 30, 200, 20);
			full.setFill(Color.BLACK);
			full.setStroke(Color.BLACK);
			full.setOpacity(0.5);
			lifeBar = new Rectangle(100, 30, 200, 19);
			lifeBar.setFill(Color.BLUE);
			lifeBar.setOpacity(0.7);
			lifeText.setLayoutX(150);
			lifeText.setLayoutX(30);
		} else {
			full = new Rectangle(Game.WIDTH - 300, 30, 200, 20);
			full.setFill(Color.BLACK);
			full.setStroke(Color.BLACK);
			full.setOpacity(0.5);
			lifeBar = new Rectangle(Game.WIDTH - 300, 30, 200, 20);
			lifeBar.setFill(Color.RED);
			lifeBar.setOpacity(0.7);
			lifeText.setLayoutX(Game.WIDTH - 200);
			lifeText.setLayoutX(30);
		}
	}
	
	@Override
	public void draw() {
		damage.draw(getRotate(), getLayoutX(), getLayoutY());
	}
	
	@Override
	public void pulse() {
		handleKeys();
		setLayoutX(getLayoutX() + getVelocityX());
		setLayoutY(getLayoutY() + getVelocityY());
		revertVelocityX(0.025f);
		revertVelocityY(0.025f);
		
		
		//bounds
		int bound = 20;
		if(getLayoutX() < -bound) {
			setLayoutX(Game.WIDTH - bound);
		} else if(getLayoutX() > Game.WIDTH + bound) {
			setLayoutX(bound);
		}
		
		if(getLayoutY() < -bound) {
			setLayoutY(Game.HEIGHT - bound);
		} else if(getLayoutY() > Game.HEIGHT + bound) {
			setLayoutY(bound);
		}
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.METEOR) {
			Game.remove(other);
			updateLife(life - 5);
		}
		if(other.getType() == NodeType.BULLET) {
			if(shield == null && !(((Bullet) other).getShip() == this)) {
				updateLife(life - 5);
				Game.remove(other);
			}
		}
	}
	
	@Override
	public void onAdd() {
		Game.ROOT.getChildren().add(full);
		Game.ROOT.getChildren().add(lifeText);
		Game.ROOT.getChildren().add(lifeBar);
	}
	
	@Override
	public void onRemove() {
		Game.ROOT.getChildren().remove(full);
		Game.ROOT.getChildren().remove(lifeText);
		Game.ROOT.getChildren().remove(lifeBar);
		Game.ROOT.getChildren().remove(damage);
	}
	
	/**
	 * Rotating the ship to the right.
	 * @param amount rotation amount.
	 */
	private void rotateRight(int amount) {
		setRotate(getRotate() + amount);
		if(getRotate() > 360)
			setRotate(amount);
	}
	
	/**
	 * Rotate the ship to the left.
	 * @param amount rotation amount.
	 */
	private void rotateLeft(int amount) {
		setRotate(getRotate() - amount);
		if(getRotate() < 0)
			setRotate(360 - amount);
	}
	
	/**
	 * Updating all components releated to the life of the ship.
	 * @param life the current life of the ship.
	 */
	public void updateLife(int life) {
		//death and capping.
		if(life <= 0)
			Game.remove(this);
		if(life > 400)
			life = 400;
		
		//visual damage.
		if(life < 350 && damage.getStage() != LOW)
			damage.setStage(this, LOW);
		if(life < 200 && damage.getStage() != ShipDamage.DamageStage.MEDIUM)
			damage.setStage(this, MEDIUM);
		if(life < 200 && damage.getStage() != ShipDamage.DamageStage.MEDIUM)
			damage.setStage(this, HIGH);
		
		//ui update
		this.life = life;
		lifeBar.setWidth((life / 4) * 2);
		lifeText.setText(life + "");
	}
	
	/**
	 * Handles maneuvers depending on pressed keys.
	 */
	private void handleKeys() {
		for(KeyCode key : pressed) {
			
			//rotation.
			if((primary && key == KeyCode.A) || (!primary && key == KeyCode.LEFT)) {
				rotateLeft(4);
			} else if((primary && key == KeyCode.D) || (!primary && key == KeyCode.RIGHT)) {
				rotateRight(4);
			}
			
			//acceleration.
			if((primary && key == KeyCode.W) || (!primary && key == KeyCode.UP)) {
				setMoveAngle(getRotate());
				double angle = getMoveAngle() - 90;
				if(angle < 0)
					angle = 360 + angle;
				double rad = Math.toRadians(angle);
				double speed = 0.5f;
				double y = speed * Math.sin(rad);
				double x = speed * Math.cos(rad);
				addVelocityX(x);
				addVelocityY(y);
			}
			
			//shooting.
			if(waitShoot > 5 && ((primary && key == KeyCode.F) || (!primary && key == KeyCode.M))) {
				final double x = getLayoutX() + getImage().getWidth() / 2 - 5;
				final double y = getLayoutY() + getImage().getHeight() / 2 - 5;
				shoot(x, y);
				if(powerShot > 0) {
					Timeline delay = new Timeline(new KeyFrame(Duration.millis(200), event -> {
						final double x2 = getLayoutX() + getImage().getWidth() / 2 - 5;
						final double y2 = getLayoutY() + getImage().getHeight() / 2 - 5;
						shoot(x2, y2);
					}));
					delay.setCycleCount(1);
					delay.play();
					powerShot--;
				}
				waitShoot = 0;
			}
		}
		waitShoot++;
	}
	
	/**
	 * Adding a key on press to listener.
	 * @param code the key pressed.
	 */
	public void keyPress(KeyCode code) {
		pressed.add(code);
	}
	
	/**
	 * Removing a key on release from listener.
	 * @param code the key released.
	 */
	public void keyRelease(KeyCode code) {
		pressed.remove(code);
	}
	
	/**
	 * Shooting a bullet.
	 * @param x the x starting position of our bullet.
	 * @param y the y starting position of our bullet.
	 */
	private void shoot(double x, double y) {
		Bullet bul = new Bullet(this, x, y, getRotate());
		Game.add(bul);
		bul.toBack();
	}
	
	/**
	 * Adding power shots that make greater damage.
	 * @param power amount of power shots to add.
	 */
	public void addPowerShots(int power) {
		this.powerShot += power;
	}
	
	
	/**
	 * Getting a condition if this ship is the first player or second player ship.
	 * @return {@code true} if it's first player, {@code false} if second.
	 */
	public boolean isPrimary() {
		return primary;
	}
	
	/**
	 * Gets the life amount of this ship.
	 * @return ship's life.
	 */
	public int getLife() {
		return life;
	}
	
	/**
	 * Setting a shield protection for the ship.
	 * @param shield the new shield instance.
	 */
	public void setShield(ShipShield shield) {
		if(shield == null && this.shield != null)
			Game.remove(this.shield);
		else if(shield != null)
			Game.add(shield);
		this.shield = shield;
	}
	
	/**
	 * Gets the shield protection instance.
	 * @return ship's shield.
	 */
	public ShipShield getShield() {
		return shield;
	}
	
}
