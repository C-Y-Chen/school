import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Server extends JFrame implements Runnable{

	private JPanel contentPane;
	private ServerSocket serverSocket;
	private Thread thread;
	private Socket svSocket;
	private JTextArea displayArea;
	private DataOutputStream out;
	private DataInputStream in;
	private BufferedInputStream bufin;
	private JTextArea textbox;
	private JButton connectbtn;
	private boolean isstarted = false;
	private JTextField iptext;
	private JTextField porttext;
	private JTextField usertext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	
	public Server() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 380);
		setTitle("Server");
		setName("Server");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);			//disable resize frame
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{27, 109, 0, 67, 0, 226, 0, 0};
		gbl_contentPane.rowHeights = new int[]{17, 212, 40, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Semilight", Font.BOLD, 21));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weighty = 1.0;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		iptext = new JTextField();
		iptext.setHorizontalAlignment(SwingConstants.CENTER);
		iptext.setFont(new Font("新細明體", Font.BOLD, 17));
		iptext.setText("127.0.0.1");
		GridBagConstraints gbc_iptext = new GridBagConstraints();
		gbc_iptext.insets = new Insets(0, 0, 5, 5);
		gbc_iptext.fill = GridBagConstraints.HORIZONTAL;
		gbc_iptext.gridx = 1;
		gbc_iptext.gridy = 0;
		contentPane.add(iptext, gbc_iptext);
		iptext.setText(String.valueOf(InetAddress.getLocalHost()).substring(String.valueOf(InetAddress.getLocalHost()).indexOf("/")+1));
		iptext.setEditable(false);
		iptext.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("port");
		lblNewLabel_1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 21));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		porttext = new JTextField();
		porttext.setHorizontalAlignment(SwingConstants.CENTER);
		porttext.setFont(new Font("新細明體", Font.BOLD, 17));
		porttext.setText("1234");
		GridBagConstraints gbc_porttext = new GridBagConstraints();
		gbc_porttext.insets = new Insets(0, 0, 5, 5);
		gbc_porttext.fill = GridBagConstraints.HORIZONTAL;
		gbc_porttext.gridx = 3;
		gbc_porttext.gridy = 0;
		contentPane.add(porttext, gbc_porttext);
		porttext.setColumns(10);
		
		connectbtn = new JButton("start");
		connectbtn.setFont(new Font("Segoe UI Semilight", Font.BOLD, 18));
		connectbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					start();
			}
		});
		
		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("新細明體", Font.BOLD, 18));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 4;
		gbc_lblName.gridy = 0;
		contentPane.add(lblName, gbc_lblName);
		
		usertext = new JTextField();
		usertext.setFont(new Font("Segoe UI Semilight", Font.BOLD, 17));
		usertext.setText("server");
		GridBagConstraints gbc_usertext = new GridBagConstraints();
		gbc_usertext.insets = new Insets(0, 0, 5, 5);
		gbc_usertext.fill = GridBagConstraints.HORIZONTAL;
		gbc_usertext.gridx = 5;
		gbc_usertext.gridy = 0;
		contentPane.add(usertext, gbc_usertext);
		usertext.setColumns(10);
		GridBagConstraints gbc_connectbtn = new GridBagConstraints();
		gbc_connectbtn.weighty = 1.0;
		gbc_connectbtn.fill = GridBagConstraints.BOTH;
		gbc_connectbtn.insets = new Insets(0, 0, 5, 0);
		gbc_connectbtn.gridx = 6;
		gbc_connectbtn.gridy = 0;
		contentPane.add(connectbtn, gbc_connectbtn);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 7;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{264, 4, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{29, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		displayArea = new JTextArea();
		displayArea.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		GridBagConstraints gbc_displayArea = new GridBagConstraints();
		
		displayArea.setLineWrap(true);  							// enable displayArea line-wrapping
		displayArea.setWrapStyleWord(true);
		
		DefaultCaret caret = (DefaultCaret) displayArea.getCaret();  //set scroll auto bottom
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		gbc_displayArea.fill = GridBagConstraints.BOTH;
		gbc_displayArea.gridheight = 3;
		gbc_displayArea.gridwidth = 11;
		gbc_displayArea.gridy = 0;
		gbc_displayArea.gridx = 0;
		panel_1.add(displayArea, gbc_displayArea);
		
		JScrollPane jp2 = new JScrollPane(displayArea);				//set scroll
		GridBagConstraints gbc_jp2 = new GridBagConstraints();
		gbc_jp2.gridheight = 3;
		gbc_jp2.gridy = 0;
		gbc_jp2.fill = GridBagConstraints.BOTH;
		gbc_jp2.gridwidth = 7;
		gbc_jp2.gridx = 0;
		panel_1.add(jp2, gbc_jp2);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.gridwidth = 6;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{199, 104, 0, 0};
		gbl_panel.rowHeights = new int[]{29, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		textbox = new JTextArea();
		textbox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// for Jtextarea enter newline problem
				textbox.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doNoing");
				switch (e.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					String str = textbox.getText().toString();
					send(str);
				break;
			}
			}
		});
		textbox.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
		
		textbox.setLineWrap(true); 							 // enable textbox line-wrapping
		textbox.setWrapStyleWord(true);
		GridBagConstraints gbc_textbox_1 = new GridBagConstraints();
		
		gbc_textbox_1.fill = GridBagConstraints.BOTH;
		gbc_textbox_1.gridwidth = 4;
		gbc_textbox_1.gridx = 0;
		gbc_textbox_1.gridy = 0;
		panel.add(textbox, gbc_textbox_1);
		
		JScrollPane jp = new JScrollPane(textbox);					//set scroll
		GridBagConstraints gbc_jp = new GridBagConstraints();
		gbc_jp.gridheight = 2;
		gbc_jp.gridy = 0;
		gbc_jp.fill = GridBagConstraints.BOTH;
		gbc_jp.gridwidth = 4;
		panel.add(jp, gbc_jp);
		
		JButton sendbtn = new JButton("send");
		sendbtn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case KeyEvent.VK_ENTER:
					String str = textbox.getText().toString();
					send(str);
				break;
			}
			}
		});
		sendbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textbox.getText().toString();
				send(str);
			}
		});
		sendbtn.setFont(new Font("KodchiangUPC", Font.BOLD, 23));
		GridBagConstraints gbc_sendbtn = new GridBagConstraints();
		gbc_sendbtn.gridheight = 2;
		gbc_sendbtn.weighty = 1.0;
		gbc_sendbtn.weightx = 1.0;
		gbc_sendbtn.fill = GridBagConstraints.BOTH;
		gbc_sendbtn.gridx = 6;
		gbc_sendbtn.gridy = 2;
		contentPane.add(sendbtn, gbc_sendbtn);
		/****/
		setVisible(true);
		/****/
	}
	/*
	 * Start the server thread
	 */
	public void start(){
		connectbtn.setEnabled(false);
		porttext.setEditable(false);
		if(isstarted ){
			displayArea.append("Server is already started"+"\n");
		}
		else{
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public void run(){
		while(true) {
			try {   
				serverSocket = new ServerSocket(1234);
				displayArea.append("Server started"+"\n");
				usertext.setEditable(false);
				while(true) {
					svSocket = serverSocket.accept(); 	//listen for a connection and accept it
					openStream(svSocket);				// open data input and output stream
			        out.writeUTF(usertext.getText() + " : Welcome" + "\n");	  	//write accept msg to client
			        out.flush(); 				//flush dataoutputstream
					thread = new Thread(new read());
					thread.start();
					isstarted = true;
				}
			   
		    }
			catch(IOException ie) {  
				//System.out.println("Acceptance Error: " + ie);  
		    }
		}
	}
	
	public void openStream(Socket s){
		try {
			bufin = new BufferedInputStream(s.getInputStream());
			in = new DataInputStream(bufin);
			out = new DataOutputStream(s.getOutputStream());
			System.out.println(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void send(String s){
		try {
				openStream(svSocket);			// open data input and output stream and buffer input stream
				out.writeUTF(usertext.getText() + " : " + s);//write msg to client side
				out.flush();
				displayArea.append(usertext.getText() + " : " + s+"\n");
				textbox.setText("");
				textbox.requestFocus();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/*
	 * read msg from client side
	 */
	public class read implements Runnable{

		@Override
		public void run() {
			
			try {
				while(true){
					openStream(svSocket);	// open data input and output stream and buffer input stream
					String s;
					s = in.readUTF();		// datainputstream read msg from client side
					displayArea.append(s+"\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
