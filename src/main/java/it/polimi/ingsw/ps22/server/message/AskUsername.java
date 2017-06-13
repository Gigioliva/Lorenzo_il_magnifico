package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;

public class AskUsername extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	public AskUsername(){
		super.setString("Who are you?");
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
