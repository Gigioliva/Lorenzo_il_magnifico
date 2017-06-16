package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;

public class FamiliarButton extends JButton {
	/**
	 * 
	 */
	private Color color;
	private java.awt.Color c;
	private static final long serialVersionUID = 8698619865338767712L;

	public FamiliarButton(Color color, java.awt.Color c, TakeFamiliarListener listener){
		super();
		this.setEnabled(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(true);
		//loadImage();
		this.c = c;
		
		this.addActionListener(listener);
	
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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(c);
		g.drawOval(0, 0, this.getWidth(), this.getHeight());
		g.fillOval(0, 0, this.getWidth(), this.getHeight());

	}
	
	
}
