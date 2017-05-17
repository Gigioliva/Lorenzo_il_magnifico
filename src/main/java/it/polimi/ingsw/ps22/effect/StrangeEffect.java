package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class StrangeEffect implements PermanentEffect{
	private String type;

	
	public StrangeEffect(String type) {
		this.type = type;
	}

	@Override
	public void performEffect(Player player, Board board) {
		
	}
	

}
