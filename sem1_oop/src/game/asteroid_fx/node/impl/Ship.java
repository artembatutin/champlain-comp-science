package game.asteroid_fx.node.impl;

import game.asteroid_fx.node.SpaceNode;
import javafx.scene.paint.Color;

public class Ship extends SpaceNode {
	
	private static double SIZE = 10;
	
	private int speed = 0;
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship() {
		super(100, 120, Color.AQUA,
				-SIZE-1, SIZE*1.4,
				0, SIZE*1.2,
				SIZE+1, SIZE*1.4,
				SIZE, 0,
				SIZE*0.5, 1,
				0, -SIZE,
				-SIZE*0.5, 1,
				-SIZE, 0);
	}
	
	@Override
	public void draw() {
	//	setLayoutX(getLayoutX() + 1);
		if(speed == 0)
			return;
		
		double angle = getRotate();
		double y = speed * Math.sin(Math.toRadians(angle));
		double x = speed * Math.cos(Math.toRadians(angle));
		
		setLayoutX(getLayoutX() + x);
		setLayoutY(getLayoutY() + y);
	}
	
	@Override
	public void pulse() {
		
	}
	
	public void rotateRight(int amount) {
		System.out.println(getRotate());
		setRotate(getRotate() + amount);
		if(getRotate() > 360)
			setRotate(amount);
	}
	
	public void rotateLeft(int amount) {
		System.out.println(getRotate());
		setRotate(getRotate() - amount);
		if(getRotate() < 0)
			setRotate(360-amount);
	}
	
	public void increaseSpeed(int speed) {
		this.speed += speed;
		System.out.println("speed" + speed);
		if(this.speed >= 30)
			this.speed = 30;
	}
	
	public void decreaseSpeed(int speed) {
		this.speed -= speed;
		if(this.speed < 0)
			this.speed = 0;
	}
	
	public int getSpeed() {
		return speed;
	}
	
}
