package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.action.CardAction;
import it.polimi.ingsw.ps22.server.answer.AnswerCard;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ExtraAction;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * A card may have an {@link ExtraAction} effect that allows the player to take
 * another card, given a certain action value. This message propose to the
 * player a list of cards that he can afford, and the player will have to choose
 * one
 *
 */
public class AskCard extends MessageAsk {

	private static final long serialVersionUID = 1L;
	private HashMap<String, ArrayList<DevelopmentCard>> possibleCard;
	private Player player;
	private CardAction cardAction;

	/**
	 * It creates a new {@link AskCard} message
	 * 
	 * @param possibleCard
	 *            all the possible {@link DevelopmentCard} the the player can
	 *            afford
	 * @param player
	 *            the target player
	 * @param cardAction
	 *            the card the invoked the action
	 */
	public AskCard(HashMap<String, ArrayList<DevelopmentCard>> possibleCard, Player player, CardAction cardAction) {
		super(player.getUsername());
		this.player = player;
		this.possibleCard = possibleCard;
		this.cardAction = cardAction;
		StringBuilder str = new StringBuilder();
		for (String el : possibleCard.keySet()) {
			str.append("Le carte di tipo " + el + " che puoi prendere sono: \n");
			for (DevelopmentCard card : possibleCard.get(el)) {
				str.append(card.getName() + "\n");
			}
		}
		str.append("Quale scegli?: "); // chiedi il tipo di carta e il nome
										// della carta
		setString(str.toString());
	}

	/**
	 * 
	 * @param str
	 *            the text of the message
	 * @param id
	 *            the id of the message
	 * @param possibleCard
	 *            all the possible {@link DevelopmentCard} the the player can
	 *            afford
	 */
	public AskCard(String str, int id, HashMap<String, ArrayList<DevelopmentCard>> possibleCard) {
		super(str, id);
		HashMap<String, ArrayList<DevelopmentCard>> temp = new HashMap<String, ArrayList<DevelopmentCard>>();
		for (String el : possibleCard.keySet()) {
			temp.put(el, new ArrayList<DevelopmentCard>());
			for (DevelopmentCard card : possibleCard.get(el)) {
				temp.get(el).add(card.clone());
			}
		}
		this.possibleCard = temp;
	}

	/**
	 * 
	 * @return all the possible {@link DevelopmentCard} the the player can
	 *         afford
	 */
	public HashMap<String, ArrayList<DevelopmentCard>> getPossibleCard() {
		return possibleCard;
	}

	/**
	 * 
	 * @return the target player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @return the card the invoked the action
	 */
	public CardAction getCardAction() {
		return cardAction;
	}

	@Override
	public void applyDefault(Model model) {
		ArrayList<String> type = new ArrayList<>(possibleCard.keySet());
		AnswerCard ans = new AnswerCard(this.getId(), type.get(0), possibleCard.get(type.get(0)).get(0).getName());
		ans.applyAnswer(model);
	}

	public AskCard accept(VisitorA visitor) {
		return visitor.visit(this);
	}

	public void accept(VisitorB visitor) {
		visitor.visit(this);
	}

}
