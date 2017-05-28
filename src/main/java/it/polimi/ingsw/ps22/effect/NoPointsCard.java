package it.polimi.ingsw.ps22.effect;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class NoPointsCard implements EndEffect {
	private String cardType;
	

	public NoPointsCard(String cardType) {
		this.cardType = cardType;
	}


	//forse rimuovere non è la cosa migliore da fare
	@Override
	public void performEffect(Player player, Board board) { //cancella le carte cardType
		for(int i=0; i<player.getDevelopmentCard(cardType).size(); i++)
			player.getDevelopmentCard(cardType).remove(i);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("End Effect: no victory points given by your " + cardType);
		return str.toString();
	}

}
