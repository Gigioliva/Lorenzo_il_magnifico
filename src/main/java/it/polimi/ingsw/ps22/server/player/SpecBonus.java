package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.HashMap;

public class SpecBonus implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String,Boolean> bonus;
	
	public SpecBonus(){
		bonus=new HashMap<String,Boolean>();
		bonus.put("NoMarket",false);
		bonus.put("DoubleServant",false);		
		bonus.put("SkipFirstMove",false);
		bonus.put("GainTowers",false);
		bonus.put("OccupiedSpace",false);
		bonus.put("NoCostTower",false);
		bonus.put("Neutral+3",false);
		bonus.put("AllFamilyCol5",false);
		bonus.put("FamilyCol+2",false);
		bonus.put("PointVicChurch+5",false);
		bonus.put("NoTerritoryReq",false);
		bonus.put("DoubleGain",false);
	}
	
	@Override
	public SpecBonus clone(){
		SpecBonus temp=new SpecBonus();
		for(String el: bonus.keySet()){
			temp.bonus.put(el, this.bonus.get(el));
		}
		return temp;
	}
	
	public void setSpecBonus(String type){
		bonus.remove(type);
		bonus.put(type, new Boolean(true));
	}
	
	public boolean returnBool(String type){
		return bonus.get(type);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("You have the following bonus/malus \n");
		
		for(String type: bonus.keySet()){
			if(bonus.get(type))
				str.append("  " + type + "\n");
		}
		
		return str.toString();
	}

}
