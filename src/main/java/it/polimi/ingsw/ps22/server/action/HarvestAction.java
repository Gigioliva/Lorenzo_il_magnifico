package it.polimi.ingsw.ps22.server.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
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

	/**
	 *it performs the {@link HarvestAction}. So it will apply to the {@link Player} all the effects of the {@link CardTerritory} 
	 *that he can afford according to the action value, added servants and eventual {@link PermanentEffect}.
	 *It requires the effect not to interact with the player.
	 * @param player the {@link Player} that performs the action
	 * @param servants the number of {@link Servant} to increment the action value
	 * @param model that represent the state of the game.
	 */
	@Override
	public void applyAction(Player player, int servants, Model model) {
		ArrayList<DevelopmentCard> cards = player.getDevelopmentCard("Territory");
		int bonus = player.getBonusAcc().getBonus("IncrementHarvest").getQuantity() + servants;
		player.getSpecificResource("Servant").subResource(new Servant(servants));
		for(DevelopmentCard card: cards){
			ArrayList<ActionEffect> effects = card.getActionEffects();
			if(card.getActionValue() <= super.getActionValue() + bonus){
				for(ActionEffect effect: effects){
						effect.performEffect(player, model);
				}
			}
		}
		if(1 <= super.getActionValue() + bonus){
			player.getPersonalBoard().applyPersonalBoardBonus("Harvest", player, model);
		}
	}
	

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Harvest Action with action value: " + super.getActionValue() + "\n");
		return str.toString();
	}
	
}
