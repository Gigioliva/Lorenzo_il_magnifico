package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.player.Player;

public class HarvestZone extends Zone {

	private static final int NUM_SPACES=4;
	private HarvestSpace[] harvestSpace;

	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}

}
