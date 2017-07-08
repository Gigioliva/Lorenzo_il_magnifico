package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;

/**
 * 
 * This message is used to ask the player the username
 *
 */
public class AskUsername extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	/**
	 * creates a new ask username message
	 */
	public AskUsername(){
		super.setString("Who are you?");
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
}
