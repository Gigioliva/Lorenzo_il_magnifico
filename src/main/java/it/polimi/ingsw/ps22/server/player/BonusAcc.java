package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.resource.*;

/**
 * Collection of all bonus counters of a {@link Player}
 */
public class BonusAcc implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<String, BonusAbstract> accumulator;
	private HashMap<String, Resource> saleBuilding;
	private HashMap<String, Coin> saleCharacter;
	private HashMap<String, ResourceAbstract> saleVenture;

	/**
	 * Constructor that creates all Object of the 4 {@link HashMap} ad
	 * initializes all bonus counters to the default values
	 */
	public BonusAcc() {
		accumulator = new HashMap<String, BonusAbstract>();
		saleBuilding = new HashMap<String, Resource>();
		saleCharacter = new HashMap<String, Coin>();
		saleVenture = new HashMap<String, ResourceAbstract>();
		accumulator.put("Coin", new Coin(0));
		accumulator.put("Stone", new Stone(0));
		accumulator.put("Wood", new Wood(0));
		accumulator.put("Servant", new Servant(0));
		accumulator.put("VictoryPoint", new VictoryPoint(0));
		accumulator.put("FaithPoint", new FaithPoint(0));
		accumulator.put("MilitaryPoint", new MilitaryPoint(0));
		accumulator.put("IncrementBuilding", new IncrementBuilding(0));
		accumulator.put("IncrementCharacter", new IncrementCharacter(0));
		accumulator.put("IncrementTerritory", new IncrementTerritory(0));
		accumulator.put("IncrementVenture", new IncrementVenture(0));
		accumulator.put("IncrementHarvest", new IncrementHarvest(0));
		accumulator.put("IncrementProduction", new IncrementProduction(0));
		accumulator.put("IncrementDice", new IncrementDice(0));
		saleBuilding.put("Coin", new Coin(0));
		saleBuilding.put("Stone", new Stone(0));
		saleBuilding.put("Wood", new Wood(0));
		saleCharacter.put("Coin", new Coin(0));
		saleVenture.put("Coin", new Coin(0));
		saleVenture.put("Stone", new Stone(0));
		saleVenture.put("Wood", new Wood(0));
		saleVenture.put("VictoryPoint", new VictoryPoint(0));
		saleVenture.put("MilitaryPoint", new MilitaryPoint(0));
		saleVenture.put("FaithPoint", new FaithPoint(0));
	}

	@Override
	public BonusAcc clone() {
		BonusAcc temp = new BonusAcc();
		for (String el : accumulator.keySet()) {
			temp.accumulator.put(el, this.accumulator.get(el).clone());
		}
		for (String el : saleBuilding.keySet()) {
			temp.saleBuilding.put(el, this.saleBuilding.get(el).clone());
		}
		for (String el : saleCharacter.keySet()) {
			temp.saleCharacter.put(el, this.saleCharacter.get(el).clone());
		}
		for (String el : saleVenture.keySet()) {
			temp.saleVenture.put(el, this.saleVenture.get(el).clone());
		}
		return temp;
	}

	/**
	 * Get function
	 * @param type is the key of the {@link HashMap} with all players bonus accumulator
	 * @return a {@link BonusAbstract} which represent the bonus
	 */
	public BonusAbstract getBonus(String type) {
		return accumulator.get(type);
	}

	/**
	 * * Method that permit to add discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value add at the
	 *            accumulators
	 */
	public void addBonus(HashMap<String, BonusAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			this.accumulator.get(el).addResource(bonus.get(el));
		}
	}

	/**
	 * Method that permit to add discounts of the specified type
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to add to the
	 *            building discounts
	 * @param type
	 *            represent the type of the card which you want to add the bonus
	 */
	public void addSales(HashMap<String, ResourceAbstract> bonus, String type) {
		if (type.equalsIgnoreCase("Building")) {
			addSaleBuilding(bonus);
		}
		if (type.equalsIgnoreCase("Character")) {
			addSaleCharacter(bonus);
		}
		if (type.equalsIgnoreCase("Venture")) {
			addSaleVenture(bonus);
		}

	}

	/**
	 * Method that permit to add building discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to add to the
	 *            building discounts
	 */
	private void addSaleBuilding(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleBuilding.containsKey(el))
				this.saleBuilding.get(el).addResource(bonus.get(el));
		}
	}

	/**
	 * Method that permit to add character discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to add to the
	 *            character discounts
	 */
	private void addSaleCharacter(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleCharacter.containsKey(el))
				this.saleCharacter.get(el).addResource(bonus.get(el));
		}
	}

	/**
	 * * Method that permit to add venture discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to add to the
	 *            venture discounts
	 */
	private void addSaleVenture(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleVenture.containsKey(el))
				this.saleVenture.get(el).addResource(bonus.get(el));
		}
	}

	/**
	 * Method that permit to sub discounts of the specified type
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to sub to the
	 *            building discounts
	 * @param type
	 *            represent the type of the card which you want to sub the bonus
	 */
	public void subSales(HashMap<String, ResourceAbstract> bonus, String type) {
		if (type.equalsIgnoreCase("Building")) {
			subSaleBuilding(bonus);
		}
		if (type.equalsIgnoreCase("Character")) {
			subSaleCharacter(bonus);
		}
		if (type.equalsIgnoreCase("Venture")) {
			subSaleVenture(bonus);
		}

	}

	/**
	 * * Method that permit to sub venture discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to sub to the
	 *            building discounts
	 */
	private void subSaleBuilding(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleBuilding.containsKey(el))
				this.saleBuilding.get(el).subResource(bonus.get(el));
		}
	}

	/**
	 * * Method that permit to sub venture discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to sub to the
	 *            character discounts
	 */
	private void subSaleCharacter(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleCharacter.containsKey(el))
				this.saleCharacter.get(el).subResource(bonus.get(el));
		}
	}

	/**
	 * * Method that permit to sub venture discounts
	 * 
	 * @param bonus
	 *            is a {@link HashMap} that contains the value to sub to the
	 *            venture discounts
	 */
	private void subSaleVenture(HashMap<String, ResourceAbstract> bonus) {
		ArrayList<String> temp = new ArrayList<String>(bonus.keySet());
		for (String el : temp) {
			if (saleVenture.containsKey(el))
				this.saleVenture.get(el).subResource(bonus.get(el));
		}
	}

	/**
	 * @return a {@link HashMap} with the discount useful to take a
	 *         {@link CardBuilding} from {@link TowerBuilding}
	 */
	public HashMap<String, Resource> getSaleBuilding() {
		return saleBuilding;
	}

	/**
	 * @return a {@link Coin} that represent the discount useful to take a
	 *         {@link CardCharacter} from {@link TowerCharacter}
	 */
	public Coin getSaleCharacter() {
		return saleCharacter.get("Coin");
	}

	/**
	 * @return a {@link HashMap} with the discount useful to take a
	 *         {@link CardVenture} from {@link TowerVenture}
	 */
	public HashMap<String, ResourceAbstract> getSaleVenture() {
		return saleVenture;
	}

	/**
	 * @return string useful to print all accumulators
	 */
	private String accString() {
		StringBuilder str = new StringBuilder();
		if (accumulator.size() > 0) {
			str.append("Accumulator: \n");
			for (String type : accumulator.keySet()) {
				str.append("  " + accumulator.get(type).getQuantity() + " " + type + "\n");
			}
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all discounts player have while is taking
	 *         a {@link CardBuilding} from {@link TowerBuilding}
	 */
	private String saleBuildingString() {
		StringBuilder str = new StringBuilder();
		if (saleBuilding.size() > 0) {
			str.append("SaleBuilding: \n");
			for (String type : saleBuilding.keySet()) {
				str.append("  " + saleBuilding.get(type).getQuantity() + " " + type + "\n");
			}
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all discounts player have while is taking
	 *         a {@link CardCharacter} from {@link TowerCharacter}
	 */
	private String saleCharacterString() {
		StringBuilder str = new StringBuilder();
		if (saleCharacter.size() > 0) {
			str.append("SaleCharacter: \n");
			for (String type : saleCharacter.keySet()) {
				str.append("  " + saleCharacter.get(type).getQuantity() + " " + type + "\n");
			}
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all discounts player have while is taking
	 *         a {@link CardVenture} from {@link TowerVenture}
	 */
	private String saleVentureString() {
		StringBuilder str = new StringBuilder();
		if (saleVenture.size() > 0) {
			str.append("Sale Venture: \n");
			for (String type : saleVenture.keySet()) {
				str.append("  " + saleVenture.get(type).getQuantity() + " " + type + "\n");
			}
		}

		return str.toString();
	}

	/**
	 * @return string useful to print all discounts player have
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(accString() + saleBuildingString() + saleCharacterString() + saleVentureString());
		return str.toString();
	}

}
