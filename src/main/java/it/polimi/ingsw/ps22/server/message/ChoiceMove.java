package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * this message signals the beginning of the turn for a player
 *
 */
public class ChoiceMove extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	/**
	 * new choice move
	 */
	public ChoiceMove() {
		setString("it's your turn");
	}
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
