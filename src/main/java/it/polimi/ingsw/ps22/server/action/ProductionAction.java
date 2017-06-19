package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.ExchangeResource;
import it.polimi.ingsw.ps22.server.message.AskEffect;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class ProductionAction extends Action {

	private static final long serialVersionUID = 1L;

	public ProductionAction(int actionValue) {
		super(actionValue);
	}

	@Override
	public ProductionAction clone() {
		ProductionAction temp = new ProductionAction(this.getActionValue());
		temp.servants = this.servants;
		return temp;
	}

	private LinkedHashMap<DevelopmentCard, ArrayList<ActionEffect>> getPossibleEffects(Player player, int bonus,
			LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>> allEffects) {
		LinkedHashMap<DevelopmentCard, ArrayList<ActionEffect>> possibleEffects = new LinkedHashMap<DevelopmentCard, ArrayList<ActionEffect>>();
		ArrayList<DevelopmentCard> buildingCards = new ArrayList<DevelopmentCard>(allEffects.keySet());
		for (DevelopmentCard card : buildingCards) {
			LinkedHashMap<ActionEffect, Integer> mapEffects = allEffects.get(card);
			for (ActionEffect effect : mapEffects.keySet()) {
				if ((effect instanceof ExchangeResource)  && effect.canAffordCost(player) && card.getActionValue() <= bonus + super.getActionValue()) {
					if (possibleEffects.containsKey(card)) {
						possibleEffects.get(card).add(effect);
					} else {
						possibleEffects.put(card, new ArrayList<ActionEffect>());
						possibleEffects.get(card).add(effect);
					}
				}
			}
		}
		return possibleEffects;
	}

	private void applyNoExchangeEffect(Player player, int bonus) {
		ArrayList<DevelopmentCard> buildingCards = player.getDevelopmentCard("Building");
		for (DevelopmentCard card : buildingCards) {
			ArrayList<ActionEffect> effects = card.getActionEffects();
			for (int i = 0; i < effects.size(); i++) {
				if (!(effects.get(i) instanceof ExchangeResource)
						&& card.getActionValue() <= super.getActionValue() + bonus)
					effects.get(i).performEffect(player);
			}
		}
	}

	@Override
	public void applyAction(Player player, int servants) {
		LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>> allEffects;
		LinkedHashMap<DevelopmentCard, ArrayList<ActionEffect>> possibleEffects;
		Player clonedPlayer = player.clone(player.getUsername());
		this.servants = servants;
		int bonus = player.getBonusAcc().getBonus("IncrementProduction").getQuantity() + servants;
		player.getSpecificResource("Servant").subResource(new Servant(servants));
		allEffects = player.cloneCardswithActionEffect("Building");
		// do{
		possibleEffects = getPossibleEffects(clonedPlayer, bonus, allEffects);
		// HashMap<DevelopmentCard,Integer> chosenEffect = new
		// HashMap<DevelopmentCard,Integer>();
		// passa a utente lista di carte ed effetti possibili ad ogni carta
		// chosenEffect = askEffect...
		if (!possibleEffects.isEmpty()) {
			AskEffect mex = new AskEffect(possibleEffects, this, player);
			mex.applyAsk();
		} else{
			applyNoExchangeEffect(player, bonus);
			if (1 <= super.getActionValue() + bonus) {
				player.getPersonalBoard().applyPersonalBoardBonus("Production", player);
			}
		}
		// DevelopmentCard card = chosenEffect.keySet().iterator().next();
		// card.applyActionEffect(player, board, chosenEffect.get(card));
		// allEffects.remove(card);

		// }while(!possibleEffects.isEmpty());

		// applyNoExchangeEffect(player, board, bonus);
		// player.getPersonalBoard().applyPersonalBoardBonus("Production",
		// player, board);
	}

	public void applyAnswer(HashMap<DevelopmentCard, Integer> chosenEffects, Player player) {
		for (DevelopmentCard card : chosenEffects.keySet()) {
			Integer chosenEffect = chosenEffects.get(card);
			card.applyActionEffect(player, chosenEffect);
		}
		int bonus = player.getBonusAcc().getBonus("IncrementProduction").getQuantity() + servants;
		applyNoExchangeEffect(player, bonus);
		if (1 <= super.getActionValue() + bonus) {
			player.getPersonalBoard().applyPersonalBoardBonus("Production", player);
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Production Action with action value: " + super.getActionValue() + "\n");
		return str.toString();
	}

}
