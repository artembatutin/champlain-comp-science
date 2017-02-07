package game.battlefield.node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A space node attributed in our game.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public abstract class Node extends ImageView {
	
	/**
	 * The type of our node.
	 */
	private final NodeType type;
	
	/**
	 * The state of this {@link Node}.
	 */
	private NodeState state;
	
	/**
	 * The horizontal velocity of this {@link Node}.
	 */
	private double velocityX;
	
	/**
	 * The vertical velocity of this {@link Node}.
	 */
	private double velocityY;
	
	/**
	 * The moving speed of this {@link Node}.
	 */
	private double moveSpeed;
	
	/**
	 * The moving angle of this {@link Node}.
	 */
	private double moveAngle;
	
	/**
	 * The flag which determines if this {@link Node} collides with others.
	 */
	private boolean colliding;
	
	/**
	 * Our main construction of {@link Node}.
	 * @param image the image of our node.
	 * @param x     the x layout coordinate.
	 * @param y     the y layout coordinate.
	 */
	public Node(NodeType type, Image image, double x, double y) {
		super(image);
		this.type = type;
		//Setting the location.
		setLayoutX(x);
		setLayoutY(y);
	}
	
	/**
	 * The logic method for this {@link Node}.
	 * @param delta the delta time difference
	 */
	public abstract void pulse(float delta);
	
	/**
	 * The collision method when two nodes collide.
	 * @param other the other node which is colliding.
	 */
	public void collide(Node other) {
		
	};
	
	/**
	 * Gets the {@link NodeState}.
	 * @return the type of this {@link Node}
	 */
	public NodeType getType() {
		return type;
	}
	
	/**
	 * Gets the {@link NodeState}.
	 * @return the state of this {@link Node}
	 */
	public NodeState getState() {
		return state;
	}
	
	/**
	 * Sets the state of this {@link Node}.
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
	 * The method executed when this {@link Node} is added to the world.
	 */
	public void onAdd() {
	}
	
	/**
	 * The method executed when this {@link Node} dies.
	 */
	public void onRemove() {
	}
	
	public double getVelocityX() {
		return velocityX;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	public double getVelocityY() {
		return velocityY;
	}
	
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	public double getMoveSpeed() {
		return moveSpeed;
	}
	
	public void setMoveSpeed(double speed) {
		moveSpeed = speed;
	}
	
	public void increaseMoveSpeed(double speed) {
		this.moveSpeed += speed;
		if(this.moveSpeed >= 1)
			this.moveSpeed = 1;
	}
	
	public void decreaseMoveSpeed(double speed) {
		this.moveSpeed -= speed;
		if(this.moveSpeed < 0.1)
			this.moveSpeed = 0.1;
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
	
	public boolean colliding(Node other) {
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
