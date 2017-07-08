package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class ProductionRightButton extends ActionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7868176120447214985L;
	ProductionLabel lab;

	public ProductionRightButton(int space, String username, Rectangle dim, ActionListener actionListener, ProductionLabel lab){
		super(space, username, dim, actionListener);
		lab.setBounds(dim.getInitx(), dim.getInity(), 500, 500);
		this.lab = lab;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
	
	@Override
	public void updateButton(Model model){
		ArrayList<Family> fam = model.getBoard().getProdZone().getProdSpaces()[1].getFamilies();
		this.familiars = fam;
		lab.updateProdLabel(fam);
	}
}
