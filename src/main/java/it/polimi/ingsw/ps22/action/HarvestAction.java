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
		ArrayList<DevelopmentCard> cards = player.getDevelopmentCard("CardTerritory");
		for(DevelopmentCard card: cards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			if(card.getActionValue() <= super.getActionValue()){
				for(ActionEffect effect: effects){
						effect.performEffect(player, board);
				}
			}
		}
		
	}

}
