package xion.node.impl;

import xion.Game;
import xion.node.NodeType;
import xion.node.SpaceNode;
import javafx.scene.image.Image;

import java.util.Random;

public class Meteor extends SpaceNode {
	
	private final double rotate;
	
	private final double bound;
	
	private final static String ROCK = "file:data/space/meteors/meteorGrey_small2.png";
	
	public Meteor(int x, int y) {
		super(NodeType.METEOR, new Image(ROCK), x, y);
		Random gen = new Random();
		rotate = gen.nextInt(100) / 100.D;
		bound = getImage().getHeight() / 2;
		int angle = gen.nextInt(360);
		double xMove = (gen.nextInt(2) + 2) / 3;
		double yMove = (gen.nextInt(2) + 2) / 3;
		double velX = xMove * Math.cos(Math.toRadians(angle));
		double velY = yMove * Math.sin(Math.toRadians(angle));
		setVelocityX(gen.nextBoolean() ? -velX : velX);
		setVelocityY(gen.nextBoolean() ? -velY : velY);
	}
	
	@Override
	public void draw() {
		rotateProperty().set(getRotate() + rotate);
		setLayoutX(getLayoutX() + getVelocityX());
		setLayoutY(getLayoutY() + getVelocityY());
	}
	
	@Override
	public void pulse() {
		//bounds
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
		
	}
}
