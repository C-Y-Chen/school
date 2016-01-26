package ccc;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JApplet;

public class Hw2Client extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 427994844310572990L;
	ShapeWorld shpWorld;

	/**
	 * Create the applet.
	 */
	public Hw2Client() {

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		// super.init();
		Rectangle wnd_dim = new Rectangle(getSize()); // get the dimension of the applet
		shpWorld = new ShapeWorld();
		shpWorld.buildWorld(10, wnd_dim); // build the shape world
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}


	@Override
	public void paint(Graphics g) {
		int n = shpWorld.getShapeList().size();
		for (int i = 0; i < n; i++) { // draw or fill each shape of the world
			boolean fill_flag = false;
			MyShape s = shpWorld.getShapeList().get(i);
			if (s.isCollided()) { // set the color and drawing mode depending on the collision status
				g.setColor(Color.RED);
				fill_flag = true;
			} else
				g.setColor(Color.BLUE);
			if (fill_flag)
				s.draw(g);
			else
				s.fill(g);
		}
	}
}
