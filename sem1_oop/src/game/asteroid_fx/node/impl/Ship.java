package game.asteroid_fx.node.impl;

import game.asteroid_fx.SpaceCraft;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ship extends SpaceNode {
	
	private static double SIZE = 30;
	
	/**
	 * Pressed keys on keyboard.
	 */
	private Set<KeyCode> pressed = new HashSet<>();
	
	/**
	 * Conditiong if it should use WASD or arrows otherwise.
	 */
	private final boolean primary;
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship(boolean primary) {
		super(primary ? 100 : 800, 120, primary ? Color.AQUA : Color.RED,
				SIZE, 0,
				- SIZE * 0.5, -SIZE * 0.6,
				-SIZE * 0.8, 0,
				-SIZE * 0.5, SIZE * 0.6
		);
		this.primary = primary;
		setColliding(true);
	}
	
	@Override
	public void draw() {
		handleKeys();
		if(getMoveSpeed() == 0)
			return;
		setLayoutX(getLayoutX() + getVelocityX() * getMoveSpeed());
		setLayoutY(getLayoutY() + getVelocityY() * getMoveSpeed());
		setVelocityX(getVelocityX() - (getVelocityX() * (getMoveSpeed() / 40)));
		setVelocityY(getVelocityY() - (getVelocityY() * (getMoveSpeed() / 40)));
		decreaseMoveSpeed(0.0005);
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
		Random gen = new Random();
		int r = gen.nextInt(100);
		int g = gen.nextInt(100);
		int b = gen.nextInt(100);
		setFill(Color.color(r / 100.0, g / 100.0, b / 100.0));
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
					double y = 2 * Math.sin(Math.toRadians(getMoveAngle()));
					double x = 2 * Math.cos(Math.toRadians(getMoveAngle()));
					setVelocityX(getVelocityX() + x);
					setVelocityY(getVelocityY() + y);
					increaseMoveSpeed(0.002);
				}
		}
	}
	
	public void keyPress(KeyCode code) {
		pressed.add(code);
	}
	
	public void keyRelease(KeyCode code) {
		pressed.remove(code);
	}
	
}
