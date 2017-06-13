package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class FaithTrackLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045902773102095037L;
	private Rectangle dim;
	ArrayList<Player> players;
	
	
	public FaithTrackLabel(double resizeFactor, int slot, ArrayList<Player> players){
		super();
		//this.setOpaque(false);
		this.dim = AdaptiveLayout.getFaithSlotSpace(resizeFactor, slot);
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
