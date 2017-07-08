package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * Extension of the abstract class {@link Card}, it implements a
 * representation of the Excomm Card. For this type of card, we may have 
 * multiple {@link EndEffect} and multiple
 * {@link PermanentEffect}. No more type of effect are allowed.
 *
 */
public class CardExcomm extends Card {

	private static final long serialVersionUID = 1L;
	private int era;
	private String pathname;
	private ArrayList<PermanentEffect> permanentEffects;
	private ArrayList<EndEffect> endEffects;

	/**
	 * Create an empty {@link CardExcomm}
	 */
	public CardExcomm() {
		era = 0;
		pathname = null;
		endEffects = new ArrayList<EndEffect>();
		permanentEffects = new ArrayList<PermanentEffect>();
	}

	/**
	 * It creates a copy of the CardExcomm.
	 * 
	 * @return an instance of {@link CardExcomm}, with the same effects as the
	 *         given one.
	 */
	@Override
	public CardExcomm clone() {
		CardExcomm temp = new CardExcomm();
		temp.era = this.era;
		temp.pathname = this.pathname;
		for (PermanentEffect el : permanentEffects) {
			temp.addPermanentEffect(el.clone());
		}
		for (EndEffect el : endEffects) {
			temp.addEndEffect(el.clone());
		}
		return temp;
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
	 * it adds the given {@link EndEffect} to the card
	 * 
	 * @param the
	 *            effect to be added to the card
	 */
	public void addEndEffect(EndEffect effect) {
		this.endEffects.add(effect);
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
	 * It adds to {@link EndEffect} of the player all the {@link EndEffect} of
	 * the card
	 * 
	 * @param player the {@link Player} to which you want to add the effects
	 */
	public void loadEndEffects(Player player) {
		for (EndEffect effect : endEffects) {
			player.getEndEffects().add(0, effect);
		}
	}

	public void setEra(int era) {
		this.era = era;
	}

	public String getPathname() {
		return this.pathname;
	}

	public void setPathname(String path) {
		this.pathname = path;
	}

	public int getEra() {
		return this.era;
	}

	/**
	 * Returns a string representation of this card.
	 * 
	 * @return a string containing the most important informations about this
	 *         card
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("era: " + era + "\n");

		if (permanentEffects.size() > 0) {
			str.append("permanent effects: \n");
			int i = 1;
			for (PermanentEffect effect : permanentEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
				i++;
			}
		}

		if (endEffects.size() > 0) {
			str.append("end effects: \n");
			int i = 1;
			for (EndEffect effect : endEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
				i++;
			}
		}

		return str.toString();
	}

}
