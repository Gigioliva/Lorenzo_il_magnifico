package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.effect.PermanentEffect;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.Resource;

public class CardCharacter extends DevelopmentCard {
	private Coin cost; // provare a usare tutto nel costruttore se XML permette
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<PermanentEffect> permanentEffects;

	public CardCharacter() {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.permanentEffects = new ArrayList<PermanentEffect>();
	}

	public void addCost(Coin coin) {
		this.cost = coin;
	}

	public HashMap<String, Resource> getCost() {
		HashMap<String, Resource> temp = new HashMap<String, Resource>();
		temp.put("Coin", this.cost);
		return temp;
	}

	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	public void addPermanentEffect(PermanentEffect effect) {
		this.permanentEffects.add(effect);
	}

	public void applyImmediateEffects(Player player) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				el.performEffect(player);
			}
		}
	}

	public void applyPermanentEffects(Player player) {
		for (PermanentEffect el : permanentEffects) {
			el.performEffect(player);
		}
	}

	private Coin getActualCost(Player player) {
		Coin reduction = player.getBonusAcc().getSaleCharacter();
		if (reduction.getQuantity() > cost.getQuantity())
			return new Coin(0);
		else {
			return new Coin(cost.getQuantity() - reduction.getQuantity());
		}
	}

	@Override
	public void applyCostToPlayer(Player player) {
		Coin actualCost = getActualCost(player);
		player.getResources().get("Coin").subResource(actualCost);
	}

	@Override
	public boolean takeCardControl(Player player) {
		int requiredCost = getActualCost(player).getQuantity();
		int playerCoin = player.getResources().get("Coin").getQuantity();
		return (playerCoin >= requiredCost);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("nome carta: " + super.getName() + "\n");

		str.append("era: " + super.getEra() + "\n");

		str.append("Cost: " + cost.getQuantity() + " Coin");

		if (immediateEffects.size() > 0) {
			str.append("\nimmediate effects: \n");
			int i = 1;
			for (ImmediateEffect effect : immediateEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
				i++;
			}
		}

		if (permanentEffects.size() > 0) {
			str.append("permanent effects: \n");
			int i = 1;
			for (PermanentEffect effect : permanentEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
				i++;
			}
		}

		return str.toString();
	}

	/*
	 * public void applyAllEffects(Player player, Board board){
	 * applyPermanentEffects(player,board); applyImmediateEffects(player,
	 * board); }
	 */
}
