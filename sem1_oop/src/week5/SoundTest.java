package week5;

import sun.audio.AudioPlayer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SoundTest {
	
	private final static String PATH = "./data/misc/soundtest.mp3";
	
	public static void main(String[] args) {
		
		/*File audioFile = new File(PATH);
		if(!audioFile.exists()) {
			System.out.println("File does not exist.");
			System.exit(0);
		}
		
		try {
			InputStream audioStream = new FileInputStream(audioFile);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(MyClazz.class.getResource("music.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch(FileNotFoundException e) {
			System.out.println("Could not read the audio file.");
			e.printStackTrace();
			System.exit(0);
		}*/
	}

}
