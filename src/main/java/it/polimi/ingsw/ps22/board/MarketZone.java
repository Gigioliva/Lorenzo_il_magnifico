package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class MarketZone extends Zone {
	private static final int NUM_SPACES = 4;
	private MarketSpace[] marketSpace;

	public MarketZone() {
		marketSpace = new MarketSpace[NUM_SPACES];
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i] = new MarketSpace(1, false);
		}
		// leggere da file i bonus degli spazi azione
	}

	@Override
	public boolean Control(Player player, int actionSpace, Family family) {
		int actionValue = family.getValue();
		if (marketSpace[actionSpace].isPlayable()
				&& (marketSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& player.getSpecBonus().returnBool("NotMarket")
				&& checkActionValue(marketSpace[actionSpace], family, actionValue)) {
			marketSpace[actionSpace].addFamily(family);
			marketSpace[actionSpace].applyBonus(player);
			return true;
		}
		return false;
	}
	
	@Override
	public void reset() {
		for (int i = 0; i < NUM_SPACES; i++) {
			marketSpace[i].resetFamily();
		}
	}

}
