package game.asteroid_fx.node.impl;

import game.asteroid_fx.Game;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import game.asteroid_fx.node.impl.ship.Ship;
import javafx.scene.image.Image;

public class Bullet extends SpaceNode {
	
	private final static String BULLP = "file:data/space/lasers/bullet.png";
	private final static String BULLS = "file:data/space/lasers/bullet2.png";
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
		} else if(getLayoutX() > Game.WIDTH + bound) {
			remove = true;
		}
		
		if(getLayoutY() < -bound) {
			remove = true;
		} else if(getLayoutY() > Game.HEIGHT + bound) {
			remove = true;
		}
		if(remove)
			Game.remove(this);
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.METEOR) {
			Game.remove(other);
			Game.remove(this);
		}
	}
	
	public Ship getShip() {
		return ship;
	}
}
