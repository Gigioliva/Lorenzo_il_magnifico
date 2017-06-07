package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.server.view.Visitor;

public class ChatMessage extends GenericMessage {
	
	private static final long serialVersionUID = 1L;

	public ChatMessage(String str) {
		setString(str);
	}
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}

}