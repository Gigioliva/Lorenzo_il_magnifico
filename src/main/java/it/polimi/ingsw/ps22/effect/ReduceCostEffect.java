package it.polimi.ingsw.ps22.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Resource;

public class ReduceCostEffect implements PermanentEffect {
	 private HashMap<String,Resource> bonus;  //accedere alla classe bonus di player e aggiungere

	 public ReduceCostEffect(){
		 bonus=new HashMap<String,Resource>();
	 }
	 
	 public void addBonus(String type,Resource value){
		 bonus.put(type, value);
	 }
	 
	@Override
	public void performEffect(Player player, Board board) {
		
	}
	

}
