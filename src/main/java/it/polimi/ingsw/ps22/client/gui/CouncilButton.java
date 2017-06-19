package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class CouncilButton extends ActionButton {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2561755498667221212L;
	CouncilLabel lab;
	

	public CouncilButton(int space, String username, Rectangle dim, ActionListener actionListener, CouncilLabel lab){
		super(space, username, dim, actionListener);
		this.lab = lab;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
	
	@Override
	public void updateButton(Model model){
		ArrayList<Family> fam = model.getBoard().getCouncilPalace().getFamilies();
		this.familiars = fam;
		lab.updateCouncilLabel(fam);
	}
}