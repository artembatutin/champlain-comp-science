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
	 * The horizontal velocity of this {@link SpaceNode}.
	 */
	private double velocityX;
	
	/**
	 * The vertical velocity of this {@link SpaceNode}.
	 */
	private double velocityY;
	
	/**
	 * The moving speed of this {@link SpaceNode}.
	 */
	private double moveSpeed;
	
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
	 * The collision method when two nodes collide.
	 * @param other the other node which is colliding.
	 */
	public abstract void collide(SpaceNode other);
	
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
		if(this.moveSpeed < 0)
			this.moveSpeed = 0;
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
	
}
