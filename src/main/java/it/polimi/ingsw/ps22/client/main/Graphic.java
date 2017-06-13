package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public abstract class Graphic {
	
	private VisitorB visitor;
	private RequestMove requestMove;
	
	public Graphic(VisitorB visitor, RequestMove requestMove){
		this.visitor=visitor;
		this.requestMove=requestMove;
	}
	
	public void getAnswer(GenericMessage arg){
		arg.accept(visitor);
	}
	
	public void getMove(){
		requestMove.requestMove();
	}
	
	public abstract void printModel(Model model);
	
	/*public abstract String getChat();*/

}
