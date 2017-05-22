package it.polimi.ingsw.ps22.action;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.CardBuilding;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.player.Player;

public class ProductionAction extends Action {

	public ProductionAction(int actionValue) {
		super(actionValue);
	}
	
	private HashMap<DevelopmentCard,ArrayList<Integer>> getPossibleEffects(Player player){
		
		ArrayList<DevelopmentCard> buildingCards = player.getDevelopmentCard("CardBuilding");
		HashMap<DevelopmentCard,ArrayList<Integer>> possibleEffects = new HashMap<DevelopmentCard, ArrayList<Integer>>();
		for(DevelopmentCard card: buildingCards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			for(int i=0; i<effects.size(); i++){
				if (effects.get(i).canAffordCost(player)){
					if (possibleEffects.containsKey(card)){
						possibleEffects.get(card).add(i);
					}
					else{
						possibleEffects.put(card, new ArrayList<Integer>());
						possibleEffects.get(card).add(i);
					}
				}
			}
		}
		return possibleEffects;
	}

	@Override
	public void applyAction(Player player, Board board) {
		//ricordarsi che i gain devono essere fatti per ultimi e che gli scambi possono essere più di uno
		HashMap<DevelopmentCard,ArrayList<Integer>> possibleEffects = getPossibleEffects(player);
		for(DevelopmentCard card: possibleEffects.keySet()){
			if(card.getActionValue() <= super.getActionValue()){
				//se la size degli effetti possibili è maggiore di uno, chiedi all'utente quale vuole fare, mi deve ritornare un intero
				if (possibleEffects.get(card).size() > 1){
				//chiedi all'utente quale effetto vuol fare, mi ritorna quello che vuole fare
				}
				else{
					card.applyActionEffect(player, board, possibleEffects.get(card).get(0));
				}
			}
		}
	}

}
