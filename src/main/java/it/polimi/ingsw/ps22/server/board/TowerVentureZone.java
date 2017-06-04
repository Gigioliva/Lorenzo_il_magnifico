package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TowerVentureZone extends TowerZone {
	
	private static final long serialVersionUID = 1L;

	public TowerVentureZone(Board board) {
		super(board);
		// leggi da file e carica usando addCards le carte nelle torri

	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementVenture").getQuantity();
		if (!(family.isPlaced())
				&& (towerSpaces[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkResources(player, towerSpaces[actionSpace])
				&& checkActionValue(numServant, towerSpaces[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}

	public void placeFamily(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		towerSpaces[actionSpace].addFamily(family);
		if (!(player.getSpecBonus().returnBool("NoGainTowers")
				&& (towerSpaces[actionSpace].getPlan() == 3 || towerSpaces[actionSpace].getPlan() == 4))) {
			towerSpaces[actionSpace].applyBonus(player);
		}
		if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
			player.getSpecificResource("Coin").subResource(new Coin(3));
		}
	}

	public void takeCard(int actionSpace, Player player) {
		ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
		if (possibleCost.size() == 1) {
			payCard(0, possibleCost, player, towerSpaces[actionSpace]);
		} else {
			AskCosts ask = new AskCosts(possibleCost, player, towerSpaces[actionSpace]);
			ask.applyAsk();
		}
	}

	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount) {
		applyDiscount(player, "Venture", discount);
		ArrayList<RequisiteCost> possibleCost = towerSpaces[actionSpace].getCard().getAffordableCosts(player);
		if (possibleCost.size() == 1) {
			payCard(0, possibleCost, player, towerSpaces[actionSpace], discount);
		} else {
			AskCosts ask = new AskCosts(possibleCost, player, towerSpaces[actionSpace], discount);
			ask.applyAsk();
		}
	}

	public void payCard(int choice, ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace) {
		towerSpace.getCard().applyCostToPlayer(player, possibleCost.get(choice));
		towerSpace.getCard().applyImmediateEffects(player, board);
		towerSpace.getCard().loadEndEffects(player, board);
		player.getDevelopmentCard("Venture").add(towerSpace.getCard());
		towerSpace.removeCard();
	}

	public void payCard(int choice, ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace,
			HashMap<String, ResourceAbstract> discount) {
		payCard(choice, possibleCost, player, towerSpace);
		deApplyDiscount(player, "Venture", discount);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Venture Tower \n" + super.toString());
		return str.toString();
	}
}
