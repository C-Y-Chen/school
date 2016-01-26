package midtermHW;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.awt.Window.Type;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;

public class midterm005 extends JApplet {
	private JTextField inputText;
	private boolean isTriangle = true;
	private boolean isCircle = false;
	private boolean isRectangle = false;
	private Color color = Color.RED;
	private ArrayList<Shape> shapes  = new ArrayList<>();
	/**
	 * Create the applet.
	 */
	
	public midterm005() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JCheckBox triangle = new JCheckBox("Triangle");
		triangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(triangle.isSelected()){
					isTriangle = true;
					shapes.clear();
				}
				else {
					isTriangle = false;
					shapes.clear();
				}
				repaint();
			}
		});
		triangle.setSelected(true);
		panel.add(triangle);
		
		JCheckBox rectangle = new JCheckBox("Rectangle");
		rectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rectangle.isSelected()){
					isRectangle = true;
					shapes.clear();
				}
				else {
					isRectangle = false;
					shapes.clear();
				}
				repaint();
			}
		});
		panel.add(rectangle);
		
		JCheckBox circle = new JCheckBox("Circle");
		circle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(circle.isSelected()){
					isCircle = true;
					shapes.clear();
				}
				else {
					isCircle = false;
					shapes.clear();
				}
				repaint();
			}
		});
		panel.add(circle);
		
		JButton draw = new JButton("draw");
		draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = Integer.parseInt(inputText.getText());
				if(n!=shapes.size() && isCircle|isTriangle|isRectangle)
					drawShape(n);
				repaint();
			}

			private void drawShape(int n) {
				// TODO 自動產生的方法 Stub
				Random r = new Random();
				Shape s = null;
				for(int i=0;i<n;i++){
					while(s == null){
						int type = r.nextInt(3);
						if(isTriangle && type == 0){
							Triangle005 triangle005 = new Triangle005();
							s = triangle005.getshape();
						}
						else if(isRectangle && type == 1){
							Rectangle005 Rectangle005 = new Rectangle005();
							s = Rectangle005.getshape();
							
						}
						else if(isCircle && type == 2){
							Circle005 circle005 = new Circle005();
							s = circle005.getshape();
						}
					}
					shapes.add(s);
					s = null;
				}
			}
		});
		panel.add(draw);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JRadioButton red = new JRadioButton("RED");
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(red.isSelected()){
					color = Color.RED;
					repaint();
				}
			}
		});
		red.setSelected(true);
		panel_1.add(red);
		
		JRadioButton green = new JRadioButton("GREEN");
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(green.isSelected()){
					color = Color.GREEN;
					repaint();
				}
			}
		});
		panel_1.add(green);
		
		JRadioButton blue = new JRadioButton("BLUE");
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(blue.isSelected()){
					color = Color.BLUE;
					repaint();
				}
			}
		});
		panel_1.add(blue);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(red);
		buttonGroup.add(green);
		buttonGroup.add(blue);
		JLabel lblNewLabel = new JLabel("Num of shape");
		panel_1.add(lblNewLabel);
		
		inputText = new JTextField();
		inputText.setText("1");
		panel_1.add(inputText);
		inputText.setColumns(10);

	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paint(g2);
		g2.setColor(color);
		for(Shape s:shapes){
			g2.draw(s);
		}
	}

}
