package board;

import player.Player;

public class MarketZone extends Zone {

	private static final int NUM_SPACES=4;
	private MarketSpace[] marketSpace;

	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}

}
