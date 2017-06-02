package it.polimi.ingsw.ps22.answer;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.effect.ExchangeResource;
import it.polimi.ingsw.ps22.message.AskEffect;
import it.polimi.ingsw.ps22.message.GenericMessage;
import it.polimi.ingsw.ps22.message.MessageAsk;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class AnswerEffect extends GenericAnswer {

	private ArrayList<Integer> answer;

	public AnswerEffect(int id, ArrayList<Integer> answer) {
		super(id);
		this.answer = answer;
	}

	@Override
	public void applyAnswer(Model model) {
		AskEffect ask = null;
		for (MessageAsk el : model.getWaitAnswer()) {
			if (el.getId() == id) {
				ask = (AskEffect) el;
			}
		}
		if (ask != null) {
			HashMap<DevelopmentCard, ArrayList<ActionEffect>> listEffect = ask.getListEffect();
			HashMap<DevelopmentCard, Integer> temp = new HashMap<DevelopmentCard, Integer>();
			HashMap<String, ResourceAbstract> cost = null;
			Player player=ask.getPlayer();
			if (answer.size() == listEffect.size()) {
				int i = 0;
				for (DevelopmentCard el : listEffect.keySet()) {
					if (answer.get(i) >= 0 && answer.get(i) <= listEffect.get(el).size()) {
						if (answer.get(i) != 0) {
							temp.put(el, answer.get(i) - 1);
						}
					} else {
						GenericMessage mex = new GenericMessage();
						mex.setString("risposta errata");
						model.notifyMessage(mex);
						return;
					}
					i++;
				}
				for (DevelopmentCard el : temp.keySet()) {
					HashMap<String, ResourceAbstract> cost_temp = ((ExchangeResource) listEffect.get(el)
							.get(temp.get(el))).getCost();
					cost=sumCost(cost,cost_temp);
				}
				for(String el: cost.keySet()){
					if(cost.get(el).getQuantity()>player.getSpecificResource(el).getQuantity()){
						GenericMessage mex = new GenericMessage();
						mex.setString("risposta errata");
						model.notifyMessage(mex);
						return;
					}
				}
				ask.getProdAction().applyAnswer(temp, player);
				return;
			}
		}
		// applicato solo se tutto non va bene
		GenericMessage mex = new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
	}

	private HashMap<String, ResourceAbstract> sumCost(HashMap<String, ResourceAbstract> cost1,
			HashMap<String, ResourceAbstract> cost2) {
		if(cost1==null && cost2==null){
			return null;
		}
		if (cost1 == null) {
			return cost2;
		}
		if(cost2==null){
			return cost1;
		}
		for (String el : cost2.keySet()) {
			if (cost1.containsKey(el)) {
				cost1.get(el).addResource(cost2.get(el));
			} else {
				cost1.put(el, cost2.get(el));
			}
		}
		return cost1;
	}

}
