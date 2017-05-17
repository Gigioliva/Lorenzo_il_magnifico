package it.polimi.ingsw.ps22.player;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.resource.*;

public class BonusAcc {
	private HashMap<String,BonusAbstract> accumulator;
	
	public BonusAcc(){
		accumulator=new HashMap<String,BonusAbstract>();
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
		accumulator.put("IncrementDiceBlack", new IncrementDiceBlack(0));
		accumulator.put("IncrementDiceWhite", new IncrementDiceWhite(0));
		accumulator.put("IncrementDiceOrange", new IncrementDiceOrange(0));
	}
	
	public BonusAbstract getBonus(String type){
		return accumulator.get(type);
	}
	
	public void addBonus(HashMap<String,BonusAbstract> bonus){
		ArrayList<String> temp=new ArrayList<String>(bonus.keySet());
		for(String el: temp){
			this.accumulator.get(el).addResource(bonus.get(el));;
		}
	}

}
