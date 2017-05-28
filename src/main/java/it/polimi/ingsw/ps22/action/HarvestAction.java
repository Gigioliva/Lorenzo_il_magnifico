package it.polimi.ingsw.ps22.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.player.Player;

public class HarvestAction extends Action {

	public HarvestAction(int actionValue) {
		super(actionValue);
	}

	//suppongo che gli effetti delle carte territorio vengano eseguiti tutti, in quanto in genere sono solo di tipo GainResource
	@Override
	public void applyAction(Player player, Board board) {
		ArrayList<DevelopmentCard> cards = player.getDevelopmentCard("Territory");
		int bonus = player.getBonusAcc().getBonus("IncrementHarvest").getQuantity();
		for(DevelopmentCard card: cards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			if(card.getActionValue() <= super.getActionValue() + bonus){
				for(ActionEffect effect: effects){
						effect.performEffect(player, board);
				}
			}
		}
		player.getPersonalBoard().applyPersonalBoardBonus("Harvest", player, board);
	}
	

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Harvest Action with action value: " + super.getActionValue() + "\n");
		return str.toString();
	}
	
}
