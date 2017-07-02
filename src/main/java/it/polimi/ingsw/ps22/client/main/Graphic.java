package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public abstract class Graphic {
	
	protected VisitorB visitor;
	protected RequestMove requestMove;
	
	public Graphic(VisitorB visitor, RequestMove requestMove){
		this.visitor=visitor;
		this.requestMove=requestMove;
	}
	
	public Graphic(){
		
	}
	
	public void getAnswer(GenericMessage arg){
		arg.accept(visitor);
	}
	
	public void getMove(){
		requestMove.requestMove();
	}
	
	public abstract void printModel(Model model);
}
