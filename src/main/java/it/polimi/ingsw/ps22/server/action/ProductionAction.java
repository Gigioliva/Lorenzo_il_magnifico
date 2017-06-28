package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.ExchangeResource;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.message.AskEffect;
import it.polimi.ingsw.ps22.server.model.Model;
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

	private void applyNoExchangeEffect(Player player, int bonus, Model model) {
		ArrayList<DevelopmentCard> buildingCards = player.getDevelopmentCard("Building");
		for (DevelopmentCard card : buildingCards) {
			ArrayList<ActionEffect> effects = card.getActionEffects();
			for (int i = 0; i < effects.size(); i++) {
				if (!(effects.get(i) instanceof ExchangeResource)
						&& card.getActionValue() <= super.getActionValue() + bonus)
					effects.get(i).performEffect(player, model);
			}
		}
	}

	/**
	 * It performs a {@link ProductionAction} by selecting all the {@link ActionEffect} that the player can afford according
	 * to the action value, added servants and eventual {@link PermanentEffect}. If an effect is of type {@link ExchangeResource} and/or
	 * has a choice that the player has to make, an interaction with the player is needed.
	 * @param player the {@link Player} that performs the action
	 * @param servants the number of {@link Servant} to increment the action value
	 * @param model that represent the state of the game.
	 */
	@Override
	public void applyAction(Player player, int servants, Model model) {
		LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>> allEffects;
		LinkedHashMap<DevelopmentCard, ArrayList<ActionEffect>> possibleEffects;
		
		Player clonedPlayer = player.clone(player.getUsername());
		this.servants = servants;
		
		int bonus = player.getBonusAcc().getBonus("IncrementProduction").getQuantity() + servants;
		player.getSpecificResource("Servant").subResource(new Servant(servants));
		
		allEffects = player.cloneCardswithActionEffect("Building");
		
		possibleEffects = getPossibleEffects(clonedPlayer, bonus, allEffects);
		
		if (!possibleEffects.isEmpty()) {
			AskEffect mex = new AskEffect(possibleEffects, this, player);
			model.notifyAsk(mex);
		} else{
			applyNoExchangeEffect(player, bonus, model);
			if (1 <= super.getActionValue() + bonus) {
				player.getPersonalBoard().applyPersonalBoardBonus("Production", player, model);
			}
		}
	}

	/**
	 * Once the player has chosen the effects he wants to perform, this method is called to apply them to him.
	 * Also effect that didn't require interaction with the player are processed now
	 * @param chosenEffects the effects chosen by the player
	 * @param player the player that performs the action
	 * @param model that represents the state of the game.
	 */
	public void applyAnswer(HashMap<DevelopmentCard, Integer> chosenEffects, Player player, Model model) {
		for (DevelopmentCard card : chosenEffects.keySet()) {
			Integer chosenEffect = chosenEffects.get(card);
			card.applyActionEffect(player, chosenEffect, model);
		}
		int bonus = player.getBonusAcc().getBonus("IncrementProduction").getQuantity() + servants;
		applyNoExchangeEffect(player, bonus, model);
		if (1 <= super.getActionValue() + bonus) {
			player.getPersonalBoard().applyPersonalBoardBonus("Production", player, model);
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Production Action with action value: " + super.getActionValue() + "\n");
		return str.toString();
	}

}
