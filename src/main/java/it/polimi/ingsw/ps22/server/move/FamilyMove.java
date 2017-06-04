package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;

public abstract class FamilyMove extends Move{
	
	private static final long serialVersionUID = 1L;
	protected Color color;
	protected int space;
	protected int numServant;
	
	public FamilyMove(String username, Color color, int space, int numServant){
		super(username);
		this.color=color;
		this.space=space;
		this.numServant=numServant;
	}
	
	protected boolean canFamilyMove(Model model){
		return model.getCanFamilyMove();
	}

}
