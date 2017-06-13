package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.player.Player;

public class NoPointsCard implements EndEffect {
	
	private static final long serialVersionUID = 1L;
	private String cardType;

	public NoPointsCard(String cardType) {
		this.cardType = cardType;
	}
	
	@Override
	public NoPointsCard clone(){
		NoPointsCard temp=new NoPointsCard(this.cardType);
		return temp;
	}


	//forse rimuovere non è la cosa migliore da fare
	@Override
	public void performEffect(Player player) { //cancella le carte cardType
		for(int i=0; i<player.getDevelopmentCard(cardType).size(); i++)
			player.getDevelopmentCard(cardType).remove(i);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("No victory points given by your " + cardType + "\n");
		return str.toString();
	}

}
