package it.polimi.ingsw.ps22.player;

import java.util.HashMap;

public class SpecBonus {
	private HashMap<String,Boolean> bonus;
	
	public SpecBonus(){
		bonus=new HashMap<String,Boolean>();
		bonus.put("NotMarket",false);
		bonus.put("DoubleServant",false);
		bonus.put("SkipFirstMove",false);
		bonus.put("GainTowers",false);
	}
	
	public void setSpecBonus(String type){
		bonus.remove(type);
		bonus.put(type, new Boolean(true));
	}
	
	public boolean returnBool(String type){
		return bonus.get(type);
		
	}

}
