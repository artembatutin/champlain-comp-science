package game.asteroid_fx.node.impl;

import game.asteroid_fx.node.SpaceNode;
import javafx.scene.paint.Color;

public class Bullet extends SpaceNode {
	
	/**
	 * Creates a {@link Bullet} instance.
	 * @param x the x coordinate of the bullet.
	 * @param y the y coordinate of the bullet.
	 */
	public Bullet(int x, int y) {
		super(x, y, Color.WHITE, 0D, 1D, 0D, 1D);
	}
	
	@Override
	public void draw() {
		
	}
	
	@Override
	public void pulse() {
		
	}
}
