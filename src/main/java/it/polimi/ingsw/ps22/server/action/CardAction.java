package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.board.TowerZone;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class CardAction extends Action {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<String> types;
	private HashMap<String, ResourceAbstract> discount; 

	public CardAction(int actionValue) {
		super(actionValue);
		types = new ArrayList<String>();
		discount = new HashMap<String, ResourceAbstract>();
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
		AskCard mex = new AskCard(possibleCards, player, this);
		mex.applyAsk();
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
		/*
		 * quando risponde trovo il piano relativo alla carta e applica il prendi carta della torre
		 */
		String chosenType = chosenCard.keySet().iterator().next();
		int flat = getRightFlat(chosenCard.get(chosenType), chosenType, board);
		board.getTower(chosenType).takeCard(flat, player, discount);
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
