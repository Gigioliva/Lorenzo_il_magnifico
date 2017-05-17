package it.polimi.ingsw.ps22.player;

import java.util.HashMap;

import it.polimi.ingsw.ps22.resource.BonusAbstract;

public class BonusAcc {
	private HashMap<String,BonusAbstract> accumulator;
	
	public BonusAcc(){
		//setta tutto i bonusAbs a 0
	}
	
	public BonusAbstract getBonus(String type){
		return accumulator.get(type);
	}

}
