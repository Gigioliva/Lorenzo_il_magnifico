package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.action.Action;
import it.polimi.ingsw.ps22.server.answer.AnswerServant;
import it.polimi.ingsw.ps22.server.effect.ExtraAction;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * Sometimes it's necessary to ask the player if he wants 
 * to add some servants to increase the action value. This is necessary,
 * for instance, when a player takes a card which has an {@link ExtraAction}
 * effect. 
 *
 */
public class AskServant extends MessageAsk {
	
	private static final long serialVersionUID = 1L;
	private Action Action;
	
	/**
	 * 
	 * @param Action the {@link Action} whose action value may be increased
	 * @param player the target player
	 */
	public AskServant(Action Action, Player player){
		super(player.getUsername());
		this.Action=Action;
		setString("Quanti servitori vuoi spendere? ");
	}
	
	public AskServant(String str, int id){
		super(str,id);
	}
	
	/**
	 * 
	 * @return the {@link Action} performed
	 */
	public Action getAction(){
		return Action;
	}
	
	@Override
	public void applyDefault(Model model) {
		AnswerServant ans = new AnswerServant(this.getId(), 0);
		ans.applyAnswer(model);
	}
	
	public AskServant accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
