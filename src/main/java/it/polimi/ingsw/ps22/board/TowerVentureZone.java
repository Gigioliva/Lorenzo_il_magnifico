package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class TowerVentureZone extends TowerZone {

	public TowerVentureZone(Board board) {
		super(board);
		//leggi da file e carica usando addCards le carte nelle torri

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
			Ask ask = Model.getAsk();
			ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
			ArrayList<HashMap<String, ResourceAbstract>> cost=new ArrayList<HashMap<String, ResourceAbstract>>();
			for(RequisiteCost el: possibleCost){
				cost.add(el.getCost());
			}
			int choice = ask.askCosts(cost);
			towerSpaces[actionSpace].getCard().applyCostToPlayer(player, possibleCost.get(choice));
			towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
			towerSpaces[actionSpace].getCard().loadEndEffects(player, board);
			player.getDevelopmentCard("Venture").add(towerSpaces[actionSpace].getCard());
			towerSpaces[actionSpace].removeCard();
			return true;
		}
		return false;
	}

}
