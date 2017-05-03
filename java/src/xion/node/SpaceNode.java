package xion.node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A space node attributed in our game.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public abstract class SpaceNode extends ImageView {
	
	/**
	 * The type of our node.
	 */
	private final NodeType type;
	
	/**
	 * The state of this {@link SpaceNode}.
	 */
	private NodeState state;
	
	/**
	 * The horizontal velocity of this {@link SpaceNode}.
	 */
	private double velocityX;
	
	/**
	 * The vertical velocity of this {@link SpaceNode}.
	 */
	private double velocityY;
	
	/**
	 * The moving angle of this {@link SpaceNode}.
	 */
	private double moveAngle;
	
	/**
	 * The flag which determines if this {@link SpaceNode} collides with others.
	 */
	private boolean colliding;
	
	/**
	 * Our main construction of {@link SpaceNode}.
	 * @param image the image of our node.
	 * @param x     the x layout coordinate.
	 * @param y     the y layout coordinate.
	 */
	public SpaceNode(NodeType type, Image image, double x, double y) {
		super(image);
		this.type = type;
		//Setting the location.
		setLayoutX(x);
		setLayoutY(y);
	}
	
	/**
	 * The logic method for this {@link SpaceNode}.
	 */
	public abstract void pulse();
	
	/**
	 * The collision method when two nodes collide.
	 * @param other the other node which is colliding.
	 */
	public abstract void collide(SpaceNode other);
	
	/**
	 * Gets the {@link NodeState}.
	 * @return the type of this {@link SpaceNode}
	 */
	public NodeType getType() {
		return type;
	}
	
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
	public void onRemove() {
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public void addVelocityX(double amount) {
		velocityX += amount;
	}
	
	public void revertVelocityX(double amount) {
		this.velocityX += -this.velocityX * amount;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
	
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public void addVelocityY(double amount) {
		velocityY += amount;
	}
	
	public void revertVelocityY(double amount) {
		this.velocityY += -this.velocityY * amount;
	}
	
	public double getMoveAngle() {
		return this.moveAngle;
	}
	
	public void setMoveAngle(double angle) {
		moveAngle = angle;
	}
	
	public boolean isColliding() {
		return colliding;
	}
	
	public void setColliding(boolean colliding) {
		this.colliding = colliding;
	}
	
	public boolean colliding(SpaceNode other) {
		if(getImage() == null || other.getImage() == null) {
			return false;
		}
		double x = getLayoutX();
		double y = getLayoutY();
		double rx = other.getLayoutX();
		double ry = other.getLayoutY();
		double w = getImage().getWidth();
		double h = getImage().getHeight();
		double rw = other.getImage().getWidth();
		double rh = other.getImage().getHeight();
		return x < rx + rw && x + w > rx && y < ry + rh && y + h > ry;
	}
	
}
