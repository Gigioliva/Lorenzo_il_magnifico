package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Model;

public class EndTurn extends Move {

	private static final long serialVersionUID = 1L;

	public EndTurn(String username) {
		super(username);
	}

	@Override
	public void applyMove(Model model) {
		if(model.getWaitAnswer().isEmpty()){
			do{
				model.nextPlayer();
			}while(model.getCurrentPlayer()!=null && !model.getCurrentPlayer().getConnected() && model.getIsActive());
			model.notifyModel();
		}else{
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
			model.notifyModel();
		}
	}
	
	

}
