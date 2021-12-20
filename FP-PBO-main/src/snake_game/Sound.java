package snake_game;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound 
{
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/snake_game/Level0.wav");
	}
		
	public void setFile() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[0]);
			clip = AudioSystem.getClip();
			clip.open(ais); 
		}catch (Exception e) {
			
		}
	}
		
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
		
}


