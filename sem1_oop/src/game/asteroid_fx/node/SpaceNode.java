package game.asteroid_fx.node;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * A space node attributed in our game.
 * @author Artem Batutin <artembatutin@gmail.com>
 */
public abstract class SpaceNode extends Polygon {
	
	/**
	 * Our main construction of {@link SpaceNode}.
	 * @param x the x layout coordinate.
	 * @param y the y layout coordinate.
	 * @param fill the background fill color.
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
	 * The updating pulse behavoir of this {@link SpaceNode}.
	 */
	public abstract void pulse();
	
}
