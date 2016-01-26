import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class RectangleWorld {
	
	private int n = 0;
	private static ArrayList<String> rect = new ArrayList<String>();
	private static ArrayList<String> rectArea = new ArrayList<String>();
	public static void main(String[] args) {
		int bigAreaIndex=0;
		String token[];
		
		//讀檔
		RectangleWorld rw = new RectangleWorld();
		rw.readFile("test.txt");
		
		//移除碰撞的矩形
		bigAreaIndex = rw.rectArea();
		rw.removeRect(bigAreaIndex);
		
		//排序矩形
		Collections.sort(rectArea);
		Collections.sort(rect);
		
		//輸出
		for(int i=rect.size()-1;i>=0;i--){
			token = rectArea.get(i).split(" ",2);
			if(token[1].equals(rect.get(i))){
				System.out.println(rect.get(i) + "A=" + token[0]);
			}
		}
		
	}// end main
	
	public void readFile(String args){
		String input;
		try {
			FileReader fr = new FileReader(args);
			BufferedReader br = new BufferedReader(fr);
			n = Integer.valueOf(br.readLine());
			
			while ((input=br.readLine()) != null) {
				rect.add(input);
			}
			br.close();
		} catch(IOException e){System.out.println(e);}
		
	}// end readFile
	
	public void removeRect(int index){
		
		Rectangle bigR = new Rectangle(rect.get(index));
		
		for(int i=0;i<rect.size();i++){
			if(i != index){
				Rectangle r = new Rectangle(rect.get(i));
				if(bigR.isIntersect(r)){
					rect.remove(i);
					rectArea.remove(i);
				}
			}
		}// end for
	}// end removeRect
	
	public int rectArea(){
		double bigArea=0;
		int index=0;
		for(int i=0;i<rect.size();i++){
			Rectangle r = new Rectangle(rect.get(i));
			rectArea.add(String.valueOf(r.area())+ " " + String.valueOf(rect.get(i)));
			if(r.area() > bigArea){
				bigArea = r.area();
				index = i;
			}
		}// end for
		return index;
	}// end rectArea
}// end RectangleWorld class

