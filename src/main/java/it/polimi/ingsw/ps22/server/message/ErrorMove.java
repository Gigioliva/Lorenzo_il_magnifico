package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * this message is used to signal an error to the client
 * (wrong move)
 *
 */
public class ErrorMove extends GenericMessage {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates an error move
	 */
	public ErrorMove() {
		setString("Mossa errata. Riprova");
	}
	
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
