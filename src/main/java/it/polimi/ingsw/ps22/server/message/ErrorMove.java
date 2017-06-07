package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.view.Visitor;

public class ErrorMove extends GenericMessage {

	private static final long serialVersionUID = 1L;

	public ErrorMove() {
		setString("Mossa errata. Riprova");
	}
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}

}