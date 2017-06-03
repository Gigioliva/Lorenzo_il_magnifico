package it.polimi.ingsw.ps22.move;

import java.io.Serializable;
import it.polimi.ingsw.ps22.model.Model;

public abstract class Move implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected String username;
	
	public Move(String username){
		this.username=username;
	}
	
	public void applyMove(Model model){
		
	}

}
