package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * This message signals the end of the draft
 *
 */
public class EndDraft extends GenericMessage {

	private static final long serialVersionUID = 1L;
	
	/**
	 * new end draft message
	 */
	public EndDraft(){
		super.setString("End Draft");
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}
	
	public EndDraft accept(VisitorA visitor){
		return visitor.visit(this);
	}

}
