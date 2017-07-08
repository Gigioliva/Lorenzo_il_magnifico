package it.polimi.ingsw.ps22.server.card;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * Class that represent the cost to take a {@link CardTerritory}
 *
 */
public class RequisiteCost implements Serializable {

	private static final long serialVersionUID = 1L;
	private LinkedHashMap<String, ResourceAbstract> cost;
	private LinkedHashMap<String, ResourceAbstract> requisite;

	/**
	 * Initialize two empty {@link HashMap}
	 *
	 */
	public RequisiteCost() {
		this.cost = new LinkedHashMap<String, ResourceAbstract>();
		this.requisite = new LinkedHashMap<String, ResourceAbstract>();
	}

	/**
	 * It creates a copy of the RequisiteCost.
	 * 
	 * @return an instance of {@link RequisiteCost}, with the same requisite and
	 *         effects as the given one.
	 */
	@Override
	public RequisiteCost clone() {
		RequisiteCost temp = new RequisiteCost();
		for (String el : cost.keySet()) {
			temp.cost.put(el, cost.get(el).clone());
		}
		for (String el : requisite.keySet()) {
			temp.requisite.put(el, requisite.get(el).clone());
		}
		return temp;
	}

	/**
	 * Add the cost to the {@link RequisiteCost}
	 * @param is a {@link HashMap} that contains all costs in {@link ResourceAbstract}
	 */
	public void addCost(HashMap<String, ResourceAbstract> cost) {
		for (String el : cost.keySet()) {
			this.cost.put(el, cost.get(el));
		}
	}

	/**
	 * Add the requisite to the {@link RequisiteCost}
	 * @param is a {@link HashMap} that contains all requisite in {@link ResourceAbstract}
	 */
	public void addRequisite(HashMap<String, ResourceAbstract> requisite) {
		for (String el : requisite.keySet()) {
			this.requisite.put(el, requisite.get(el));
		}
	}
	
	/**
	 * Get all the costs
	 * @return a {@link LinkedHashMap} which contains the costs in {@link ResourceAbstract}
	 */
	public LinkedHashMap<String, ResourceAbstract> getCost() {
		return this.cost;
	}
	
	/**
	 * 
	 * @param costType is a string that represent the {@link ResourceAbstract}
	 * @return a boolean that is true if card have a requisite, false otherwise
	 */
	public boolean hasRequisite(String costType) {
		return (requisite.containsKey(costType));
	}

	/**
	 * Return a specific type of cost
	 * @param type is the type you want to know
	 * @return the request cost
	 */
	public int getSpecificCost(String type) {
		return this.cost.get(type).getQuantity();
	}

	/**
	 * Return a specific type of requisite
	 * @param type is the type you want to know
	 * @return the request requisite
	 */
	public int getSpecificRequisite(String type) {
		return this.requisite.get(type).getQuantity();
	}

	/**
	 * Get all the requisite
	 * @return a {@link LinkedHashMap} which contains the rewuisite in {@link ResourceAbstract}
	 */
	public LinkedHashMap<String, ResourceAbstract> getRequisite() {
		return this.requisite;
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
		if (requisite.size() > 0) {
			str.append("requisite: \n");
			for (String type : requisite.keySet()) {
				str.append("  " + requisite.get(type).getQuantity() + " " + type + "\n");
			}
		}

		str.append("cost: \n");
		for (String type : cost.keySet()) {
			str.append("  " + cost.get(type).getQuantity() + " " + type + "\n");
		}
		return str.toString();
	}

}