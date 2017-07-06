package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.action.Action;
import it.polimi.ingsw.ps22.server.effect.ExtraAction;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * Sometimes, performing an {@link ExtraAction} effects, a player
 * may want to increment the action value by using {@link Servant}.
 * This class performs this answer
 *
 */
public class AnswerServant extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private int answer;
	
	/**
	 * 
	 * @param id the id of the message
	 * @param answer an int representing the quantity of
	 * {@link Servant} chosen by the player
	 */
	public AnswerServant(int id, int answer){
		super(id);
		this.answer=answer;
	}

	/**
	 * It applies the answer by performing the {@link Action} 
	 * which action value is increased by the number of {@link Servant}
	 * chosen bby the player
	 * @param model
	 */
	@Override
	public void applyAnswer(Model model) {
		AskServant ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskServant)el;
			}
		}
		if(ask!=null){
			Player player=model.getCurrentPlayer();
			if(answer>=0 && answer<=player.getSpecificResource("Servant").getQuantity()){
				ask.getAction().applyAction(player, model.getBoard(), answer, model);
				model.getWaitAnswer().remove(ask);
				model.notifyModel();
				return;
			}
			model.notifyAsk(ask);
			return;
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
		
	}

}
