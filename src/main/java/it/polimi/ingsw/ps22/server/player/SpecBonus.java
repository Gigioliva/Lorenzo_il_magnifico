package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Collection of all Special bonus of a {@link Player}
 */
public class SpecBonus implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<String, Boolean> bonus;

	/**
	 * Constructor that creates all Object of the {@link HashMap} ad initializes all
	 * bonus attribute to false.
	 */
	public SpecBonus() {
		bonus = new HashMap<String, Boolean>();
		bonus.put("NoMarket", false);
		bonus.put("DoubleServant", false);
		bonus.put("SkipFirstMove", false);
		bonus.put("GainTowers", false);
		bonus.put("OccupiedSpace", false);
		bonus.put("NoCostTower", false);
		bonus.put("Neutral+3", false);
		bonus.put("AllFamilyCol5", false);
		bonus.put("FamilyCol+2", false);
		bonus.put("PointVicChurch+5", false);
		bonus.put("NoTerritoryReq", false);
		bonus.put("DoubleGain", false);
	}

	@Override
	public SpecBonus clone() {
		SpecBonus temp = new SpecBonus();
		for (String el : bonus.keySet()) {
			temp.bonus.put(el, this.bonus.get(el));
		}
		return temp;
	}

	/**
	 * Set the relative boolean to true
	 * 
	 * @param type
	 *            is a String that represent the key of the HashMap which the
	 *            Object must be set to true
	 */
	public void setSpecBonus(String type) {
		bonus.remove(type);
		bonus.put(type, new Boolean(true));
	}

	/*
	 * @param type is a String that represent the key of the HashMap which we
	 * must return
	 * 
	 * @return boolean that say if the bonus is active
	 */
	public boolean returnBool(String type) {
		return bonus.get(type);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("You have the following bonus/malus \n");

		for (String type : bonus.keySet()) {
			if (bonus.get(type))
				str.append("  " + type + "\n");
		}

		return str.toString();
	}

}
