package ccc;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import ccc.*;

/**
 * @author CCC
 *
 */
public class ShapeWorld {
	final static int WIDTH = 500;
	final static int HEIGHT = 500;
	ArrayList<MyShape> shpList = new ArrayList<>();

	public ShapeWorld() {
		// TODO Auto-generated constructor stub
	}

	/** 
	 * Create a world of shapes.
	 * @param n: number of shapes to be generated in the world 
	 * @param wnd_dim: dimension of the display window
	 */
	public void buildWorld(int n, Rectangle wnd_dim) {
		int n_shapes = 0;
		while (n_shapes < n) {
			int n_vertices = (int) (Math.random() * 1000) % 20 + 3;
			int x = (int) (WIDTH * Math.random()); 	// x-coordinate of shape center 
			int y = (int) (HEIGHT * Math.random()); // y-coordinate of shape center
			int type;// = (int) (Math.random() * 1000) % 3; // 0: ellipse, 1: star, others: external shape
			type = 3;
			MyShape s = null;
			if (type == 0) {
				int radius1 = (int) (WIDTH * 0.25 * Math.random()) + 20; // long axis of an ellipse
				int radius2 = (int) (WIDTH * 0.25 * Math.random()) + 20; // short axis of an ellipse
				s = new Ellipse(x, y, radius1, radius2);
			}
			else if (type == 1) {
				int radius = (int) (WIDTH * 0.25 * Math.random()) + 20;  // radius of a star
				s = new Star(x, y, n_vertices, radius);
			}
			else {
				s = new XShape(x,y); // call the constructor of your shape here
			}
			Rectangle r = s.getShape().getBounds(); // bounding box of the shape
			if (wnd_dim.contains(r)) { // test if it's inside the display window, and discard it if not
				shpList.add(s);
				n_shapes ++;
			}
		}
		testCollision(); // test collision between each pair of shapes
	}

	/** 
	 * Get the shape list of the world.
	 * @return: the list
	 */
	public ArrayList<MyShape> getShapeList() {
		return shpList;
	}

	/** 
	 * Do the collision test for each pair of shapes in the world.
	 * 
	 */
	public void testCollision() {
		for (int i = 0; i < shpList.size(); i++) {
			MyShape s1 = shpList.get(i);
			for (int j = i + 1; j < shpList.size(); j++) {
				MyShape s2 = shpList.get(j);
				if (s1.collide(s2)) { // check collision
					s1.setCollided(true);
					s2.setCollided(true);
				}
			}
		}
	}
}
