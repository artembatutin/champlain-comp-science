package game.asteroid_fx.node.impl;

import game.asteroid_fx.node.SpaceNode;
import javafx.scene.paint.Color;

public class Ship extends SpaceNode {
	
	/**
	 * Ship polygon values.
	 */
	private static final double[] SHIP = {-6, 6, -3, 7, 0, 7, 3, 7, 6, 6, 0, -7};
	
	/**
	 * Creating our {@link Ship}.
	 */
	public Ship() {
		super(100, 120, Color.AQUA, SHIP);
	}
	
	@Override
	public void pulse() {
		setLayoutX(getLayoutX() + 1);
	}
	
	@Override
	public void draw() {
		
	}
	
}
