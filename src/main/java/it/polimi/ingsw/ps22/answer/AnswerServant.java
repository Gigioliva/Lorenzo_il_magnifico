package it.polimi.ingsw.ps22.answer;

import it.polimi.ingsw.ps22.message.AskServant;
import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.message.MessageAsk;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Player;

public class AnswerServant extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private int answer;
	
	public AnswerServant(int id, int answer){
		super(id);
		this.answer=answer;
	}

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
			if(answer>0 && answer<=player.getSpecificResource("Servant").getQuantity()){
				ask.getAction().applyAction(player, model.getBoard(), answer);
			}
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
		
	}

}
