package game.asteroid_fx.node;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

import static game.asteroid_fx.node.NodeState.CREATED;

/**
 * A space node attributed in our game.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public abstract class SpaceNode extends Polygon {
	
	/**
	 * The state of this {@link SpaceNode}, {@link NodeState#CREATED} by default.
	 */
	private NodeState state = CREATED;
	
	/**
	 * The horizontal velocity of our {@link SpaceNode}.
	 */
	private int velocityX;
	
	/**
	 * The vertical velocity of our {@link SpaceNode}.
	 */
	private int velocityY;
	
	/**
	 * The moving angle of our {@link SpaceNode}.
	 */
	private int moveAngle;
	
	/**
	 * Our main construction of {@link SpaceNode}.
	 * @param x      the x layout coordinate.
	 * @param y      the y layout coordinate.
	 * @param fill   the background fill color.
	 * @param points the points of this polygon.
	 */
	public SpaceNode(int x, int y, Paint fill, double... points) {
		//Setting the location.
		setLayoutX(x);
		setLayoutY(y);
		
		//Setting the color.
		if(fill != null)
			setFill(fill);
		
		//Adding points.
		for(double p : points)
			getPoints().add(p);
	}
	
	/**
	 * The drawing method for this {@link SpaceNode}.
	 */
	public abstract void draw();
	
	/**
	 * The logic method for this {@link SpaceNode}.
	 */
	public abstract void pulse();
	
	/**
	 * Gets the {@link NodeState}.
	 * @return the state of this {@link SpaceNode}
	 */
	public NodeState getState() {
		return state;
	}
	
	/**
	 * Sets the state of this {@link SpaceNode}.
	 * @param state the new state to set.
	 */
	public void setState(NodeState state) {
		this.state = state;
		switch(state) {
			case ALIVE:
				onAdd();
				break;
			case DEAD:
				onDie();
				break;
			case REMOVED:
				onRemove();
				break;
		}
	}
	
	/**
	 * The method executed when this {@link SpaceNode} is added to the world.
	 */
	public void onAdd() {
	}
	
	/**
	 * The method executed when this {@link SpaceNode} dies.
	 */
	public void onDie() {
	}
	
	/**
	 * The method executed when this {@link SpaceNode} is removed from the world.
	 */
	public void onRemove() {
	}
	
	public int getVelocityX() {
		return velocityX;
	}
	
	public void incrementVelocityX(int amount) {
		this.velocityX += amount;
	}
	
	public void decrementVelocityX(int amount) {
		this.velocityX -= amount;
	}
	
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	
	public int getVelocityY() {
		return velocityY;
	}
	
	public void incrementVelocityY(int amount) {
		this.velocityY += amount;
	}
	
	public void decrementVelocityY(int amount) {
		this.velocityY -= amount;
	}
	
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public void incrementMoveAngle(int amount) {
		this.moveAngle += amount;
	}
	
	public void decrementMoveAngle(int amount) {
		this.moveAngle -= amount;
	}
	
	public void setMoveAngle(int velocityY) {
		this.moveAngle = velocityY;
	}
	
}
