package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.model.Model;

public class ExcommLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8198698155682435615L;
	ArrayList<java.awt.Color> players = new ArrayList<>();
	
	public ExcommLabel(double resizeFactor, Rectangle dim){
		super();
		//this.setOpaque(false);
		setMeasures(dim);
	}
	
	private void setMeasures(Rectangle dim){
		this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	}
	
	public void update(Model model){
		//model.getBoard().getChurch(1).get
		repaint();
			
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		MyImage.updateExcommSpaces(players, this, g);
	}
}
