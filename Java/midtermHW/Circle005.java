package midtermHW;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Circle005 {
	Ellipse2D ellipse2d;
	
	public Circle005(){
		Random r = new Random();
		int x = r.nextInt(300);
		int y = r.nextInt(300);
		int w = r.nextInt(300);
		ellipse2d = new Ellipse2D.Double(x,y,w,w);
	}
	public Shape getshape(){
		return ellipse2d;
	}
}
