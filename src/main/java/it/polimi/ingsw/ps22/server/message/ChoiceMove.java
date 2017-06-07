package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class ChoiceMove extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	public ChoiceMove() {
		setString("it's your turn");
	}
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public GenericAnswer accept(VisitorB visitor){
		return visitor.visit(this);
	}

}
