package xion.node.impl;

import xion.Game;
import xion.node.NodeType;
import xion.node.SpaceNode;
import xion.node.impl.ship.Ship;
import xion.node.impl.ship.ShipShield;
import javafx.scene.image.Image;

import java.util.Random;

public class PowerUp extends SpaceNode {
	
	private final PowerType type;
	
	/**
	 * Creating our {@link PowerUp}.
	 */
	public PowerUp(PowerType type, double x, double y) {
		super(NodeType.SHIP, type.getImage(), x, y);
		setColliding(true);
		this.type = type;
	}
	
	@Override
	public void draw() {
	}
	
	@Override
	public void pulse() {
	}
	
	@Override
	public void collide(SpaceNode other) {
		if(other.getType() == NodeType.SHIP) {
			Game.remove(this);
			type.effect((Ship) other);
		}
	}
	
	public enum PowerType {
		BOLT("bolt.png") {
			@Override
			public void effect(Ship ship) {
				ship.addPowerShots(50);
			}
		},
		PILL("pill.png") {
			@Override
			public void effect(Ship ship) {
				ship.updateLife(ship.getLife() + 40);
			}
		},
		SHIELD("shield.png") {
			@Override
			public void effect(Ship ship) {
				if(ship.getShield() == null)
					ship.setShield(new ShipShield(ship));
			}
		};
		
		private Image image;
		
		PowerType(String image) {
			this.image = new Image("file:data/space/power/" + image);
		}
		
		public void effect(Ship ship) {
		}
		
		public Image getImage() {
			return image;
		}
		
		public static PowerType random() {
			Random gen = new Random();
			PowerType[] values = values();
			return values[gen.nextInt(values.length)];
		}
	}
	
}
