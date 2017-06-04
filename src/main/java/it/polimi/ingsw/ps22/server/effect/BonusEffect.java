package it.polimi.ingsw.ps22.server.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.BonusAbstract;

public class BonusEffect implements PermanentEffect {
	 private HashMap<String,BonusAbstract> bonus;  //accedere alla classe bonus di player e aggiungere

	 public BonusEffect(){
		 bonus=new HashMap<String,BonusAbstract>();
	 }
	 
	 public void addBonus(String type,BonusAbstract value){
		 bonus.put(type, value);
	 }
	 
	@Override
	public void performEffect(Player player) {
		player.getBonusAcc().addBonus(bonus);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(String type: bonus.keySet()){
			str.append(bonus.get(type).getQuantity() + " " + type + "\n");
		}
		
		return str.toString();
	}

	
}
