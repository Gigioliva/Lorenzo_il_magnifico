package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.board.TowerVentureZone;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;

/**
 * 
 * It may happen that a card has multiple costs that the player can afford.
 * This class implements the answer of the client that specifies the cost he
 * wants to pay
 *
 */
public class AnswerCosts extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private int answer;
	
	/**
	 * 
	 * @param id the id of the message
	 * @param answer an int representing the chosen cost
	 */
	public AnswerCosts(int id, int answer){
		super(id);
		this.answer=answer;
	}

	/**
	 * It applies the answer by applying the cost to the player. 
	 * Eventual discounts and other permanent effects are taken
	 * into account
	 * @param model
	 */
	@Override
	public void applyAnswer(Model model) {
		AskCosts ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskCosts)el;
			}
		}
		if(ask!=null){
			ArrayList<RequisiteCost> possibleCost=ask.getPossibleCost();
			answer--;
			if(answer>=0 && answer<possibleCost.size()){
				TowerVentureZone temp=(TowerVentureZone)model.getBoard().getTower("Venture");
				temp.payCard(answer, possibleCost, ask.getPlayer(), ask.getTowerSpace(), ask.getDiscount());
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
