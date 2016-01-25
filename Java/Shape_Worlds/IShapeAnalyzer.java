package ccc;
import java.awt.Graphics;

public interface IShapeAnalyzer extends Comparable<MyShape> {
	/** 
	 * Calculate the area of the shape object.
	 * @return: the calculated area
	 */
	double calcArea();
	/** 
	 * Test if the current shape object collides with another shape object.
	 * @param s2: the shape object to be checked for collision
	 * @return: true if collision
	 */
	boolean collide(MyShape s2);
	/** 
	 * Draw the frame of the shape object.
	 * @param g: the graphics object of the display window
	 */
	void draw(Graphics g);
	/** 
	 * Draw the filled the shape object
	 * @param g: the graphics object of the display window
	 */
	void fill(Graphics g);
}
