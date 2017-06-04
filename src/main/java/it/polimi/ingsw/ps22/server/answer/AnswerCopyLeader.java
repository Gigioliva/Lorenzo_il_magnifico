package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class AnswerCopyLeader extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String answer;

	public AnswerCopyLeader(int id, String answer) {
		super(id);
		this.answer = answer;
	}

	@Override
	public void applyAnswer(Model model) {
		AskCopyLeader ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskCopyLeader)el;
			}
		}
		if(ask!=null){
			Player player=ask.getPlayer();
			CardLeader lead=ask.getLeader();
			CardLeader leadChoice=null;
			for(CardLeader el: ask.getLeaders()){
				if(el.getName().equalsIgnoreCase(answer)){
					leadChoice=el;
					break;
				}
			}
			if(leadChoice!=null){
				lead.setActionEffect(leadChoice.getActionEffect());
				lead.setPermanentEffect(leadChoice.getPermanentEffect());
				lead.playLeader(player);
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
