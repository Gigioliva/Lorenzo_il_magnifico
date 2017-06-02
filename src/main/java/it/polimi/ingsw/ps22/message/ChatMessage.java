package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.view.Visitor;

public class ChatMessage extends GenericMessage {
	
	public ChatMessage(String str) {
		setString(str);
	}
	
	public GenericMessage accept(Visitor visitor){
		return visitor.visit(this);
	}

}
