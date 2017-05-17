package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.model.Color;

public abstract class FamilyMove extends Move{
	private Color color;
	private int space;
	
	public FamilyMove(String username, Color color, int space){
		super(username);
		this.color=color;
		this.space=space;
	}

}
