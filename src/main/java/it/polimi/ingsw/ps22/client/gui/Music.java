package it.polimi.ingsw.ps22.client.gui;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

@SuppressWarnings("restriction")
public class Music {
	
	private static MediaPlayer c;
	
	static void playMP3(String fileName) {
		(new javafx.embed.swing.JFXPanel()).getClass();
		String uriString1 = new File(fileName).toURI().toString();
		Media temp1=new Media(uriString1);
		c=new MediaPlayer(temp1);
		c.setAutoPlay(true);
		c.setCycleCount(MediaPlayer.INDEFINITE);
		c.play();
	}
	
	static void stopMP3(){
		c.stop();
		c=null;
	}
}
