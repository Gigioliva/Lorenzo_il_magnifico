package board;

import player.Player;

public class ProductionZone extends Zone{

	private static final int NUM_SPACES=2;
	
	private ProductionSpace[] productionSpace;

	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}
	

}
