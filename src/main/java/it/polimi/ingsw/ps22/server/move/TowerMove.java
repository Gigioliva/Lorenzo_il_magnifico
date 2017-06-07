package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.model.Color;

public abstract class TowerMove extends FamilyMove {
	
	private static final long serialVersionUID = 1L;

	public TowerMove(String username,Color color, int floor, int numServant){
		super(username, color, floor, numServant);
	}

}