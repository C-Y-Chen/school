import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

public class Rectangle {
	
	private Point2D.Double p1 = new Point2D.Double();
	private Point2D.Double p2 = new Point2D.Double();
	private Point2D.Double p3 = new Point2D.Double();
	private Point2D.Double p4 = new Point2D.Double();
	
	public Rectangle(String rect){
		String token[];
		token = rect.split(" ");
		setP1(java.lang.Double.valueOf(token[0]),java.lang.Double.valueOf(token[1]));
		setP2(java.lang.Double.valueOf(token[2]),java.lang.Double.valueOf(token[3]));
		setP3(java.lang.Double.valueOf(token[4]),java.lang.Double.valueOf(token[5]));
		setP4(p1, p2, p3);
	}

	public void setP1(double x,double y) {
		this.p1.setLocation(x, y);
	}
	
	public void setP2(double x,double y) {
		this.p2.setLocation(x, y);		
	}

	public void setP3(double x,double y) {
		this.p3.setLocation(x, y);		
	}
	// 計算第四點
	public void setP4(Point2D.Double p1,Point2D.Double p2,Point2D.Double p3) {
		double x=0,y=0,dis1,dis2,dis3;
		
		dis1 = this.distance(this.p1, this.p2);
		dis2 = this.distance(this.p1, this.p3);
		dis3 = this.distance(this.p2, this.p3);
		
		//判斷哪個角為直角，並計算第四點的x和y
		if(Math.max(Math.max(dis1, dis2), dis3) == dis1) {
			x = (this.p1.getX()+this.p2.getX()+this.p3.getX())-2*this.p3.getX();
			y = (this.p1.getY()+this.p2.getY()+this.p3.getY())-2*this.p3.getY();
		}
		else if (Math.max(Math.max(dis1, dis2), dis3) == dis2) {
			x = (this.p1.getX()+this.p2.getX()+this.p3.getX())-2*this.p2.getX();
			y = (this.p1.getY()+this.p2.getY()+this.p3.getY())-2*this.p2.getY();
		}
		else if (Math.max(Math.max(dis1, dis2), dis3) == dis3) {
			x = (this.p1.getX()+this.p2.getX()+this.p3.getX())-2*this.p1.getX();
			y = (this.p1.getY()+this.p2.getY()+this.p3.getY())-2*this.p1.getY();
		}
		this.p4.setLocation(x, y);		
	}
	
	public Point2D.Double getP1() {
		return p1;		
	}
	
	public Point2D.Double getP2() {
		return p2;
	}

	public Point2D.Double getP3() {
		return p3;
	}
	
	public Point2D.Double getP4() {
		return p4;
	}
	
	//計算兩點距離
	public double distance(Point2D.Double p1,Point2D.Double p2){
		return p1.distance(p2); 
	}
	
	//計算矩形面積:為三頂點形成的三角形面積的兩倍
	public double area(){
		return 2*triangleArea(this.p1, this.p2, this.p3);
	}
	
	//計算三角形面積，海龍公式
	public double triangleArea(Point2D.Double p1,Point2D.Double p2,Point2D.Double p3){
		double l1,l2,l3,s;
		l1 = this.distance(p1, p2);
		l2 = this.distance(p1, p3);
		l3 = this.distance(p2, p3);
		s = (l1+l2+l3)/2;
		return Math.sqrt((s*(s-l1)*(s-l2)*(s-l3)));
	}
	
	//透過計算，點和矩形的四個頂點形成的四個三角形面積和，大於矩形原本面積，則點在矩形外，反之，則判斷點是否在矩形內
	public boolean checkPoint(Point2D.Double p){
		double a1,a2,a3,a4,totalArea;
		a1 = triangleArea(this.p1, this.p2, p);
		a2 = triangleArea(this.p2, this.p3, p);
		a3 = triangleArea(this.p3, this.p4, p);
		a4 = triangleArea(this.p4, this.p1, p);
		totalArea = a1+a2+a3+a4;
		
		if(totalArea <= this.area()){
			return true;
		}
		else {
			return false;
		}
		
	}
	
	//只要矩形A有一個點在矩形B內，則碰撞
	public boolean isIntersect(Rectangle r){
		if(this.checkPoint(r.getP1()) || this.checkPoint(r.getP2()) || this.checkPoint(r.getP3()) || this.checkPoint(r.getP4())) {
			return true;
		}
		else {
			return false;
		}
	}
}// end Rectangle class
	