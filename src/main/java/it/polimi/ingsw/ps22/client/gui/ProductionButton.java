package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class ProductionButton extends ActionButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 974203561858330360L;

	public ProductionButton(int space, String username, Rectangle dim, ActionListener actionListener){
		super(space, username, dim, actionListener);
	}
	
	@Override
	public void updateButton(Model model){
		ArrayList<Family> fam = model.getBoard().getProdZone().getProdSpaces()[space].getFamilies();
		this.familiars = fam;
		repaint();
	}
}