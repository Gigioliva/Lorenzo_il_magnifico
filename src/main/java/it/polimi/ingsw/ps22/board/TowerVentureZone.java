package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;

public class TowerVentureZone extends TowerZone {

	public TowerVentureZone(Board board) {
		super(board);
		
	}

	@Override
	public boolean Control(Player player, int actionSpace, Family family) {
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementVenture").getQuantity();
		if ((towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkActionValue(towerSpaces[actionSpace], family, actionValue)
				&& checkResources(player, towerSpaces[actionSpace])) {
			towerSpaces[actionSpace].addFamily(family);
			if (!(player.getSpecBonus().returnBool("NoGainTowers")
					&& (towerSpaces[actionSpace].getPlan() == 3 || towerSpaces[actionSpace].getPlan() == 4))) {
				towerSpaces[actionSpace].applyBonus(player);
			}
			if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
				player.getSpecificResource("Coin").subResource(new Coin(3));
			}
			Ask ask=Model.getProva();
			ArrayList<RequisiteCost> possibleCost=towerSpaces[actionSpace].getCard().getAffordableCosts(player);
			int choice=ask.askCostVenture(possibleCost);
			towerSpaces[actionSpace].getCard().applyCostToPlayer(player, possibleCost.get(choice));
			towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
			towerSpaces[actionSpace].getCard().loadEndEffects(player, board);
			player.getDevelopmentCard("Venture").add(towerSpaces[actionSpace].getCard());
			return true;
		}
		return false;
	}

}
