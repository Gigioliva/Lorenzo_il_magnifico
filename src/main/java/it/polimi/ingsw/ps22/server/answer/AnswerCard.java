package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.action.CardAction;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerCard extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	
	public AnswerCard(int id, String type, String name){
		super(id);
		this.type=type;
		this.name=name;
	}

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
						return;
					}
				}
			}
			model.notifyAsk(ask);
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
	}

}
