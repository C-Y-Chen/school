package midtermHW;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;

public class Rectangle005 {
	Rectangle rectangle;
	public Rectangle005(){
		Random r = new Random();
		int x = r.nextInt(300);
		int y = r.nextInt(300);
		int w = r.nextInt(300);
		int h = r.nextInt(300);
		rectangle = new Rectangle(x, y, w, h);
	}
	public Shape getshape(){
		return rectangle;
	}
}
