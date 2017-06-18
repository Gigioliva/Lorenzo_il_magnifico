package it.polimi.ingsw.ps22.client.gui;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

	static void playMP3(String fileName) {
	    new javafx.embed.swing.JFXPanel();
	    String uriString = new File(fileName).toURI().toString();
	    new MediaPlayer(new Media(uriString)).play();
	}
}
