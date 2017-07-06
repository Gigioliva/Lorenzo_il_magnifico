package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Model;
/**
 * move that permit to notify to the server the end of the turn if the player which send it
 */
public class EndTurn extends Move {

	private static final long serialVersionUID = 1L;

	/**
	 * @param username is the username which want to end his turn 
	 */
	public EndTurn(String username) {
		super(username);
	}
	
	/**
	 * Permit to go to the next player turn
	 * 
	 * @param model
	 *            is the model in which apply the move
	 */
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
