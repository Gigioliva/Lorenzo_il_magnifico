package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;

public class TowerBuildingZone extends TowerZone {

	public TowerBuildingZone(Board board) {
		super(board);
	}

	@Override
	public boolean Control(Player player, int actionSpace, Family family) {
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementBuilding").getQuantity();
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
			//pagare il costo
			towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
			player.getDevelopmentCard("Building").add(towerSpaces[actionSpace].getCard());
			return true;
		}
		return false;
	}

}
