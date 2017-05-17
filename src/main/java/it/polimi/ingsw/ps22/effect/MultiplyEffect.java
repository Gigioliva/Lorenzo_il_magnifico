package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class MultiplyEffect implements ActionEffect, ImmediateEffect {
	private String multiplier;
	private ResourceAbstract multiplicand;
	
	public MultiplyEffect(String multiplier, ResourceAbstract multiplicand){
		this.multiplicand=multiplicand;
		this.multiplier=multiplier;
	}

	@Override
	public void performEffect(Player player, Board board) {
		
	}

}
