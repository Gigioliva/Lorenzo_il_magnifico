package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;

public class FamiliarButton extends JButton {
	/**
	 * 
	 */
	private Color color;
	private java.awt.Color c;
	private String username;
	private static final long serialVersionUID = 8698619865338767712L;

	public FamiliarButton(Color color, java.awt.Color c, TakeFamiliarListener listener, String username){
		super();
		this.setEnabled(true);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(true);
		//loadImage();
		this.c = c;
		
		this.addActionListener(listener);
	
		this.color = color;
		this.username = username;
		
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void updateFamiliar(Model model){
		if(model.getPlayers().get(username).getFamily(color).isPlaced()){
			this.setEnabled(false);
			this.setVisible(false);
		}
		else{
			this.setEnabled(true);
			this.setVisible(true);;
		}
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(this.isEnabled() == true){
			g.setColor(c);
			g.drawOval(0, 0, this.getWidth(), this.getHeight());
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
		}

	}
	
	
}
