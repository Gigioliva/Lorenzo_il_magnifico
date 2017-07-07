package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This message will be sent at end of the
 * game to signal the winner
 *
 */
public class EndGame extends GenericMessage {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param username the username of the winner
	 */
	public EndGame(String username){
		super();
		setString("THE WINNER IS " + username);
	}
	
	public EndGame accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
