package it.polimi.ingsw.ps22.server.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.BonusAbstract;

public class BonusEffect implements PermanentEffect {

	private static final long serialVersionUID = 1L;

	private HashMap<String, BonusAbstract> bonus;

	public BonusEffect() {
		bonus = new HashMap<String, BonusAbstract>();
	}

	public void addBonus(String type, BonusAbstract value) {
		bonus.put(type, value);
	}
	
	@Override
	public BonusEffect clone(){
		BonusEffect temp=new BonusEffect();
		for(String el: bonus.keySet()){
			temp.bonus.put(el, bonus.get(el).clone());
		}
		return temp;
	}

	/**
	 * It adds to the bonus accumulator of the {@link Player} the permanent bonus-malus of the effect
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	@Override
	public void performEffect(Player player, Model model) {
		player.getBonusAcc().addBonus(bonus);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (String type : bonus.keySet()) {
			str.append(bonus.get(type).getQuantity() + " " + type + "\n");
		}

		return str.toString();
	}

}
