package it.polimi.ingsw.ps22.action;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.board.TowerSpace;
import it.polimi.ingsw.ps22.board.TowerZone;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.message.AskCard;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class CardAction extends Action {
	private ArrayList<String> types; // inventato da noi per possibilità di
										// prendere più tipi di carte
	private HashMap<String, ResourceAbstract> discount; // da sottrarre al costo
														// della carta e
														// riaggiungerlo dopo
														// averla presa o se non
														// può prenderla
	private int flat;

	public CardAction(int actionValue) {
		super(actionValue);
		types = new ArrayList<String>();
		discount = new HashMap<String, ResourceAbstract>();
		flat = 0;
	}

	public void addType(String type) {
		types.add(type);
	}
	
	public void addDiscount(String type, ResourceAbstract resource){
		discount.put(type, resource);
	}
	
	private void deApplyDiscount(Player player, String cardType){
		player.getBonusAcc().subSales(discount, cardType);
	}
	
	//NB la carta potrebbe non avere un costo in quella risorsa
	private void applyDiscount(Player player, String cardType){
		player.getBonusAcc().addSales(discount, cardType);
	}
	
	private boolean isTakeable(String cardType, DevelopmentCard card, Player player, int servants){
		boolean takeable = false;
		int bonus = player.getBonusAcc().getBonus(cardType).getQuantity() + servants;
		applyDiscount(player, cardType);
		if(card.takeCardControl(player) && card.getActionValue() <= bonus + super.getActionValue() ){
			takeable = true;
		}
		deApplyDiscount(player, cardType);
		return takeable;
	}
	
	private HashMap<String,ArrayList<DevelopmentCard>> getPossibleCards(Player player, Board board, int servants){
		HashMap<String,ArrayList<DevelopmentCard>> possibleCards =  new HashMap<String,ArrayList<DevelopmentCard>>();
		for(String type: types){
			TowerZone tower = board.getTower(type);
			TowerSpace[] spaces = tower.getTowerSpaces();
			for(int i = 0; i < spaces.length; i++){
				DevelopmentCard card = spaces[i].getCard();
					if(isTakeable(type,card,player,servants)){
						if(!possibleCards.containsKey(type)){
							possibleCards.put(type,new ArrayList<DevelopmentCard>());
						}
						possibleCards.get(type).add(card);
					}
				}
			}
		return possibleCards;
	}
	

	@Override
	public void applyAction(Player player, Board board, int servants) {
		HashMap<String,ArrayList<DevelopmentCard>> possibleCards = getPossibleCards(player, board, servants);
		//String chosenType = "";
		//DevelopmentCard chosenCard = null;
		AskCard mex = new AskCard(possibleCards);
		mex.applyAsk();
		HashMap<String, HashMap<DevelopmentCard,Integer>> chosen = null;
		//notifica all'utente quale carta vuol prendere
		//player.getDevelopmentCard(chosenType).add(chosenCard);
		for(String chosenType: chosen.keySet()){
			for(DevelopmentCard chosenCard: chosen.get(chosenType).keySet()){
				Integer index = chosen.get(chosenType).get(chosenCard);
				player.getDevelopmentCard(chosenType).add(chosenCard);
				TowerSpace space = board.getTower(chosenType).getTowerSpaces()[index];
				space.removeCard();
			}
		}
		/*
		board.getTower(chosenType).getTowerSpace()[chosen.]
		chosenCard.applyImmediateEffects(player, board);
		chosenCard.applyPermanentEffects(player, board);
		*/
		
	}
	
	private int getRightFlat(DevelopmentCard chosenCard,String chosenType, Board board){
		TowerZone tower = board.getTower(chosenType);
		for(int i=0; i<tower.getTowerSpaces().length; i++){
			if(tower.getTowerSpaces()[i].getCard() == chosenCard){
				return i;
			}
		}
		return -1;
	}
	
	public void applyAnswer(HashMap<String, DevelopmentCard> chosenCard, Player player, Board board){
		String chosenType = chosenCard.keySet().iterator().next();
		player.getDevelopmentCard(chosenType).add(chosenCard.get(chosenType));
		
		//applico costo a giocatore
		applyDiscount(player, chosenType);
		chosenCard.get(chosenType).applyCostToPlayer(player);
		deApplyDiscount(player, chosenType);
		
		//rimuovo carta da board
		int flat = getRightFlat(chosenCard.get(chosenType), chosenType, board);
		board.getTower(chosenType).getTowerSpaces()[flat].removeCard();
		
		
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("You can take one card between the following types: \n");
		for(int i = 0; i< types.size(); i++){
			str.append("  " + types.get(i) + "\n");
		}
		
		if(discount.size() == 0){
			str.append("with discount: \n");
			for(String string: discount.keySet()){
			str.append(discount.get(string).getQuantity() + " " + string + "\n");
			}
		}
		return str.toString();
	}

}
