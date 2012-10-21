package main.utilities;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.log4j.Logger;

public class Sound  {
	
	private static Logger log = Logger.getLogger(Sound.class);
	String sound_file;
	private boolean soundState;
	private static Sound soundInstance;

	public boolean getSoundState() {
		return soundState;
	}

	public void setSoundState(boolean soundState) {
		this.soundState = soundState;
	}
	
	public static Sound getInstance(){
		log.info("Getting sound instance");
		if (soundInstance==null){
			soundInstance = new Sound();
		}
		return soundInstance;
	}

	public void playSound() {
//		if (GameBoard.getAudioFile()==null)
//			sound_file="Ball_Bounce_1.wav";
//		else 
//			sound_file=GameBoard.getAudioFile();
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();			
			AudioInputStream inputStream;
			log.debug("soundpath="+getSound_file());
			inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(getSound_file()));
			clip.open(inputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		clip.start(); 
	}

	public void setSound_file(String sound_file) {
		this.sound_file = sound_file;
	}

	public String getSound_file() {
		return sound_file;
	}
}
