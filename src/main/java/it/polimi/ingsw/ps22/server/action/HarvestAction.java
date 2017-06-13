package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class HarvestAction extends Action {

	private static final long serialVersionUID = 1L;


	public HarvestAction(int actionValue) {
		super(actionValue);
	}
	
	@Override
	public HarvestAction clone() {
		HarvestAction temp=new HarvestAction(this.getActionValue());
		temp.servants=this.servants;
		return temp;
	}

	//suppongo che gli effetti delle carte territorio vengano eseguiti tutti, in quanto in genere sono solo di tipo GainResource
	@Override
	public void applyAction(Player player, int servants) {
		ArrayList<DevelopmentCard> cards = player.getDevelopmentCard("Territory");
		int bonus = player.getBonusAcc().getBonus("IncrementHarvest").getQuantity() + servants;
		player.getSpecificResource("Servants").subResource(new Servant(servants));
		for(DevelopmentCard card: cards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			if(card.getActionValue() <= super.getActionValue() + bonus){
				for(ActionEffect effect: effects){
						effect.performEffect(player);
				}
			}
		}
		if(1 <= super.getActionValue() + bonus){
			player.getPersonalBoard().applyPersonalBoardBonus("Harvest", player);
		}
	}
	

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Harvest Action with action value: " + super.getActionValue() + "\n");
		return str.toString();
	}
	
}
