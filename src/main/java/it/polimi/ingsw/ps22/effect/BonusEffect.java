package it.polimi.ingsw.ps22.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.BonusAbstract;

public class BonusEffect implements PermanentEffect {
	 private HashMap<String,BonusAbstract> bonus;  //accedere alla classe bonus di player e aggiungere

	 public BonusEffect(){
		 bonus=new HashMap<String,BonusAbstract>();
	 }
	 
	 public void addBonus(String type,BonusAbstract value){
		 bonus.put(type, value);
	 }
	 
	@Override
	public void performEffect(Player player, Board board) {
		player.getBonusAcc().addBonus(bonus);
	}

	
}
