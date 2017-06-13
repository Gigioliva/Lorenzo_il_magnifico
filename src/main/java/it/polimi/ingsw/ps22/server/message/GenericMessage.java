package it.polimi.ingsw.ps22.server.message;

import java.io.Serializable;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class GenericMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String ask;

	public String getString() {
		return ask;
	}
	
	public void setString(String str) {
		this.ask = str;
	}
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
