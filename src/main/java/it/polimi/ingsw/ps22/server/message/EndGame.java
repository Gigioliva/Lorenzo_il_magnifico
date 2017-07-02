package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class EndGame extends GenericMessage {

	private static final long serialVersionUID = 1L;
	
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
