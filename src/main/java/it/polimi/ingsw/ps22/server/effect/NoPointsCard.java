package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;;

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

	@Override
	public void performEffect(Player player, Model model) {
		ArrayList<EndEffect> endPlayer=player.getEndEffects();
		for(int i=0; i<player.getDevelopmentCard(cardType).size(); i++){
			DevelopmentCard temp=player.getDevelopmentCard(cardType).remove(i);
			for(EndEffect el: temp.getEndEffect()){
				if(endPlayer.contains(el)){
					endPlayer.remove(el);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("No victory points given by your " + cardType + "\n");
		return str.toString();
	}

}
