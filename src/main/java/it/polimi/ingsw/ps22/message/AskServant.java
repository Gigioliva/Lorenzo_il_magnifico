package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.action.Action;
import it.polimi.ingsw.ps22.view.Visitor;

public class AskServant extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private transient Action Action;
	
	public AskServant(Action Action){
		this.Action=Action;
		setString("Quanti servitori vuoi spendere? ");
	}
	
	public AskServant(String str, int id){
		super(str,id);
	}
	
	public Action getAction(){
		return Action;
	}
	
	public void applyAsk(){
		model.notifyAsk(this);
	}
	
	public AskServant accept(Visitor visitor){
		return visitor.visit(this);
	}

}
