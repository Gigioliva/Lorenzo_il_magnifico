package it.polimi.ingsw.ps22.action;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.CardBuilding;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.effect.ExchangeResource;
import it.polimi.ingsw.ps22.player.Player;

public class ProductionAction extends Action {

	public ProductionAction(int actionValue) {
		super(actionValue);
	}
	
	private HashMap<DevelopmentCard,ArrayList<Integer>> getPossibleEffects(Player player, int bonus, HashMap<DevelopmentCard, HashMap<ActionEffect,Integer>> allEffects){
		
		HashMap<DevelopmentCard,ArrayList<Integer>> possibleEffects = new HashMap<DevelopmentCard,ArrayList<Integer>>();
		ArrayList<DevelopmentCard> buildingCards = new ArrayList<DevelopmentCard>(allEffects.keySet());
		
		for(DevelopmentCard card: buildingCards){
			HashMap<ActionEffect,Integer> mapEffects = allEffects.get(card);
			for(ActionEffect effect: mapEffects.keySet()){
				if (effect.canAffordCost(player) && card.getActionValue() <= bonus + super.getActionValue()){
					if (possibleEffects.containsKey(card)){
						possibleEffects.get(card).add(mapEffects.get(effect));
					}
					else{
						possibleEffects.put(card, new ArrayList<Integer>());
						possibleEffects.get(card).add(mapEffects.get(effect));
					}
				}
				else{
					allEffects.get(card).remove(effect);
				}
			}
			if(allEffects.get(card).size()==0)
				allEffects.remove(card);
		}
		
		return possibleEffects;
	}
	
	private void applyNoExchangeEffect(Player player, Board board, int bonus ){
		ArrayList<DevelopmentCard> buildingCards = player.getDevelopmentCard("CardBuilding");
		for(DevelopmentCard card: buildingCards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			for(int i=0; i<effects.size(); i++){
				if(!(effects.get(i) instanceof ExchangeResource) && card.getActionValue() <= super.getActionValue() + bonus)
					effects.get(i).performEffect(player, board);
			}
		}
	}

	@Override
	public void applyAction(Player player, Board board) {
		HashMap<DevelopmentCard, HashMap<ActionEffect,Integer>> allEffects;
		HashMap<DevelopmentCard,ArrayList<Integer>> possibleEffects;
		int bonus = player.getBonusAcc().getBonus("IncrementProduction").getQuantity();
		allEffects = player.cloneCardswithActionEffect("CardBuilding");
		do{
			possibleEffects = getPossibleEffects(player,bonus, allEffects);
			HashMap<DevelopmentCard,Integer> chosenEffect = new HashMap<DevelopmentCard,Integer>();
			//passa a utente lista di carte ed effetti possibili ad ogni carta 
			//chosenEffect = askEffect...
			DevelopmentCard card = chosenEffect.keySet().iterator().next();
			card.applyActionEffect(player, board, chosenEffect.get(card));
			allEffects.remove(card);
			
		}while(!possibleEffects.isEmpty());
		
		applyNoExchangeEffect(player, board, bonus);
	}
	

}
