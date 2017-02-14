package xion.node.impl.ship;

import xion.Game;
import xion.node.NodeType;
import xion.node.SpaceNode;
import xion.node.impl.Bullet;
import javafx.scene.image.Image;

/**
 * Represents the shield of our {@link Ship}.
 */
public class ShipShield extends SpaceNode {
	
	private final static Image SHIP_1 = new Image("file:data/space/Effects/shield1.png");
	private final static Image SHIP_2 = new Image("file:data/space/Effects/shield2.png");
	private final static Image SHIP_3 = new Image("file:data/space/Effects/shield3.png");
	
	private final Ship ship;
	
	private int life = 120;
	
	public ShipShield(Ship ship) {
		super(NodeType.SHIP_ASSET, SHIP_3, ship.getLayoutX(), ship.getLayoutY());
		this.ship = ship;
		setColliding(true);
		setLayoutX(ship.getLayoutX());
		setLayoutY(ship.getLayoutY());
	}
	
	@Override
	public void draw() {
		setRotate(ship.getRotate());
		setLayoutX(ship.getLayoutX() - 15);
		setLayoutY(ship.getLayoutY() - 18.2);
	}
	
	@Override
	public void pulse() {
		
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.BULLET) {
			Bullet bul = (Bullet) other;
			if(bul.getShip() != ship) {
				Game.remove(other);
				onHit();
			}
		}
	}
	
	/**
	 * Operation handled once a bullet hits our shield.
	 */
	private void onHit() {
		int prev = life;
		if(prev == 80)
			setImage(SHIP_2);
		if(prev == 40)
			setImage(SHIP_1);
		life = prev - 1;
		if(life <= 0) {
			ship.setShield(null);
		}
	}
}
