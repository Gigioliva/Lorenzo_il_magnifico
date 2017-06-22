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

public class CardCharacter extends DevelopmentCard {
	
	private static final long serialVersionUID = 1L;
	private Coin cost;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<PermanentEffect> permanentEffects;

	public CardCharacter() {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.permanentEffects = new ArrayList<PermanentEffect>();
	}
	
	@Override
	public CardCharacter clone() {
		CardCharacter temp=new CardCharacter();
		temp.setEra(this.getEra());
		temp.setName(this.getName());
		temp.cost=this.cost.clone();
		for(ImmediateEffect el: immediateEffects){
			temp.addImmediateEffect(el.clone());
		}
		for(PermanentEffect el: permanentEffects){
			temp.addPermanentEffect(el.clone());
		}
		return temp;
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

	public void applyImmediateEffects(Player player, Model model) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, model);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				((GainResource)el).doubleGain(player);
			}
		}
	}

	public void applyPermanentEffects(Player player, Model model) {
		for (PermanentEffect el : permanentEffects) {
			el.performEffect(player, model);
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

		str.append("Cost: " + cost.getQuantity() + " Coin ");

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
