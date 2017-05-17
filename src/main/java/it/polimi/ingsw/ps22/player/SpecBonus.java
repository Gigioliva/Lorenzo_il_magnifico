package it.polimi.ingsw.ps22.player;

import java.util.HashMap;

public class SpecBonus {
	private HashMap<String,Boolean> bonus;
	//altri bool
	
	public SpecBonus(){
		//setta a null tutti i bool;
	}
	
	public void setSpecBonus(String type){
		bonus.remove(type);
		bonus.put(type, new Boolean(true));
	}
	
	public boolean returnBool(String type){
		return bonus.get(type);
		
	}

}
