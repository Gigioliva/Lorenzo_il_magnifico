package it.polimi.ingsw.ps22.player;


import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class PersonalBoard {
	private HashMap<String, GainResource> personalBonus;
	private HashMap<Integer, VictoryPoint> bonusCharacter;
	private HashMap<Integer, MilitaryPoint> requirementHarvest;
	private HashMap<Integer, VictoryPoint> bonusHarvest;
	
	public PersonalBoard(){
		//setta i vari campi a 0
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
	
	public void applyPersonalBoardBonus(String actionType, Player player,Board board){
		personalBonus.get(actionType).performEffect(player, board);	
	}
	
}
