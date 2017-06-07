package it.polimi.ingsw.ps22.server.answer;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class AnswerCouncilPrivilege extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> answer;
	
	public AnswerCouncilPrivilege(int id, ArrayList<Integer> answer){
		super(id);
		this.answer=answer;
	}

	@Override
	public void applyAnswer(Model model) {
		AskCouncilPrivilege ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskCouncilPrivilege)el;
			}
		}
		for(int i=0; i<answer.size();i++){
			for(int j=i+1; j<answer.size();j++){
				if(answer.get(i)==answer.get(j)){
					GenericMessage mex=new GenericMessage();
					mex.setString("risposta errata");
					model.notifyMessage(mex);
					return;
				}
			}
		}
		if(ask!=null){
			if(answer.size()==ask.getNumChoice()){
				Player player=ask.getPlayer();
				HashMap<String, ResourceAbstract> temp=CouncilPrivilege.exchangeWithResources(answer);
				for(String type: temp.keySet()){
					player.getSpecificResource(type).addResource(temp.get(type));
				}
				player.applyMalusResource(new ArrayList<String>(temp.keySet()));
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