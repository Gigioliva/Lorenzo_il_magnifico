package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

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
