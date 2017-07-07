package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This message is used for communication between players
 *
 */
public class ChatMessage extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param str a string representing the content of the message
	 */
	public ChatMessage(String str) {
		setString(str);
	}
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
