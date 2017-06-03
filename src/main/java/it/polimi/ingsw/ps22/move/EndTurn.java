package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Model;

public class EndTurn extends Move {

	private static final long serialVersionUID = 1L;

	public EndTurn(String username) {
		super(username);
	}

	@Override
	public void applyMove(Model model) {
		if(model.getWaitAnswer().size()!=0){
			model.nextPlayer();
			model.notifyModel();
		}else{
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
		}
	}
	
	

}
