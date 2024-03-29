package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.board.TowerZone;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class CardAction extends Action {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> types;
	private HashMap<String, ResourceAbstract> discount;

	public CardAction(int actionValue) {
		super(actionValue);
		types = new ArrayList<String>();
		discount = new HashMap<String, ResourceAbstract>();
	}

	@Override
	public CardAction clone() {
		CardAction temp = new CardAction(this.getActionValue());
		temp.servants = this.servants;
		for (String el : types) {
			temp.types.add(el);
		}
		for (String el : discount.keySet()) {
			temp.discount.put(el, discount.get(el).clone());
		}
		return temp;
	}

	public void addType(String type) {
		types.add(type);
	}

	public void addDiscount(String type, ResourceAbstract resource) {
		discount.put(type, resource);
	}

	private void deApplyDiscount(Player player, String cardType) {
		player.getBonusAcc().subSales(discount, cardType);
	}

	// NB la carta potrebbe non avere un costo in quella risorsa
	private void applyDiscount(Player player, String cardType) {
		player.getBonusAcc().addSales(discount, cardType);
	}

	private boolean isTakeable(String cardType, DevelopmentCard card, Player player, int servants, Board board) {
		boolean takeable = false;
		StringBuilder temp = new StringBuilder("Increment");
		temp.append(cardType);
		int bonus = player.getBonusAcc().getBonus(temp.toString()).getQuantity() + servants;
		int plan = getRightFlat(card, cardType, board);
		applyDiscount(player, cardType);
		if (card.takeCardControl(player)
				&& board.getTower(cardType).getTowerSpaces()[plan].getActionCost() <= bonus + super.getActionValue()) {
			takeable = true;
		}
		deApplyDiscount(player, cardType);
		return takeable;
	}

	private HashMap<String, ArrayList<DevelopmentCard>> getPossibleCards(Player player, Board board, int servants) {
		HashMap<String, ArrayList<DevelopmentCard>> possibleCards = new HashMap<String, ArrayList<DevelopmentCard>>();
		for (String type : types) {
			TowerZone tower = board.getTower(type);
			TowerSpace[] spaces = tower.getTowerSpaces();
			for (int i = 0; i < spaces.length; i++) {
				DevelopmentCard card = spaces[i].getCard();
				if (card != null && isTakeable(type, card, player, servants, board)) {
					if (!possibleCards.containsKey(type)) {
						possibleCards.put(type, new ArrayList<DevelopmentCard>());
					}
					possibleCards.get(type).add(card);
				}
			}
		}
		return possibleCards;
	}
	
	/**
	 *it performs the {@link CardAction}. So it will propose to the player a list of card that he can take considering eventual
	 *{@link Servant}, {@link PermanentEffect} by which he is affected. 
	 * @param player the {@link Player} that performs the action
	 * @param board to get the affordable cards
	 * @param servants the number of {@link Servant} to increment the action value
	 * @param model that represent the state of the game.
	 */
	@Override
	public void applyAction(Player player, Board board, int servants, Model model) {
		HashMap<String, ArrayList<DevelopmentCard>> possibleCards = getPossibleCards(player, board, servants);
		AskCard mex = new AskCard(possibleCards, player, this);
		model.notifyAsk(mex);
	}

	private int getRightFlat(DevelopmentCard chosenCard, String chosenType, Board board) {
		TowerZone tower = board.getTower(chosenType);
		for (int i = 0; i < tower.getTowerSpaces().length; i++) {
			if (tower.getTowerSpaces()[i].getCard() == chosenCard) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Once the player has chosen the card he wants, this method is called to take it and give it to him.
	 * @param chosenCard the card the player has chosen
	 * @param player the player that performs the action
	 * @param model that represents the state of the game.
	 */
	public void applyAnswer(HashMap<String, DevelopmentCard> chosenCard, Player player, Board board) {
		
		String chosenType = chosenCard.keySet().iterator().next();
		int flat = getRightFlat(chosenCard.get(chosenType), chosenType, board);
		board.getTower(chosenType).takeCard(flat, player, discount);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("You can take one card between the following types: \n");
		for (int i = 0; i < types.size(); i++) {
			str.append("  " + types.get(i) + "\n");
		}

		str.append("with action value: " + super.getActionValue() + "\n");

		if (discount.size() != 0) {
			str.append("with discount: \n");
			for (String string : discount.keySet()) {
				str.append(discount.get(string).getQuantity() + " " + string + "\n");
			}
		}
		return str.toString();
	}

}
