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

/**
 * 
 * A {@link CouncilPrivilege} must exchanged with some {@link ResourceAbstract}. 
 * This class implements the answer of the client to the question aboout the 
 * resource to take in exchange of the council privilege
 *
 */
public class AnswerCouncilPrivilege extends GenericAnswer {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> answer;
	
	/**
	 * 
	 * @param id the id of the message
	 * @param answer an ArrayList representing the choices of the player about the reosurces
	 * to take
	 */
	public AnswerCouncilPrivilege(int id, ArrayList<Integer> answer){
		super(id);
		this.answer=answer;
	}

	/**
	 * It applies the answer by adding to the player the 
	 * resources he has chosen. Eventual bonus/malus and
	 * other permanent effects are taken into account
	 * @param model
	 */
	@Override
	public void applyAnswer(Model model) {
		AskCouncilPrivilege ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskCouncilPrivilege)el;
			}
		}
		if(ask!=null){
			for(int i=0; i<answer.size();i++){
				for(int j=i+1; j<answer.size();j++){
					if(answer.get(i)==answer.get(j) || (answer.get(i)<=0 && answer.get(i)>5)){
						GenericMessage mex=new GenericMessage();
						mex.setString("risposta errata");
						model.notifyMessage(mex);
						model.notifyAsk(ask);
						return;
					}
				}
			}
			if(answer.size()==ask.getNumChoice()){
				Player player=ask.getPlayer();
				HashMap<String, ResourceAbstract> temp=CouncilPrivilege.exchangeWithResources(answer);
				for(String type: temp.keySet()){
					player.getSpecificResource(type).addResource(temp.get(type));
				}
				player.applyMalusResource(new ArrayList<String>(temp.keySet()));
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
