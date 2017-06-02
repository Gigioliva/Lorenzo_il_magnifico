package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.view.Visitor;

public class GenericMessage {
	private String ask;

	public String getString() {
		return ask;
	}
	
	public void setString(String str) {
		this.ask = str;
	}
	
	public GenericMessage accept(Visitor visitor){
		return null;
	}

}
