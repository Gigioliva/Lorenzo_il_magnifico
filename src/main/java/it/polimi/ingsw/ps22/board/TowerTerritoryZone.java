package it.polimi.ingsw.ps22.board;


import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;

public class TowerTerritoryZone extends TowerZone {
	
	public TowerTerritoryZone(Board board) {
		super(board);
	}

	@Override
	public boolean Control(Player player, int actionSpace, Family family) {
		int actionValue = family.getValue() + player.getBonusAcc().getBonus("IncrementTerritory").getQuantity();
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
			towerSpaces[actionSpace].getCard().applyImmediateEffects(player, board);
			player.getDevelopmentCard("CardTerritory").add(towerSpaces[actionSpace].getCard());
			towerSpaces[actionSpace].removeCard();
			return true;
		}
		return false;
	}

}
