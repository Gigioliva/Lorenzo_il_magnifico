package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.action.Action;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.player.Player;

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
