package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class AnswerFamily extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private Color color;
	
	public AnswerFamily(int id, Color color) {
		super(id);
		this.color=color;
	}
	
	public Color getColor(){
		return color;
	}

	@Override
	public void applyAnswer(Model model) {
		AskFamily ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskFamily)el;
			}
		}
		if(ask!=null){
			Player player=model.getCurrentPlayer();
			player.getAllFamily().get(color).setValue(0);
			model.getWaitAnswer().remove(ask);
			model.notifyModel();
			return;
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
	}

}
