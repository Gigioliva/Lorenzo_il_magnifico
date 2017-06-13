package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class CouncilButton extends ActionButton {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2561755498667221212L;

	public CouncilButton(int space, String username, Rectangle dim, ActionListener actionListener){
		super(space, username, dim, actionListener);
	}
	
	@Override
	public void updateButton(Model model){
		ArrayList<Family> fam = model.getBoard().getCouncilPalace().getFamilies();
		this.familiars = fam;
		repaint();
	}
}