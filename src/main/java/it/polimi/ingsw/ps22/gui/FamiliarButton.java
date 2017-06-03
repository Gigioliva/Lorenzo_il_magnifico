package it.polimi.ingsw.ps22.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FamiliarButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698619865338767712L;

	public FamiliarButton(){
		super();
		this.setEnabled(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		loadImage();
		
	}
	
	private void loadImage(){
		ImageIcon img = LayeredPanel.createImageIcon("fam.jpg");
		this.setIcon(img);
		this.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
