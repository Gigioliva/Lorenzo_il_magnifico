package it.polimi.ingsw.ps22.answer;

import it.polimi.ingsw.ps22.board.ChurchSpace;
import it.polimi.ingsw.ps22.message.AskExcomm;
import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.message.MessageAsk;
import it.polimi.ingsw.ps22.model.Model;

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
					return;
				}
				if(answer.equalsIgnoreCase("SI")){
					temp.excommunication(ask.getPlayer());
					return;
				}
			}
		}
		//applicato solo se tutto non va bene
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
	}

}
