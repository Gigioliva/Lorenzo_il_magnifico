package it.polimi.ingsw.ps22.answer;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.board.TowerVentureZone;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.message.AskCosts;
import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.message.MessageAsk;
import it.polimi.ingsw.ps22.model.Model;

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
				return;
			}
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
		
	}

}
