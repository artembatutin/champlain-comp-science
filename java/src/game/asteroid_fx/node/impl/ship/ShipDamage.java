package game.asteroid_fx.node.impl.ship;

import game.asteroid_fx.Game;
import game.asteroid_fx.node.NodeType;
import game.asteroid_fx.node.SpaceNode;
import javafx.scene.image.Image;

import static game.asteroid_fx.node.impl.ship.ShipDamage.DamageStage.NOT_VISIBLE;

/**
 * Represents the image layer of damage on our {@link Ship}.
 */
public class ShipDamage extends SpaceNode {
	
	/**
	 * The stage of this damage, visually.
	 */
	private DamageStage stage;
	
	/**
	 * Creates a new {@link ShipDamage}.
	 */
	public ShipDamage() {
		super(NodeType.SHIP_ASSET, null, 0, 0);
	}
	
	public void draw(double rotate, double x, double y) {
		setRotate(rotate);
		setLayoutX(x);
		setLayoutY(y);
	}
	
	@Override
	public void draw() {
		
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
	 * @param ship to change view priority.
	 * @param stage new stage to set.
	 */
	public void setStage(Ship ship, DamageStage stage) {
		if(stage == NOT_VISIBLE) {
			setImage(null);
			Game.remove(this);
		} else {
			Game.remove(this);
			setImage(new Image(stage.getImg()));
			Game.add(this);
			this.toBack();
			ship.toBack();
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
