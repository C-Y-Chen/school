
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Calculator extends JFrame {

	private JPanel contentPane;
	private String inputString="";			
	private boolean m = false; 						//false for basic mode
	private boolean isdot = false;					//false means it's legal to dot
	private String lastResult;						//result string
	private DecimalFormat df = new DecimalFormat("#0.###########");
	JLabel lbl_input,lbl_output;
	Timer tm = new Timer();
	//calculate in basic mode
	basicParser b;
	//calculate in advance mode
	advancedParser a;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		
		setTitle("410121005 \u9673\u667A\u80B2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {500};
		gbl_contentPane.rowHeights = new int[] {135, -122};
		gbl_contentPane.columnWeights = new double[]{1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panelTop = new JPanel();
		GridBagConstraints gbc_panelTop = new GridBagConstraints();
		gbc_panelTop.fill = GridBagConstraints.BOTH;
		gbc_panelTop.gridx = 0;
		gbc_panelTop.gridy = 0;
		contentPane.add(panelTop, gbc_panelTop);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[]{472, 0};
		gbl_panelTop.rowHeights = new int[]{48, 48, 0};
		gbl_panelTop.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelTop.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelTop.setLayout(gbl_panelTop);
		
		lbl_input = new JLabel("");
		lbl_input.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lbl_input.setBackground(new Color(255, 255, 255));
		lbl_input.setForeground(new Color(0, 0, 0));
		lbl_input.setOpaque(true);
		lbl_input.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lbl_input = new GridBagConstraints();
		gbc_lbl_input.weighty = 1.0;
		gbc_lbl_input.fill = GridBagConstraints.BOTH;
		gbc_lbl_input.gridx = 0;
		gbc_lbl_input.gridy = 0;
		panelTop.add(lbl_input, gbc_lbl_input);
		
		lbl_output = new JLabel("0");
		lbl_output.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lbl_output.setForeground(new Color(255, 255, 255));
		lbl_output.setBackground(new Color(51, 0, 51));
		lbl_output.setOpaque(true);
		lbl_output.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lbl_output = new GridBagConstraints();
		gbc_lbl_output.weighty = 1.0;
		gbc_lbl_output.fill = GridBagConstraints.BOTH;
		gbc_lbl_output.gridx = 0;
		gbc_lbl_output.gridy = 1;
		panelTop.add(lbl_output, gbc_lbl_output);
		
		JPanel panelBtm = new JPanel();
		panelBtm.setFocusable(true);
		GridBagConstraints gbc_panelBtm = new GridBagConstraints();
		gbc_panelBtm.fill = GridBagConstraints.BOTH;
		gbc_panelBtm.gridx = 0;
		gbc_panelBtm.gridy = 1;
		contentPane.add(panelBtm, gbc_panelBtm);
		GridBagLayout gbl_panelBtm = new GridBagLayout();
		gbl_panelBtm.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelBtm.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelBtm.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelBtm.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelBtm.setLayout(gbl_panelBtm);
		
		JButton seven = new JButton("7");
		seven.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		seven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "7";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_seven = new GridBagConstraints();
		gbc_seven.fill = GridBagConstraints.BOTH;
		gbc_seven.insets = new Insets(0, 0, 0, 0);
		gbc_seven.gridx = 0;
		gbc_seven.gridy = 0;
		panelBtm.add(seven, gbc_seven);
		
		JButton eight = new JButton("8");
		eight.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		eight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "8";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_eight = new GridBagConstraints();
		gbc_eight.fill = GridBagConstraints.BOTH;
		gbc_eight.insets = new Insets(0, 0, 0, 0);
		gbc_eight.gridx = 1;
		gbc_eight.gridy = 0;
		panelBtm.add(eight, gbc_eight);
		
		JButton nine = new JButton("9");
		nine.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		nine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					inputString = inputString + "9";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_nine = new GridBagConstraints();
		gbc_nine.fill = GridBagConstraints.BOTH;
		gbc_nine.insets = new Insets(0, 0, 0, 0);
		gbc_nine.gridx = 2;
		gbc_nine.gridy = 0;
		panelBtm.add(nine, gbc_nine);
		
		JButton div = new JButton("/");
		div.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		div.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!checkop(inputString)){					//check the last character of inputstring is not operator or dot
					inputString = inputString + "/";
					lbl_input.setText(inputString);
					isdot = false;							
				}
				else{
					delay();								//set error message for 1 second
				}
				
			}
		});
		GridBagConstraints gbc_div = new GridBagConstraints();
		gbc_div.fill = GridBagConstraints.BOTH;
		gbc_div.insets = new Insets(0, 0, 0, 0);
		gbc_div.gridx = 3;
		gbc_div.gridy = 0;
		panelBtm.add(div, gbc_div);
		
		JButton clear = new JButton("C");
		clear.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString =  "";						//clear the inputstring
					lbl_input.setText(inputString);
					lbl_output.setText("0");
			}
		});
		GridBagConstraints gbc_clear = new GridBagConstraints();
		gbc_clear.fill = GridBagConstraints.BOTH;
		gbc_clear.gridx = 4;
		gbc_clear.gridy = 0;
		panelBtm.add(clear, gbc_clear);
		
		JButton four = new JButton("4");
		four.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		four.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "4";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_four = new GridBagConstraints();
		gbc_four.fill = GridBagConstraints.BOTH;
		gbc_four.insets = new Insets(0, 0, 0, 0);
		gbc_four.gridx = 0;
		gbc_four.gridy = 1;
		panelBtm.add(four, gbc_four);
		
		JButton five = new JButton("5");
		five.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "5";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_five = new GridBagConstraints();
		gbc_five.fill = GridBagConstraints.BOTH;
		gbc_five.insets = new Insets(0, 0, 0, 0);
		gbc_five.gridx = 1;
		gbc_five.gridy = 1;
		panelBtm.add(five, gbc_five);
		
		JButton six = new JButton("6");
		six.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		six.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "6";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_six = new GridBagConstraints();
		gbc_six.fill = GridBagConstraints.BOTH;
		gbc_six.insets = new Insets(0, 0, 0, 0);
		gbc_six.gridx = 2;
		gbc_six.gridy = 1;
		panelBtm.add(six, gbc_six);
		
		JButton mul = new JButton("*");
		mul.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		mul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkop(inputString)){					//check the last character of inputstring is not operator or dot
					inputString = inputString + "*";
					lbl_input.setText(inputString);
					isdot = false;
				}
				else{
					delay();								//set error message for 1 second
				}
			}
		});
		GridBagConstraints gbc_mul = new GridBagConstraints();
		gbc_mul.fill = GridBagConstraints.BOTH;
		gbc_mul.insets = new Insets(0, 0, 0, 0);
		gbc_mul.gridx = 3;
		gbc_mul.gridy = 1;
		panelBtm.add(mul, gbc_mul);
		
		JButton equ = new JButton("=");
		equ.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		equ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					lbl_output.setText(lastResult); 		//display the result
			}
		});
		GridBagConstraints gbc_equ = new GridBagConstraints();
		gbc_equ.fill = GridBagConstraints.BOTH;
		gbc_equ.gridheight = 3;
		gbc_equ.gridx = 4;
		gbc_equ.gridy = 1;
		panelBtm.add(equ, gbc_equ);
		
		JButton one = new JButton("1");
		one.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "1";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_one = new GridBagConstraints();
		gbc_one.fill = GridBagConstraints.BOTH;
		gbc_one.insets = new Insets(0, 0, 0, 0);
		gbc_one.gridx = 0;
		gbc_one.gridy = 2;
		panelBtm.add(one, gbc_one);
		
		JButton two = new JButton("2");
		two.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputString = inputString + "2";
				lbl_input.setText(inputString);
				lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_two = new GridBagConstraints();
		gbc_two.fill = GridBagConstraints.BOTH;
		gbc_two.insets = new Insets(0, 0, 0, 0);
		gbc_two.gridx = 1;
		gbc_two.gridy = 2;
		panelBtm.add(two, gbc_two);
		
		JButton three = new JButton("3");
		three.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					inputString = inputString + "3";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_three = new GridBagConstraints();
		gbc_three.fill = GridBagConstraints.BOTH;
		gbc_three.insets = new Insets(0, 0, 0, 0);
		gbc_three.gridx = 2;
		gbc_three.gridy = 2;
		panelBtm.add(three, gbc_three);
		
		JButton sub = new JButton("-");
		sub.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!checkop(inputString)){					//check the last character of inputstring is not operator or dot
					inputString = inputString + "-";
					lbl_input.setText(inputString);
					isdot = false;
				}
				else{
					delay();								//set error message for 1 second
				}
			}
		});
		GridBagConstraints gbc_sub = new GridBagConstraints();
		gbc_sub.fill = GridBagConstraints.BOTH;
		gbc_sub.insets = new Insets(0, 0, 0, 0);
		gbc_sub.gridx = 3;
		gbc_sub.gridy = 2;
		panelBtm.add(sub, gbc_sub);
		
		JButton zero = new JButton("0");
		zero.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		zero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					inputString = inputString + "0";
					lbl_input.setText(inputString);
					lbl_output.setText(showResult(inputString,m)); //calculate and show result at output label
			}
		});
		GridBagConstraints gbc_zero = new GridBagConstraints();
		gbc_zero.fill = GridBagConstraints.BOTH;
		gbc_zero.gridwidth = 2;
		gbc_zero.insets = new Insets(0, 0, 0, 0);
		gbc_zero.gridx = 0;
		gbc_zero.gridy = 3;
		panelBtm.add(zero, gbc_zero);
		
		
		
		JButton dot = new JButton(".");
		dot.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		dot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// check inputstring length>0 && isdot is false && last character of inputstring is not operator or dot
				if(inputString.length() != 0 && !isdot && !checkop(inputString)){
					inputString = inputString + ".";
					lbl_input.setText(inputString);
					isdot = true;
				}
				else if(checkop(inputString)){
					delay();								//set error message for 1 second
				}
			}
		});
		GridBagConstraints gbc_dot = new GridBagConstraints();
		gbc_dot.fill = GridBagConstraints.BOTH;
		gbc_dot.insets = new Insets(0, 0, 0, 0);
		gbc_dot.gridx = 2;
		gbc_dot.gridy = 3;
		panelBtm.add(dot, gbc_dot);
		
		JButton add = new JButton("+");
		add.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkop(inputString)){					//check the last character of inputstring is not operator or dot
					inputString = inputString + "+";
					lbl_input.setText(inputString);
					isdot = false;
				}
				else{
					delay();								//set error message for 1 second
				}
			}
		});
		GridBagConstraints gbc_add = new GridBagConstraints();
		gbc_add.fill = GridBagConstraints.BOTH;
		gbc_add.insets = new Insets(0, 0, 0, 0);
		gbc_add.gridx = 3;
		gbc_add.gridy = 3;
		panelBtm.add(add, gbc_add);
		
		JButton mode = new JButton("Basic");
		mode.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		mode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(m){										
					m = false;
					if(inputString.length() !=0)
						lbl_output.setText(showResult(inputString,m));
					mode.setText("Basic");
				}
				else{
					m = true;
					if(inputString.length() !=0)
						lbl_output.setText(showResult(inputString,m));
					mode.setText("Advanced");
				}
			}
		});
		GridBagConstraints gbc_mode = new GridBagConstraints();
		gbc_mode.fill = GridBagConstraints.BOTH;
		gbc_mode.gridwidth = 3;
		gbc_mode.gridx = 0;
		gbc_mode.gridy = 4;
		panelBtm.add(mode, gbc_mode);
		
		ImageIcon icon = createImageIcon("backspace.png");		//set backspace button icon
		JButton bsp = new JButton(icon);
		bsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(inputString.length()!=0){
					inputString = inputString.substring(0, inputString.length()-1); 
					lbl_input.setText(inputString);
					if(!checkop(inputString)){
						lbl_output.setText(showResult(inputString, m));
					}
					else{
						lbl_output.setText(showBspResult(inputString, m));
					}
				}
			}
		});
		GridBagConstraints gbc_bsp = new GridBagConstraints();
		gbc_bsp.fill = GridBagConstraints.BOTH;
		gbc_bsp.gridwidth = 2;
		gbc_bsp.gridx = 3;
		gbc_bsp.gridy = 4;
		panelBtm.add(bsp, gbc_bsp);
		// set keyboard listener 
		panelBtm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '0':
					zero.doClick();
					break;
				case '1':
					one.doClick();
					break;
				case '2':
					two.doClick();
					break;
				case '3':
					three.doClick();
					break;
				case '4':
					four.doClick();
					break;
				case '5':
					five.doClick();
					break;
				case '6':
					six.doClick();
					break;
				case '7':
					seven.doClick();
					break;
				case '8':
					eight.doClick();
					break;
				case '9':
					nine.doClick();
					break;
				case '+':
					add.doClick();
					break;
				case '-':
					sub.doClick();
					break;
				case '*':
					mul.doClick();
					break;
				case '/':
					div.doClick();
					break;
				case KeyEvent.VK_ENTER:
					equ.doClick();
					break;
				case KeyEvent.VK_BACK_SPACE:
					bsp.doClick();
					break;
				case KeyEvent.VK_PERIOD:
					dot.doClick();
					break;
				case KeyEvent.VK_ESCAPE:
					clear.doClick();
					break;
				case KeyEvent.VK_SPACE:
					mode.doClick();
					break;
				}
			}
		});
		
		
	}
	
	public void delay(){
		lbl_output.setText("快來救人!非禮阿");		//the input is illegal 
		tm.schedule(new TimerTask() {			//set timer for displaying 1 second
			@Override
			public void run() {
				lbl_output.setText(lastResult); //display result
			}
		}, 1000);
	}
	
	//if the last character of str is +|-|*|/|. return true 
	public boolean checkop(String str){
		char c ;
		if(str.length() == 0){
			return true;
		}
		else {
			c =  str.charAt(str.length()-1);
		}
		if(c == '+' || c == '-' || c == '*' || c == '/' || c == '.'){
			return true;
		}
		else {
			return false;
		}
	}
	
	//Avoid  the situation like "1+2+" or "1-2."
	public String showBspResult(String str,boolean m){
		String temp;
		if(!m && str.length()!=0){
			b = new basicParser();
			temp = String.valueOf(df.format(b.calculate(str.substring(0,str.length()-1))));
			System.out.println(temp);
			if(temp != null){
				lastResult = temp;
			}
			return lastResult;
		}
		else if(m && str.length()!=0){
			a = new advancedParser();
			temp = String.valueOf(df.format(a.calculate(str.substring(0,str.length()-2))));
			System.out.println(temp);
			if(temp != null){
				lastResult = temp;
			}
			return lastResult;
		}
		else{
			return "0";
		}
	}
	
	//calculate for different mode
	public String showResult(String str,boolean m){
		String temp;
		if(!m){
			b = new basicParser();
			temp = String.valueOf(df.format(b.calculate(str)));
			if(temp != null){
				lastResult = temp;
			}
			return lastResult;
		}
		else {
			a = new advancedParser();
			temp = String.valueOf(df.format(a.calculate(str)));
			if(temp != null){
				lastResult = temp;
			}
			return lastResult;
		}
		
	}
	
	//set backspace icon
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

}
