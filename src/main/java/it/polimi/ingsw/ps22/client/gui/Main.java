package it.polimi.ingsw.ps22.client.gui;

import javax.swing.SwingUtilities;

public class Main {
	public static void mainGui(String[] args) {
	
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Gui b = new Gui();
				b.initGui();
			}
		});
	}
}
