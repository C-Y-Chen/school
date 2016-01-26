package ccc;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.sound.sampled.Line;

public class XShape extends MyShape {
	
	Ellipse2D ellipse2d1;
	Ellipse2D ellipse2d2;
	Ellipse2D ellipse2d3;
	Ellipse2D ellipse2d4;
	Ellipse2D ellipse2d5;
	Rectangle rectangle;
	Area a1,a2,a3,a4,a5,a6;
	public XShape() {
		// design component
		ellipse2d1 = new Ellipse2D.Double(100,100,70,75);
		rectangle = new Rectangle(100+25, 100+60, 20, 32);
		ellipse2d2 = new Ellipse2D.Double(100+20,100+82,30,10);
		ellipse2d3 = new Ellipse2D.Double(100+20,100+76,30,10);
		ellipse2d4 = new Ellipse2D.Double(100+20,100+70,30,10);
		ellipse2d5 = new Ellipse2D.Double(100+25,100+77,20,20);
		//add component to area to form a shape
		a1 = new Area(ellipse2d1);
		a2 = new Area(rectangle);
		a3 = new Area(ellipse2d2);
		a4 = new Area(ellipse2d3);
		a5 = new Area(ellipse2d4);
		a6 = new Area(ellipse2d5);
	}
	
	public XShape(int x,int y){
		// design component
		ellipse2d1 = new Ellipse2D.Double(x,y,70,75);
		rectangle = new Rectangle(x+25, y+60, 20, 32);
		ellipse2d2 = new Ellipse2D.Double(x+20,y+82,30,10);
		ellipse2d3 = new Ellipse2D.Double(x+20,y+76,30,10);
		ellipse2d4 = new Ellipse2D.Double(x+20,y+70,30,10);
		ellipse2d5 = new Ellipse2D.Double(x+25,y+77,20,20);
		//add component to area to form a shape
		a1 = new Area(ellipse2d1);
		a2 = new Area(rectangle);
		a3 = new Area(ellipse2d2);
		a4 = new Area(ellipse2d3);
		a5 = new Area(ellipse2d4);
		a6 = new Area(ellipse2d5);
	}

	@Override
	public double calcArea() {
		// TODO 自動產生的方法 Stub
		a1.add(a2);
		a1.add(a3);
		a1.add(a4);
		a1.add(a5);
		a1.add(a6);
		Rectangle2D rectangle2d = a1.getBounds2D();
		return rectangle2d.getWidth()*rectangle2d.getHeight();
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		a1.add(a2);
		a1.add(a3);
		a1.add(a4);
		a1.add(a5);
		a1.add(a6);
		g2.draw(a1);
	}

	@Override
	public void fill(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		a1.add(a2);
		a1.add(a3);
		a1.add(a4);
		a1.add(a5);
		a1.add(a6);
		g2.fill(a1);
	}

	@Override
	public Shape getShape() {
		a1.add(a2);
		a1.add(a3);
		a1.add(a4);
		a1.add(a5);
		a1.add(a6);
		return a1;
	}

}
