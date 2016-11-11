package game.asteroid_fx.node.impl;

import game.asteroid_fx.SpaceCraft;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class Ship extends SpaceNode {
	
	private final static String BLUE = "file:data/space/PNG/playerShip3_blue.png";
	private final static String ORANGE = "file:data/space/PNG/playerShip3_orange.png";
	
	/**
	 * Pressed keys on keyboard.
	 */
	private Set<KeyCode> pressed = new HashSet<>();
	
	/**
	 * Conditiong if it should use WASD or arrows otherwise.
	 */
	private final boolean primary;
	
	/**
	 * The life of our ship.
	 */
	private int life = 100;
	private Label lifeText = new Label("100");
	private final Rectangle lifeBar;
	private final Rectangle full;
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship(boolean primary) {
		super(NodeType.SHIP, new Image(primary ? BLUE : ORANGE), primary ? 100 : 800, 120);
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
		SpaceCraft.ROOT.getChildren().add(full);
		SpaceCraft.ROOT.getChildren().add(lifeText);
		SpaceCraft.ROOT.getChildren().add(lifeBar);
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
			updateLife(life - 1);
		}
		if(other.getType() == NodeType.BULLET && !(((Bullet) other).getShip() == this)) {
			updateLife(life - 1);
			SpaceCraft.remove(other);
		}
	}
	
	public void rotateRight(int amount) {
		setRotate(getRotate() + amount);
		if(getRotate() > 360)
			setRotate(amount);
	}
	
	public void rotateLeft(int amount) {
		setRotate(getRotate() - amount);
		if(getRotate() < 0)
			setRotate(360 - amount);
	}
	
	public void handleKeys() {
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
					double x = getLayoutX() + getImage().getWidth() / 2;
					double y = getLayoutY() + getImage().getHeight() / 2;
					SpaceCraft.add(new Bullet(this, x, y, getRotate()));
				}
		}
	}
	
	public void keyPress(KeyCode code) {
		pressed.add(code);
	}
	
	public void keyRelease(KeyCode code) {
		pressed.remove(code);
	}
	
	@Override
	public void onAdd() {
		
	}
	
	public void updateLife(int life) {
		this.life = life;
		lifeBar.setWidth(life * 2);
		lifeText.setText(life + "");
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
}
