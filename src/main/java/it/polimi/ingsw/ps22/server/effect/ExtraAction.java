package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.action.Action;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class ExtraAction implements ImmediateEffect {
	
	private static final long serialVersionUID = 1L;
	private Action action;

	public ExtraAction(Action action) {
		this.action = action;
	}
	
	@Override
	public ExtraAction clone(){
		ExtraAction temp=new ExtraAction(this.action.clone());
		return temp;
	}
	
	/**
	 * It starts the performing of the extra action by asking the player for the {@link Servant} to increase the action value
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	@Override
	public void performEffect(Player player, Model model) {
		AskServant mex = new AskServant(this.action);
		model.notifyAsk(mex);
	}
	
	@Override
	public String toString() {
		return action.toString();
	}

}
