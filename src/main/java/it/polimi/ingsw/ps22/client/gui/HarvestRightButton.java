package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class HarvestRightButton  extends ActionButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1719693492586993421L;
	HarvestLabel lab;

	public HarvestRightButton(int space, String username, Rectangle dim, ActionListener actionListener, HarvestLabel lab){
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
		lab.updateHarvLabel(fam);
	}

}
