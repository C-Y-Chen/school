package midtermHW;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.Random;

public class Triangle005 {
	private int xp,yp;
	Polygon polygon;
	public Triangle005(){
		Random r = new Random();
		int[] x = {r.nextInt(300),r.nextInt(300),r.nextInt(300)};
		int[] y = {r.nextInt(300),r.nextInt(300),r.nextInt(300)};
		polygon =  new Polygon(x,y,3);
	}
	public Shape getshape(){
		return polygon;
	}
}
