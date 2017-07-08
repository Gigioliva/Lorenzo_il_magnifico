package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * Extension of the abstract class {@link Card}, it implements a representation
 * of the Leader Card. For this type of card, we may have multiple requisite,
 * multiple {@link PermanentEffect} and multiple {@link ImmediateEffect}.
 *
 */
public class CardLeader extends Card {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<HashMap<String, Integer>> requisite;
	private ArrayList<PermanentEffect> permanentEffect;
	private ArrayList<ImmediateEffect> immediateEffect;
	private boolean play;
	private boolean copy = false;

	/**
	 * create a new {@link LeaderCard} with only the name
	 * 
	 * @param name
	 *            is the name of the Leader Card
	 */
	public CardLeader(String name) {
		this.name = name;
		requisite = new ArrayList<HashMap<String, Integer>>();
		permanentEffect = new ArrayList<PermanentEffect>();
		immediateEffect = new ArrayList<ImmediateEffect>();
		this.play = false;
	}

	/**
	 * It creates a copy of the CardLeader.
	 * 
	 * @return an instance of {@link CardLeader}, with the same requisite and
	 *         effects as the given one.
	 */
	@Override
	public CardLeader clone() {
		CardLeader temp = new CardLeader(this.name);
		temp.play = this.play;
		temp.copy = this.copy;
		for (HashMap<String, Integer> el : requisite) {
			HashMap<String, Integer> x = new HashMap<String, Integer>();
			for (String el2 : el.keySet()) {
				x.put(el2, el.get(el2));
			}
			temp.addRequisite(x);
		}
		for (PermanentEffect el : permanentEffect) {
			temp.addPermanentEffect(el.clone());
		}
		for (ImmediateEffect el : immediateEffect) {
			temp.addImmediateEffect(el.clone());
		}
		return temp;
	}

	/**
	 * Activate flag which can say if the card can copy other played card
	 */
	public void setCopy() {
		copy = true;
	}

	public ArrayList<PermanentEffect> getPermanentEffect() {
		return permanentEffect;
	}

	/**
	 * it adds the given {@link PermanentEffect} to the card
	 * 
	 * @param permanentEffect
	 *            is the effect to be added to the card
	 */
	public void addPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect.add(permanentEffect);
	}

	/**
	 * @return all the {@link ImmediateEffect} of the card
	 */
	public ArrayList<ImmediateEffect> getImmediateEffect() {
		return immediateEffect;
	}

	/**
	 * it adds the given {@link ImmediateEffect} to the card
	 * 
	 * @param the
	 *            effect to be added to the card
	 */
	public void addImmediateEffect(ImmediateEffect immediateEffect) {
		this.immediateEffect.add(immediateEffect);
	}

	public String getName() {
		return name;
	}

	/**
	 * It applies all the {@link PermanentEffect} of the card to the player
	 * 
	 * @param player
	 *            the {@link Player} to which you want to apply the effects
	 * @param model
	 *            an instance of {@link Model}
	 */
	private void applyPermanentEffect(Player player, Model model) {
		for (PermanentEffect el : permanentEffect) {
			el.performEffect(player, model);
		}
	}

	/**
	 * It applies the nth {@link ActionEffect} of the card to the player
	 * 
	 * @param player
	 *            the {@link Player} to which you want to apply the effect
	 * @param model
	 *            an instance of {@link Model}
	 */
	private void applyActionEffect(Player player, Model model) {
		for (ImmediateEffect el : immediateEffect) {
			el.performEffect(player, model);
		}
	}

	/**
	 * Add a {@link HashMap} with the requisite to add at the already present
	 * @param req is the {@link HashMap} of requisite to add
	 */
	public void addRequisite(HashMap<String, Integer> req) {
		this.requisite.add(req);
	}

	/**
	 * Get a {@link HashMap} with the requisite mandatory to play the card
	 * @return the {@link HashMap} of requisite
	 */
	public ArrayList<HashMap<String, Integer>> getRequisite() {
		return requisite;
	}

	/**
	 * Apply all action and effects of the Leader Card that call it
	 * 
	 * @param player
	 *            is the player to which apply the effect
	 * @param model
	 *            is the model of the game
	 */
	public void playLeader(Player player, Model model) {
		if (play == false) {
			applyPermanentEffect(player, model);
			applyActionEffect(player, model);
			play = true;
		}
	}

	/**
	 * The method returns true if a {@link Player} can afford requisite to take
	 * the card.
	 * 
	 * @param player
	 *            the player for which you want to do the control
	 */
	public boolean takeCardControl(Player player) {
		boolean playable = false;
		for (HashMap<String, Integer> el : requisite) {
			playable = true;
			for (String type : el.keySet()) {
				if (el.get(type) > player.getGenericValue(type)) {
					playable = false;
				}
			}
			if (playable == true) {
				break;
			}
		}
		return playable;
	}

	public void resetLeader() {
		if (permanentEffect.size() == 0) {
			play = false;
		}
	}

	public boolean getCopy() {
		return copy;
	}

	/**
	 * @param copy
	 *            is the boolean which represent if this card can copy another
	 *            played
	 */
	public void setCopy(boolean copy) {
		this.copy = copy;
	}

	/**
	 * @return the boolean that present if the Leader is played or not.
	 */
	public boolean isPlay() {
		return play;
	}

	/**
	 * Returns a string representation of this card.
	 * 
	 * @return a string containing the most important informations about this
	 *         card
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("Card Leader: " + name);
		for (ImmediateEffect el : immediateEffect) {
			str.append("\nimmediate " + el.toString());
		}
		for (PermanentEffect el : permanentEffect) {
			str.append("Permanent " + el.toString());
		}
		return str.toString();
	}
}
