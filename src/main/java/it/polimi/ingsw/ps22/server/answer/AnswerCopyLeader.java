package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
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
				for(ImmediateEffect el: leadChoice.getImmediateEffect()){
					lead.addImmediateEffect(el);
				}
				for(PermanentEffect el: leadChoice.getPermanentEffect()){
					lead.addPermanentEffect(el);
				}
				lead.playLeader(player);
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
