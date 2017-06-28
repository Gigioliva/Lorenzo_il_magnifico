package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

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

	/**
	 * It performs the effect so that the {@link VictoryPoint} that the player should earn at the
	 * end of the game because of the cards of cardType will not be given.
	 * @param player affected by the effect
	 * @param model that represent the state of the game
	 */
	@Override
	public void performEffect(Player player, Model model) {
		ArrayList<EndEffect> endPlayer=player.getEndEffects();
		int size = player.getDevelopmentCard(cardType).size();
		for(int i=0; i<size; i++){
			DevelopmentCard temp=player.getDevelopmentCard(cardType).remove(0);
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
