package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.model.Color;

public class TowerMove extends FamilyMove {
	private String type;
	
	public TowerMove(String username,Color color, String type, int floor){
		super(username, color, floor);
		this.type=type;
	}

}
