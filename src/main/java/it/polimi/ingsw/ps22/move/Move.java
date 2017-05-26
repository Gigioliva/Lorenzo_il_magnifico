package it.polimi.ingsw.ps22.move;
import it.polimi.ingsw.ps22.model.Model;

public abstract class Move {
	protected String username;
	
	public Move(String username){
		this.username=username;
	}
	
	public void applyMove(Model model){
		
	}

}
