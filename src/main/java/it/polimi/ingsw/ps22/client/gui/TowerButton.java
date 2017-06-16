package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public class TowerButton extends ActionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4842063321360444632L;
	String tower;
	
	public TowerButton(int space, String username,Rectangle dim,  ActionListener actionListener, String tower){
		super(space, username, dim, actionListener);
		this.tower = tower;
	}
	
	@Override
	public void updateButton(Model model){
		if(tower!=null){
			ArrayList<Family> familiars = model.getBoard().getTower(tower).getTowerSpaces()[space].getFamilies();
			this.familiars = familiars;
			repaint();
		}
	}

}
