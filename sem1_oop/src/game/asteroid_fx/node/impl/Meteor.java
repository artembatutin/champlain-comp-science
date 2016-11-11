package game.asteroid_fx.node.impl;

import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.image.Image;

import java.util.Random;

public class Meteor extends SpaceNode {
	
	private final double rotate;
	
	private final static String ROCK = "file:data/space/PNG/Meteors/meteorGrey_small2.png";
	
	public Meteor(int x, int y) {
		super(NodeType.METEOR, new Image(ROCK), x, y);
		rotate = new Random().nextInt(100) / 100.D;
	}
	
	@Override
	public void draw() {
		rotateProperty().set(getRotate() + rotate);
	}
	
	@Override
	public void pulse() {
		
	}
	
	@Override
	public void collide(SpaceNode other) {
		
	}
}
