package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskLeader;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class AnswerLeader extends GenericAnswer {
	
	private String answer;
	private static final long serialVersionUID = 1L;

	public AnswerLeader(int id, String name) {
		super(id);
		this.answer=name;
	}

	@Override
	public void applyAnswer(Model model) {
		AskLeader ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskLeader)el;
			}
		}
		if(ask!=null){
			Player player=ask.getPlayer();
			ArrayList<CardLeader> temp=model.getCardLeaderStart(player);
			CardLeader leader=null;
			for(CardLeader el: temp){
				if(el.getName().equalsIgnoreCase(answer)){
					leader=el;
				}
			}
			if(leader!=null){
				player.addLeader(leader);
				temp.remove(leader);
				model.getWaitAnswer().remove(ask);
				model.draftStart();
				return;
			}
			model.notifyAsk(ask);
			return;
		}
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
		
	}

}
