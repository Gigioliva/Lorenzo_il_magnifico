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
		this.setOpaque(true);
		this.setContentAreaFilled(true);
		this.setBorderPainted(true);
		//loadImage();
		
		this.color = color;
		
	}
	
	private void loadImage(){
		ImageIcon img = MyImage.createImageIcon("./image/fam.jpg");
		this.setIcon(img);
		this.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
	
	public Color getColor(){
		return this.color;
	}
	
	
}
