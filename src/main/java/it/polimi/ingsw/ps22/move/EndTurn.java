package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.model.Model;

public class EndTurn extends Move {

	public EndTurn(String username) {
		super(username);
	}

	@Override
	public void applyMove(Model model) {
		model.nextPlayer();
		model.notifyModel();
	}
	
	

}
