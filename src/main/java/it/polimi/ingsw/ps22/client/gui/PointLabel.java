package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public abstract class PointLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8799499098340716847L;
	ArrayList<Player> players;
	static AdaptiveLayout layout = AdaptiveLayout.instance();
	
	
	public PointLabel(double resizeFactor, int slot, ArrayList<Player> players, Rectangle dim){
		super();
		//this.setOpaque(false);
		this.players = players;
		setMeasures(dim);
	}
	
	private void setMeasures(Rectangle dim){
		this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	}
	
	public void update(Model model){
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		MyImage.updatePlayersSpaces(players, this, g);
	}
	

}
