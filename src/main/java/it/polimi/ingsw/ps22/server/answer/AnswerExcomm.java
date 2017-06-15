package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.board.ChurchSpace;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerExcomm extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private String answer;
	
	public AnswerExcomm(int id, String answer){
		super(id);
		this.answer=answer;
	}

	@Override
	public void applyAnswer(Model model) {
		AskExcomm ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskExcomm)el;
			}
		}
		if(ask!=null){
			int turn=model.getTurn();
			ChurchSpace temp=model.getBoard().getChurch(turn-1);
			if(temp!=null){
				if(answer.equalsIgnoreCase("SI")){
					temp.notExcommunication(ask.getPlayer());
					model.getWaitAnswer().remove(ask);
					model.notifyModel();
					return;
				}
				if(answer.equalsIgnoreCase("NO")){
					temp.excommunication(ask.getPlayer());
					model.getWaitAnswer().remove(ask);
					model.notifyModel();
					return;
				}
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
