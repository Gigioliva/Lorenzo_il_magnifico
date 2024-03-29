package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * Player's Personal Board that contains all the bonus you can obtain during
 * production and harvest, the end bonus for {@link CharacterCard} and the
 * military requirement mandatory to take {@link CardTerritory}
 */
public class PersonalBoard implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pathname;
	private HashMap<String, GainResource> personalBonus;
	private HashMap<Integer, VictoryPoint> bonusCharacter;
	private HashMap<Integer, MilitaryPoint> requirementHarvest;
	private HashMap<Integer, VictoryPoint> bonusHarvest;

	@Override
	public PersonalBoard clone() {
		PersonalBoard temp = new PersonalBoard();
		temp.personalBonus = new HashMap<>();
		temp.bonusCharacter = new HashMap<>();
		temp.requirementHarvest = new HashMap<>();
		temp.bonusHarvest = new HashMap<>();
		temp.pathname = this.pathname;
		for (String el : personalBonus.keySet()) {
			temp.personalBonus.put(el, this.personalBonus.get(el).clone());
		}
		for (Integer el : bonusCharacter.keySet()) {
			temp.bonusCharacter.put(el, this.bonusCharacter.get(el).clone());
		}
		for (Integer el : requirementHarvest.keySet()) {
			temp.requirementHarvest.put(el, this.requirementHarvest.get(el).clone());
		}
		for (Integer el : bonusHarvest.keySet()) {
			temp.bonusHarvest.put(el, this.bonusHarvest.get(el).clone());
		}
		return temp;
	}

	public String getPathname() {
		return this.pathname;
	}

	public void setPathname(String path) {
		this.pathname = path;
	}

	public void setPersonalBonus(HashMap<String, GainResource> persBonus) {
		personalBonus = persBonus;
	}

	public void setBonusCharacter(HashMap<Integer, VictoryPoint> bonusChar) {
		bonusCharacter = bonusChar;
	}

	public void setRequirementHarvest(HashMap<Integer, MilitaryPoint> requHarvest) {
		requirementHarvest = requHarvest;
	}

	public void setBonusHarvest(HashMap<Integer, VictoryPoint> bonusHarv) {
		bonusHarvest = bonusHarv;
	}

	public HashMap<String, GainResource> getPersonalBonus() {
		return personalBonus;
	}

	public HashMap<Integer, VictoryPoint> getBonusCharacter() {
		return bonusCharacter;
	}

	public HashMap<Integer, MilitaryPoint> getRequirementHarvest() {
		return requirementHarvest;
	}

	public HashMap<Integer, VictoryPoint> getBonusHarvest() {
		return bonusHarvest;
	}

	public void applyPersonalBoardBonus(String actionType, Player player, Model model) {
		personalBonus.get(actionType).performEffect(player, model);
	}

	/**
	 * @return string useful to print all end bonus that can be give from
	 *         {@link CardCharacter}
	 */
	public String bonusCharString() {
		StringBuilder str = new StringBuilder();
		str.append("Characters end victory points: \n");
		for (Integer index : bonusCharacter.keySet()) {
			str.append(
					"  " + "position: " + index + " victory points: " + bonusCharacter.get(index).getQuantity() + "\n");
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all bonus given by {@link HarvestAction}
	 */
	public String bonusHarvestString() {
		StringBuilder str = new StringBuilder();
		str.append("Territory end victory points: \n");
		for (Integer index : bonusHarvest.keySet()) {
			str.append(
					"  " + "position: " + index + " victory points: " + bonusHarvest.get(index).getQuantity() + "\n");
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all requisite to take
	 *         {@link CardTerritory}
	 */
	public String reqHarvestString() {
		StringBuilder str = new StringBuilder();
		str.append("Requirements for terriory cards: \n");
		for (Integer index : bonusHarvest.keySet()) {
			str.append("  " + "position: " + index + " military points required: "
					+ requirementHarvest.get(index).getQuantity() + "\n");
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all personal bonus
	 */
	private String personalBonusString() {
		StringBuilder str = new StringBuilder();
		str.append("Personal bonus: \n");
		for (String type : personalBonus.keySet()) {
			str.append(type + "\n" + personalBonus.get(type).toString() + "\n");
		}

		return str.toString();

	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Personal Board: \n" + personalBonusString());
		return str.toString();
	}

}
