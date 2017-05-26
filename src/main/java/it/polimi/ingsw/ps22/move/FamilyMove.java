package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.model.Color;

public abstract class FamilyMove extends Move{
	protected Color color;
	protected int space;
	protected int numServant;
	
	public FamilyMove(String username, Color color, int space, int numServant){
		super(username);
		this.color=color;
		this.space=space;
		this.numServant=numServant;
	}

}
