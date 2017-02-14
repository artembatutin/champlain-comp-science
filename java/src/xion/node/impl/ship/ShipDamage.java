package xion.node.impl.ship;

import xion.Game;
import xion.node.NodeType;
import xion.node.SpaceNode;
import javafx.scene.image.Image;

import static xion.node.impl.ship.ShipDamage.DamageStage.NOT_VISIBLE;

/**
 * Represents the image layer of damage on our {@link Ship}.
 */
public class ShipDamage extends SpaceNode {
	
	/**
	 * The ship to which this damage image is applied on.
	 */
	private final Ship ship;
	
	/**
	 * The stage of this damage, visually.
	 */
	private DamageStage stage;
	
	/**
	 * Creates a new {@link ShipDamage}.
	 * @param ship the damaged ship.
	 */
	public ShipDamage(Ship ship) {
		super(NodeType.SHIP_ASSET, null, ship.getLayoutX(), ship.getLayoutY());
		this.ship = ship;
	}
	
	@Override
	public void draw() {
		setRotate(ship.getRotate());
		setLayoutX(ship.getLayoutX());
		setLayoutY(ship.getLayoutY());
	}
	
	@Override
	public void pulse() {
		
	}
	
	@Override
	public void collide(SpaceNode other) {
		
	}
	
	/**
	 * Getting the {@link #stage} the ship is in.
	 * @return damage stage.
	 */
	public DamageStage getStage() {
		return stage;
	}
	
	/**
	 * Setting the stage of this damage.
	 * @param stage new stage to set.
	 */
	public void setStage(DamageStage stage) {
		if(stage == NOT_VISIBLE) {
			setImage(null);
			Game.remove(this);
		} else {
			Game.remove(this);
			setImage(new Image(stage.getImg()));
			Game.add(this);
		}
		this.stage = stage;
	}
	
	/**
	 * An enumeration of possible damage stages.
	 */
	public enum DamageStage {
		/**
		 * Represents damage that isn't yet perceived visually.
		 */
		NOT_VISIBLE(""),
		
		/**
		 * Represents minor damage on ship.
		 */
		LOW("file:data/space/damage/damage1.png"),
		
		/**
		 * Represents medium damage on ship.
		 */
		MEDIUM("file:data/space/damage/damage2.png"),
		
		/**
		 * Represents high damage on ship.
		 */
		HIGH("file:data/space/damage/damage3.png");
		
		/**
		 * The image path.
		 */
		private final String img;
		
		/**
		 * Constructing our {@link DamageStage}.
		 * @param img the image path.
		 */
		DamageStage(String img) {
			this.img = img;
		}
		
		/**
		 * Gets the image path of this stage.
		 * @return img path.
		 */
		public String getImg() {
			return img;
		}
	}
}
