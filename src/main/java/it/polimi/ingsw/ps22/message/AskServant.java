package it.polimi.ingsw.ps22.message;

import it.polimi.ingsw.ps22.action.Action;

public class AskServant extends MessageAsk {
	private Action Action;
	
	public AskServant(Action Action){
		this.Action=Action;
		setString("Quanti servitori vuoi spendere? ");
	}
	
	public Action getExtraAction(){
		return Action;
	}
	
	public void applyAsk(){
		model.notifyAsk(this);
	}

}
