package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.player.Player;

public class TowerCharacterZone extends TowerZone {
	
	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}

}
