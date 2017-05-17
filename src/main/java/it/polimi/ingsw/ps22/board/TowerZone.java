package board;

import player.Player;
import java.util.ArrayList;
import java.util.HashMap;
import card.DevelopmentCard;

public class TowerZone extends Zone {
	
	private static final int NUM_SPACES=4;
	private TowerSpace[] towerSpaces;
	private boolean occupied;
	private HashMap<Integer, ArrayList<DevelopmentCard>> cards;

	@Override
	public boolean Control(Player player, int actionSpace, int actionValue) {
		return false;
	}

}
