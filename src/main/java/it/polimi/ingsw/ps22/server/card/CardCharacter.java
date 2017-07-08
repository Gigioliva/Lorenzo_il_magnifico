package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Resource;

/**
 * 
 * Extension of the abstract class {@link DevelopmentCard}, it implements a
 * representation of the Character Card. For this type of card, we may have a
 * cost in {@link Coin}, multiple {@link PermanentEffect} and multiple
 * {@link ImmediateEffect}. No more type of effect are allowed.
 *
 */
public class CardCharacter extends DevelopmentCard {

	private static final long serialVersionUID = 1L;
	private Coin cost;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<PermanentEffect> permanentEffects;

	/**
	 * it creates an empty Character Card, with no {@link ImmediateEffect}, no
	 * {@link PermanentEffect}, no {@link Coin} cost.
	 */
	public CardCharacter() {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.permanentEffects = new ArrayList<PermanentEffect>();
	}

	/**
	 * It creates a copy of the CardCharacter.
	 * 
	 * @return an instance of {@link CardCharacter}, with the same costs and
	 *         effects as the given one.
	 */
	@Override
	public CardCharacter clone() {
		CardCharacter temp = new CardCharacter();
		temp.setEra(this.getEra());
		temp.setName(this.getName());
		temp.cost = this.cost.clone();
		for (ImmediateEffect el : immediateEffects) {
			temp.addImmediateEffect(el.clone());
		}
		for (PermanentEffect el : permanentEffects) {
			temp.addPermanentEffect(el.clone());
		}
		return temp;
	}

	/**
	 * Add the cost a player must pay to take a card
	 * 
	 * @param coin
	 *            is the cost
	 */
	public void addCost(Coin coin) {
		this.cost = coin;
	}

	/**
	 * Get cost a player must pay to take a card
	 * 
	 * @return a {@link HashMap} whit the costs
	 */
	public HashMap<String, Resource> getCost() {
		HashMap<String, Resource> temp = new HashMap<String, Resource>();
		temp.put("Coin", this.cost);
		return temp;
	}

	/**
	 * it adds the given {@link ImmediateEffect} to the card
	 * 
	 * @param the
	 *            effect to be added to the card
	 */
	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	/**
	 * it adds the given {@link PermanentEffect} to the card
	 * 
	 * @param the
	 *            effect to be added to the card
	 */
	public void addPermanentEffect(PermanentEffect effect) {
		this.permanentEffects.add(effect);
	}

	/**
	 * It applies all the {@link ImmediateEffect} of the card to the player
	 * 
	 * @param player
	 *            the {@link Player} to which you want to apply the effects
	 * @param model
	 *            an instance of {@link Model}
	 */
	public void applyImmediateEffects(Player player, Model model) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, model);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				((GainResource) el).doubleGain(player);
			}
		}
	}

	/**
	 * It applies all the {@link PermanentEffect} of the card to the player
	 * 
	 * @param player
	 *            the {@link Player} to which you want to apply the effects
	 * @param model
	 *            an instance of {@link Model}
	 */
	public void applyPermanentEffects(Player player, Model model) {
		for (PermanentEffect el : permanentEffects) {
			el.performEffect(player, model);
		}
	}

	/**
	 * It calculates cost in {@link Coin} that the player must afford to take
	 * the card
	 * 
	 * @param player
	 *            the {@link Player} for which you want to get the possible
	 *            costs
	 * @return a {@link Coin} which represent the cost
	 */
	private Coin getActualCost(Player player) {
		Coin reduction = player.getBonusAcc().getSaleCharacter();
		if (reduction.getQuantity() > cost.getQuantity())
			return new Coin(0);
		else {
			return new Coin(cost.getQuantity() - reduction.getQuantity());
		}
	}

	/**
	 * It applies the cost given in input to the given {@link Player}.
	 *
	 * @param player
	 *            the player to which you want to apply the cost
	 */
	@Override
	public void applyCostToPlayer(Player player) {
		Coin actualCost = getActualCost(player);
		player.getResources().get("Coin").subResource(actualCost);
	}

	/**
	 * The method returns true if a {@link Player} can afford to take the card.
	 * A player can afford to take a card if it have enough {@link Coin}.
	 * 
	 * @param player
	 *            the player for which you want to do the control
	 */
	@Override
	public boolean takeCardControl(Player player) {
		if(player.getSizeCard("Character")>=6){
			return false;
		}
		int requiredCost = getActualCost(player).getQuantity();
		int playerCoin = player.getResources().get("Coin").getQuantity();
		return (playerCoin >= requiredCost);
	}

	/**
	 * Returns a string representation of this card.
	 * 
	 * @return a string containing the most important informations about this
	 *         card
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("nome carta: " + super.getName() + "\n");

		str.append("era: " + super.getEra() + "\n");

		str.append("Cost: " + cost.getQuantity() + " Coin ");

		/*
		 * if (immediateEffects.size() > 0) {
		 * str.append("\nimmediate effects: \n"); int i = 1; for
		 * (ImmediateEffect effect : immediateEffects) { str.append("  " + "[" +
		 * i + "] " + effect.toString()); i++; } }
		 * 
		 * if (permanentEffects.size() > 0) {
		 * str.append("permanent effects: \n"); int i = 1; for (PermanentEffect
		 * effect : permanentEffects) { str.append("  " + "[" + i + "] " +
		 * effect.toString()); i++; } }
		 */
		return str.toString();
	}

	/*
	 * public void applyAllEffects(Player player, Board board){
	 * applyPermanentEffects(player,board); applyImmediateEffects(player,
	 * board); }
	 */
}
