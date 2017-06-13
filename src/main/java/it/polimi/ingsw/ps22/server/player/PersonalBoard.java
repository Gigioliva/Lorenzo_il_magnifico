package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.GainResource;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class PersonalBoard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String pathname;
	private HashMap<String, GainResource> personalBonus;
	private HashMap<Integer, VictoryPoint> bonusCharacter;
	private HashMap<Integer, MilitaryPoint> requirementHarvest;
	private HashMap<Integer, VictoryPoint> bonusHarvest;
	
	@Override
	public PersonalBoard clone() {
		PersonalBoard temp = new PersonalBoard();
		temp.pathname=this.pathname;
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
		this.pathname=path;
	}
	
	public void setPersonalBonus(HashMap<String, GainResource> persBonus){
		personalBonus = persBonus;
	}
	
	public void setBonusCharacter(HashMap<Integer, VictoryPoint> bonusChar){
		bonusCharacter = bonusChar;
	}

	public void setRequirementHarvest(HashMap<Integer, MilitaryPoint> requHarvest){
		requirementHarvest = requHarvest;
	}
	
	public void setBonusHarvest(HashMap<Integer, VictoryPoint> bonusHarv){
		bonusHarvest = bonusHarv;
	}
	
	public HashMap<String, GainResource> getPersonalBonus(){
		return personalBonus;
	}
	
	public HashMap<Integer, VictoryPoint> getBonusCharacter(){
		return bonusCharacter;
	}

	public HashMap<Integer, MilitaryPoint> getRequirementHarvest(){
		return requirementHarvest;
	}
	
	public HashMap<Integer, VictoryPoint> getBonusHarvest(){
		return bonusHarvest;
	}
	
	public void applyPersonalBoardBonus(String actionType, Player player){
		personalBonus.get(actionType).performEffect(player);	
	}
	
	private String bonusCharString(){
		StringBuilder str = new StringBuilder();
		str.append("Characters end victory points: \n");
		for(Integer index: bonusCharacter.keySet()){
			str.append("  " + "position: " + index + " victory points: " + bonusCharacter.get(index).getQuantity() + "\n");
		}
		
		return str.toString();
	}
	
	private String bonusHarvestString(){
		StringBuilder str = new StringBuilder();
		str.append("Territory end victory points: \n");
		for(Integer index: bonusHarvest.keySet()){
			str.append("  " + "position: " + index + " victory points: " + bonusHarvest.get(index).getQuantity() + "\n");
		}
		
		return str.toString();
	}
	
	private String reqHarvestString(){
		StringBuilder str = new StringBuilder();
		str.append("Requirements for terriory cards: \n");
		for(Integer index: bonusHarvest.keySet()){
			str.append("  " + "position: " + index + " military points required: " + requirementHarvest.get(index).getQuantity() + "\n");
		}
		
		return str.toString();
	}
	
	private String personalBonusString(){
		StringBuilder str = new StringBuilder();
		str.append("Personal bonus: \n");
		for(String type: personalBonus.keySet()){
			str.append(type + "\n" + personalBonus.get(type).toString() + "\n");
		}
		
		return str.toString();
		
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Personal Board: \n" + bonusHarvestString() + reqHarvestString() + bonusCharString() + personalBonusString());
		return str.toString();
	}
	
	
}
