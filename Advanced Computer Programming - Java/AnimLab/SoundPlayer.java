import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SoundPlayer extends SimpleSoundPlayer implements Runnable{
	
	public static void main(String[] args) {
		SoundPlayer player = new SoundPlayer("sounds/laser.wav");
		Thread playThread = new Thread(player);
		playThread.start();
		//InputStream stream = new ByteArrayInputStream(player.samples);
		//player.play(stream);
	}
	
	public SoundPlayer (String filename) {
		super(filename);
	}
	/* ¦h¤u
	 * 1. extends thread
	 * 2. implements runnable
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream stream = new ByteArrayInputStream(samples);
		play(stream);
	}
	
	
}
