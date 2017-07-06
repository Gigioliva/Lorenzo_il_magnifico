package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.action.CardAction;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * This class implements the answers to the question relative to
 * the choice of a card from a list.
 *
 */
public class AnswerCard extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	
	/**
	 * 
	 * @param id the id of the message
	 * @param type the type of the card
	 * @param name the name of the card
	 */
	public AnswerCard(int id, String type, String name){
		super(id);
		this.type=type;
		this.name=name;
	}

	/**
	 * It applies the answer of the player by applying the effects of the card taken
	 * and by adding the card to the cards of the corresponding {@link Player}.
	 * An error message is sent back if the answer was incorrect.
	 * @param model
	 */
	@Override
	public void applyAnswer(Model model){
		AskCard ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskCard)el;
			}
		}
		if(ask!=null){
			HashMap<String,ArrayList<DevelopmentCard>> possibleCard=ask.getPossibleCard();
			if(possibleCard.containsKey(type)){
				for(DevelopmentCard el: possibleCard.get(type)){
					if(el.getName().equals(name)){
						CardAction cardAction=ask.getCardAction();
						HashMap<String, DevelopmentCard> risp=new HashMap<String,DevelopmentCard>();
						risp.put(type, el);
						cardAction.applyAnswer(risp, ask.getPlayer(), model.getBoard());
						model.getWaitAnswer().remove(ask);
						model.notifyModel();
						return;
					}
				}
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
