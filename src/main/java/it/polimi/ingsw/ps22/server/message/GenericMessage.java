package it.polimi.ingsw.ps22.server.message;

import java.io.Serializable;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This class implements a generic string message to be sent to the client
 *
 */
public class GenericMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ask;

	/**
	 * 
	 * @return the string containing the content of the message
	 */
	public String getString() {
		return ask;
	}
	
	/**
	 * 
	 * @param str that sets the content of the message
	 */
	public void setString(String str) {
		this.ask = str;
	}
	
	/**
	 * This method is used to send the message to the right player
	 * @param visitor 
	 * @return this
	 */
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	/**
	 * This method is used to create the right message on client side 
	 * (the message to be shown to the player)
	 * @param visitor
	 */
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
