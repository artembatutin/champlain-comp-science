package game.asteroid_fx.node.impl;

import game.asteroid_fx.Game;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.image.Image;

import java.util.Random;

public class Meteor extends SpaceNode {
	
	private final double rotate;
	
	private final double bound;
	
	public Meteor(int x, int y) {
		super(NodeType.METEOR, new Image("file:" + RockType.random().img), x, y);
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
	
	/**
	 * Enumeration of several meteor types.
	 */
	private enum RockType {
		/**
		 * A small meteor.
		 */
		SMALL("meteorGrey_tiny"),
		
		/**
		 * A medium meteor.
		 */
		MEDIUM("meteorGrey_small"),
		
		/**
		 * A large meteor.
		 */
		LARGE("meteorGrey_med");
		
		/**
		 * The path to the image file.
		 */
		private final String img;
		
		/**
		 * Constructs a new {@link RockType}.
		 * @param img the image name.
		 */
		RockType(String img) {
			this.img = "./data/space/meteors/" +img + (new Random().nextInt(1) + 1) + ".png";
		}
		
		/**
		 * Getting an random rock type.
		 * @return rock type.
		 */
		public static RockType random() {
			Random gen = new Random();
			RockType[] values = values();
			return values[gen.nextInt(values.length)];
		}
		
	}
}
