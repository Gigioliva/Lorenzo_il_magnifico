package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * Extension of the abstract class {@link DevelopmentCard}, it implements a
 * representation of the Building Card. For this type of card, we have only one
 * {@link HashMap} for costs, multiple {@link ActionEffects} and multiple
 * {@link ImmediateEffect}. No more type of effect are allowed.
 *
 */
public class CardBuilding extends DevelopmentCard {

	private static final long serialVersionUID = 1L;
	private int actionValue;
	private HashMap<String, Resource> cost;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<ActionEffect> actionEffects;

	/**
	 * it creates an empty Venture Card, with no {@link Resource} cost, no
	 * {@link ActionEffect}, no {@link ImmediateEffect}.
	 */
	public CardBuilding() {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.actionEffects = new ArrayList<ActionEffect>();
		this.cost = new HashMap<String, Resource>();
		this.actionValue = 0;
	}

	/**
	 * It creates a copy of the CardBuilding.
	 * 
	 * @return an instance of {@link CardBuilding}, with the same effects as the
	 *         given one.
	 */
	@Override
	public CardBuilding clone() {
		CardBuilding temp = new CardBuilding();
		temp.setEra(this.getEra());
		temp.setName(this.getName());
		temp.actionValue = this.actionValue;
		for (String el : cost.keySet()) {
			temp.cost.put(el, cost.get(el).clone());
		}
		for (ImmediateEffect el : immediateEffects) {
			temp.addImmediateEffect(el.clone());
		}
		for (ActionEffect el : actionEffects) {
			temp.addActionEffect(el.clone());
		}
		return temp;
	}

	/**
	 * It sets the action value.
	 * 
	 * @param actionValue
	 *            the action value of the card
	 */
	public void setActionValue(int actionValue) {
		this.actionValue = actionValue;
	}

	/**
	 * Add a cost to the card
	 * 
	 * @param type
	 *            is the type of the respurce
	 * @param resource
	 *            represent the {@link Resource} cost to add at the card
	 */
	public void addCost(String type, Resource resource) {
		this.cost.put(type, resource);
	}

	/**
	 * @return It returns a {@link HashMap} containing all the {@link Resource}
	 *         cost of the card
	 */
	public HashMap<String, Resource> getCost() {
		return this.cost;
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
	 * it adds the given {@link ActionEffect} to the card
	 * 
	 * @param effect
	 *            is the effect to be added to the card
	 */
	public void addActionEffect(ActionEffect effect) {
		this.actionEffects.add(effect);
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
	 * @return an ArrayList containing all the {@link EndEffect} of the card
	 */
	public ArrayList<ActionEffect> getActionEffects() {
		return this.actionEffects;
	}

	/**
	 * It applies the nth {@link ActionEffect} of the card to the player
	 * 
	 * @param player
	 *            the {@link Player} to which you want to apply the effect
	 * @param model
	 *            an instance of {@link Model}
	 * @param number
	 *            an integer representing the effect to apply
	 */
	public void applyActionEffect(Player player, int number, Model model) {
		try {
			this.actionEffects.get(number).performEffect(player, model);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

	/**
	 * @return the action value relative to this card.
	 */
	public int getActionValue() {
		return this.actionValue;
	}

	/**
	 * The method returns true if a {@link Player} can afford to take the card.
	 * A player can afford to take a card if it have all the {@link Resource}
	 * mandatory to buy this card.
	 * 
	 * @param player
	 *            the player for which you want to do the control
	 */
	@Override
	public boolean takeCardControl(Player player) {
		HashMap<String, ResourceAbstract> actualCosts = getActualCost(player);
		for (String type : actualCosts.keySet()) {
			int costRequired = actualCosts.get(type).getQuantity();
			int playerResource = player.getResources().get(type).getQuantity();
			if (costRequired > playerResource)
				return false;
		}
		return true;
	}

	/**
	 * It calculates cost that the player must afford to take this card
	 * 
	 * @param player
	 *            the {@link Player} for which you want to get the possible
	 *            costs
	 * @return an {@link HashMap} containing all the {@link Resource} you must
	 *         pay
	 */
	private HashMap<String, ResourceAbstract> getActualCost(Player player) {
		HashMap<String, ResourceAbstract> actualCosts = new HashMap<String, ResourceAbstract>();
		for (String type : cost.keySet()) {
			ResourceAbstract cardCost = cost.get(type);
			ResourceAbstract reduction = player.getBonusAcc().getSaleBuilding().get(type);
			if (reduction.getQuantity() > cardCost.getQuantity())
				actualCosts.put(type, new ResourceAbstract(0));
			else {
				actualCosts.put(type, new ResourceAbstract(cardCost.getQuantity() - reduction.getQuantity()));
			}
		}
		return actualCosts;
	}

	/**
	 * It applies the cost given in input to the given {@link Player}.
	 * 
	 * @param player
	 *            the player to which you want to apply the cost
	 */
	@Override
	public void applyCostToPlayer(Player player) {
		HashMap<String, ResourceAbstract> actualCosts = getActualCost(player);
		for (String type : actualCosts.keySet()) {
			player.getSpecificResource(type).subResource(actualCosts.get(type));
		}
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

		str.append("nome carta = " + this.getName() + "\n");

		str.append("era: " + super.getEra() + "\n");

		str.append("valore azione = " + actionValue + "\n");

		/*
		 * str.append("costo: \n"); for (String type : cost.keySet()) {
		 * str.append("  " + cost.get(type).getQuantity() + " " + type + "\n");
		 * }
		 * 
		 * if (immediateEffects.size() > 0) {
		 * str.append("Immediate effects: \n"); for (int i = 0; i <
		 * immediateEffects.size(); i++) { str.append("  [" + (i + 1) + "]" +
		 * immediateEffects.get(i).toString() + "\n"); } }
		 * 
		 * str.append("Action effects: \n"); for (int i = 0; i <
		 * actionEffects.size(); i++) { str.append("  [" + (i + 1) + "]" +
		 * actionEffects.get(i).toString() + "\n"); }
		 */
		return str.toString();
	}

}
