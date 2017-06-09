package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class TowerPanel extends ActionPanel {
	public TowerPanel(String name,Rectangle dim, ActionListener actionListener, int space){
		super(name,dim,actionListener,space);
		b.setEnabled(true);
		b.setBorderPainted(true);
	
	}
	
	public void setCard(String path){
		ImageIcon cardIcon = MyImage.createScaledImageIcon(path, dim);
		b.setIcon(cardIcon);
	}
}
