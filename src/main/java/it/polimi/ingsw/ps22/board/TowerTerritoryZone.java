package board;

import player.Player;

public class TowerTerritoryZone extends TowerZone {
	
	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}

}
