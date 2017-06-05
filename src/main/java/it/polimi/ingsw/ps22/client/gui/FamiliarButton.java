package it.polimi.ingsw.ps22.client.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;

public class FamiliarButton extends JButton {
	/**
	 * 
	 */
	private Color color;
	private static final long serialVersionUID = 8698619865338767712L;

	public FamiliarButton(Color color){
		super();
		this.setEnabled(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		loadImage();
		
		this.color = color;
		
	}
	
	private void loadImage(){
		ImageIcon img = LayeredPanel.createImageIcon("./image/fam.jpg");
		this.setIcon(img);
		this.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
	
	public Color getColor(){
		return this.color;
	}
}
