package it.polimi.ingsw.ps22.server.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TowerBuildingZone extends TowerZone {

	public TowerBuildingZone(Board board) {
		super(board);
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementBuilding").getQuantity();
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
	
	public void takeCard(int actionSpace, Player player){
		towerSpaces[actionSpace].getCard().applyCostToPlayer(player);
		towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
		player.getDevelopmentCard("Building").add(towerSpaces[actionSpace].getCard());
		towerSpaces[actionSpace].removeCard();
	}
	
	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount){
		applyDiscount(player, "Building", discount);
		takeCard(actionSpace, player);
		deApplyDiscount(player, "Building", discount);
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Building Tower \n" +  super.toString());
		return str.toString();
	}

}
