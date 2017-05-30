package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.action.Action;
import it.polimi.ingsw.ps22.message.AskServant;
import it.polimi.ingsw.ps22.player.Player;

public class ExtraAction implements ImmediateEffect {
	private Action action;

	public ExtraAction(Action action) {
		this.action = action;
	}
	
	private void askForServant(){
		AskServant mex = new AskServant(this.action);
		mex.applyAsk();
	}

	@Override
	public void performEffect(Player player) {
		askForServant(); 
	}
	
	@Override
	public String toString() {
		return action.toString();
	}

}
