

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Demo410121005 extends JFrame implements ActionListener {

	private JPanel contentPane;
	private int Width = 1024;
	private int Height = 768;
	private int noMonster = 10;
	private ArrayList<Sprite> monsters = new ArrayList<>();
	private ArrayList<Sprite> bullets = new ArrayList<>();
	private Sprite bullet;
	private Sprite ship;
	public Timer tm = new Timer(1, this); // 1 means millisecond
	private long lastTime;
	private Image backBuffer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Demo410121005 frame = new Demo410121005();
					frame.setVisible(true);

					// 先崋在backBuffer上 在畫出來 避免閃爍問題
					frame.backBuffer = frame.contentPane.createImage(frame.contentPane.getWidth(),frame.contentPane.getHeight());

					frame.lastTime = System.currentTimeMillis(); // record the frame start time
					frame.tm.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Demo410121005() {
		SoundPlayer player = new SoundPlayer("sounds/bgmusic.mid");
	    Thread playThread = new Thread(player);
	    playThread.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Width+50, Height+100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		int[] dur = {50};
		int[] dur2 = {50,50,50,50,50,50,50,50
						,50,50,50,50,50,50,50,50};
		Random random = new Random();
		ship = createSprite("spaceship", 1, dur);
		ship.setX(Width/2);
		ship.setY(Height);
		ship.setVelocityX(0.0F); // set ship speed
		ship.setVelocityY(0.0F);
		
		bullet = createSprite("bullet", 1, dur);

		for(int i =1;i<noMonster;i++) {
			// create monsters
			for(int k=1;k<=10;k++){
				Sprite sprite = createSprite("SkeltonFrame", 16, dur2);
				sprite.setDrawnHeight(81);
				sprite.setDrawnWidth(60);
	//			int x = random.nextInt(Width-sprite.getDrawnWidth());
	//			int y = random.nextInt(Height-sprite.getDrawnHeight());
				
				sprite.setX(100+k*70); // set location x
				sprite.setY(730-i*70); // set location y
	//			float vx = 0.5F - random.nextFloat(); // F means float , or the default is double
	//			float vy = 0.5F -random.nextFloat();
				sprite.setVelocityX(0.2F);
				//sprite.setVelocityY(vy);
				monsters.add(sprite);
			}
		}

			addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
			       super.mousePressed(e);
			       SoundPlayer player = new SoundPlayer("sounds/laser.wav");
			       Thread playThread = new Thread(player);
			       playThread.start();
			       newBullet();
			}
		});



		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode() == 37) {
					ship.setVelocityX(-0.25F);
				}
				else if(e.getKeyCode() == 39) {
					ship.setVelocityX(0.25F);
				}
				else if(e.getKeyCode() == 38) {
					ship.setVelocityY(0.0F);
				}

			}

		});

	}

	/*
	 *  prefix = image name
	 *  nFrame = numb of image
	 *  durs = duration time of each image
	 *  every
	 */
	public void newBullet(){
		int[] dur={50};
		Sprite sprite = createSprite("bullet", 1, dur);
		sprite.setX(ship.getX()+35);
		sprite.setY(ship.getY());
		sprite.setVelocityY(-0.3F);
		bullets.add(sprite);
	}
	
	public Sprite createSprite(String prefix, int nFrames, int[] durs) {
		/*
		 * each sprite means a monster ,a ship....
		 */
		Animation anim = new Animation();
		//Sprite s = new Sprite(anim);
		for(int i=0;i<nFrames;i++) {
			
			ImageIcon imageIcon = new ImageIcon(getClass().getResource("images/" + prefix + i + ".png"));
			anim.addFrame(imageIcon.getImage(), durs[i]);
		}

		return new Sprite(anim);
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		updateAndDraw();

	}

	private void updateAndDraw() {
		long currTime = System.currentTimeMillis();
		long elapsedTime = currTime - lastTime;
		int[] dur = {50};
		// update ship and each monster
		ship.update(elapsedTime);
		if(ship.getX() <= 0) {
			ship.setVelocityX(-ship.getVelocityX());
			ship.setX(0);
		}
		if (ship.getX() >= Width) {
			ship.setVelocityX(-ship.getVelocityX());
			ship.setX(Width);
		}
		
		for(Sprite s3:bullets) {
			
			s3.update(elapsedTime);
			if(s3.getX() <= 0) {
				s3.setVelocityX(-bullet.getVelocityX());
				s3.setX(0);
			}
			if (s3.getX() >= Width) {
				s3.setVelocityX(-bullet.getVelocityX());
				s3.setX(Width);
			}
		}

		for(Sprite s:monsters) {
			s.update(elapsedTime);
			if(s.getX() <= 0) {
				s.setVelocityX(-s.getVelocityX());
				s.setX(0);
			}
			if (s.getX() >= Width) {
				s.setVelocityX(-s.getVelocityX());
				s.setX(Width);
			}
			if(s.getY() <= 0) {
				s.setVelocityY(-s.getVelocityY());
				s.setY(0);
			}
			if (s.getY() > Height) {
				s.setVelocityY(-s.getVelocityY());
				s.setY(Height);
			}
			//s.update(elapsedTime);
		}

		Graphics g = contentPane.getGraphics(); // draw on contentPane not Jframe
		draw(g);
		g.dispose(); // release getGraphics
		lastTime = currTime;

	}

	private void draw(Graphics g) {
		// TODO Auto-generated method stub
		
		Graphics2D g2d = (Graphics2D) backBuffer.getGraphics();
		
		// draw on backBuffer first
		g2d.clearRect(0, 0, contentPane.getWidth(), contentPane.getHeight());
		
		g2d.drawImage(ship.getImage(),(int)ship.getX(),(int)ship.getY(),this);
		
		for(Sprite s2:bullets) 
			g2d.drawImage(s2.getImage(),(int)s2.getX(),(int)s2.getY(), this);
		
		
		for(int i=0;i<monsters.size();i++) {
			Sprite s = monsters.get(i);
			AffineTransform aft = new AffineTransform();
			aft.setToIdentity();
			aft.translate(s.getX(), s.getY());
			if(s.getVelocityX() < 0){
				aft.scale(-1.0, 1.0);
				aft.translate(-s.getDrawnWidth(),0);
			}
			for(int j=0;j<bullets.size();j++){
				Sprite s2 = bullets.get(j);
				if(s.getRect(60, 81).intersects(s2.getRect(15, 15))){
					monsters.remove(i);
					bullets.remove(j);
				}
			}
			g2d.drawImage(s.getImage(),aft,this);
		}
		
		g.drawImage(backBuffer,0,0,this);
	}
	
}
