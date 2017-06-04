package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.board.TowerVentureZone;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerCosts extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private int answer;
	
	public AnswerCosts(int id, int answer){
		super(id);
		this.answer=answer;
	}

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
				return;
			}
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
		
	}

}
