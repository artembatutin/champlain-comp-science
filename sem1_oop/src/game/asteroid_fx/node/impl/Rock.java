package game.asteroid_fx.node.impl;

import game.asteroid_fx.node.SpaceNode;
import javafx.scene.paint.Color;

public class Rock extends SpaceNode {
	
	private double rotate = 0;
	
	/**
	 * The default rock polygon points.
	 */
	private static final double[] ROCK = {-20, 20, -13, 23, 0, 17, 20, 20, 22, 16, 20, -20, 12, -22, 2, -14, -10, -17, -22, -20, -16, -5};
	
	public Rock(int x, int y) {
		super(x, y, Color.ORANGE, ROCK);
	}
	
	@Override
	public void draw() {
		rotate += .5;
		rotateProperty().set(rotate);
	}
	
	@Override
	public void pulse() {
		
	}
}
