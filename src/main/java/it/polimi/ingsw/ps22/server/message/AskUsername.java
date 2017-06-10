package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;

public class AskUsername extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	public AskUsername(){
		super.setString("Who are you?");
	}
	
	public GenericAnswer accept(VisitorB visitor){
		return visitor.visit(this);
	}

}
