package game.asteroid_fx.node.impl;

import game.asteroid_fx.SpaceCraft;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bullet extends SpaceNode {
	
	private final static String BULLP = "file:data/space/PNG/Lasers/bullet.png";
	
	private final static String BULLS = "file:data/space/PNG/Lasers/bullet2.png";
	
	private final Ship ship;
	
	/**
	 * Creates a {@link Bullet} instance.
	 * @param x the x coordinate of the bullet.
	 * @param y the y coordinate of the bullet.
	 */
	public Bullet(Ship ship, double x, double y, double angle) {
		super(NodeType.BULLET, new Image(ship.isPrimary() ? BULLP : BULLS), x, y);
		this.ship = ship;
		setMoveAngle(angle);
		angle = getMoveAngle() - 90;
		if(angle < 0)
			angle = 360 + angle;
		double velY = 9 * Math.sin(Math.toRadians(angle));
		double velX = 9 * Math.cos(Math.toRadians(angle));
		setMoveAngle(angle);
		setVelocityX(velX);
		setVelocityY(velY);
		setColliding(true);
	}
	
	@Override
	public void draw() {
		this.toBack();
		setLayoutX(getLayoutX() + getVelocityX());
		setLayoutY(getLayoutY() + getVelocityY());
	}
	
	@Override
	public void pulse() {
		//bounds
		int bound = 20;
		boolean remove = false;
		if(getLayoutX() < -bound) {
			remove = true;
		} else if(getLayoutX() > SpaceCraft.WIDTH + bound) {
			remove = true;
		}
		
		if(getLayoutY() < -bound) {
			remove = true;
		} else if(getLayoutY() > SpaceCraft.HEIGHT + bound) {
			remove = true;
		}
		if(remove)
			SpaceCraft.remove(this);
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.METEOR) {
			SpaceCraft.remove(other);
			SpaceCraft.remove(this);
		}
	}
	
	public Ship getShip() {
		return ship;
	}
}
