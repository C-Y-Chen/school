import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleSoundPlayer {
	public AudioFormat aFormat;
	public byte[] samples;
	SourceDataLine sDataLine;
	
	public SimpleSoundPlayer(String filename) {
		AudioInputStream stream = null;
		try {
			stream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
			aFormat = stream.getFormat();
			samples = getSamples(stream);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SimpleSoundPlayer player = new SimpleSoundPlayer("sounds/bgmusic.mid");
		InputStream stream = new ByteArrayInputStream(player.samples);
		player.play(stream);
	}
	
	private byte[] getSamples(AudioInputStream audioStream) {
		int length = (int) (audioStream.getFrameLength()*aFormat.getFrameSize()); //frameSize = ? byte/each frame
		byte[] samples = new byte[length];
		DataInputStream is = new DataInputStream(audioStream); // 將audioStream 轉型成dataInputStream
		
		try {
			is.readFully(samples);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return samples;
	}
	
	// 一次讀一點&播一點
	public void play(InputStream source) {
		int bufferSize = (int) (aFormat.getFrameSize()*Math.round(aFormat.getFrameRate()/10.0)); //1/10 seconds 需要多少byte
		byte[] buffer = new byte[bufferSize];
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, aFormat);
		try {
			sDataLine = (SourceDataLine) AudioSystem.getLine(info);
			sDataLine.open(aFormat,bufferSize);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sDataLine.start();
		try {
			int numBytesRead = 0;
			while (numBytesRead != -1) {
				numBytesRead = source.read(buffer, 0, buffer.length); //從0開始讀，每次讀buffer.length 讀1/10sec的buffer
				if(numBytesRead != -1)
					sDataLine.write(buffer, 0, numBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//將sDataLine殘餘的資料送至喇叭
		sDataLine.drain();
		sDataLine.close();
	}
}
