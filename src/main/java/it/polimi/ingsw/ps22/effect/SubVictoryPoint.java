package it.polimi.ingsw.ps22.effect;

import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.BonusAbstract;

public class SubVictoryPoint implements EndEffect {
	private HashMap<String, BonusAbstract> weights;
	private String loc; // rappresenta se Player o tipo carta
	
	

	public SubVictoryPoint(String loc) {
		weights=new HashMap<String, BonusAbstract>();
		this.loc = loc;
	}
	
	public void addBonus(String type,BonusAbstract value){
		 weights.put(type, value);
	 }


	@Override
	public void performEffect(Player player, Board board) {
		
	}

}
