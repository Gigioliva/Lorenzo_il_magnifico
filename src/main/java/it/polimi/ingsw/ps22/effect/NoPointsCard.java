package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class NoPointsCard implements EndEffect {
	private String cardType;
	

	public NoPointsCard(String cardType) {
		this.cardType = cardType;
	}


	@Override
	public void performEffect(Player player, Board board) { //cancella le carte cardType
		
	}
	

}
