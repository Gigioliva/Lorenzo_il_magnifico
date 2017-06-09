package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.Move;

public abstract class Graphic {
	
	private VisitorB visitor;
	private RequestMove requestMove;
	
	public Graphic(VisitorB visitor, RequestMove requestMove){
		this.visitor=visitor;
		this.requestMove=requestMove;
	}
	
	public GenericAnswer getAnswer(GenericMessage arg){
		return arg.accept(visitor);
	}
	
	public Move getMove(){
		return requestMove.requestMove();
	}
	
	public abstract void printModel(Model model);
	
	/*public abstract String getChat();*/

}
