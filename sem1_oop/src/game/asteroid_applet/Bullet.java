package game.asteroid_applet;

import java.awt.Rectangle;

//Bullet class - polygonal shape of bullet
public class Bullet extends BaseVectorShape {
	
	//bounding rectangle
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int) getX(), (int) getY(), 1, 1);
		return r;
	}
	
	Bullet() {
		//create the bullet shape
		setShape(new Rectangle(0, 0, 1, 1));
		setAlive(false);
	}
	
}
