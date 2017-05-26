package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.action.HarvestAction;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class HarvestZone extends Zone {

	private static final int NUM_SPACES = 2;
	private HarvestSpace[] harvestSpace;

	public HarvestZone() {
		harvestSpace = new HarvestSpace[NUM_SPACES];
		harvestSpace[0] = new HarvestSpace(1, false);
		harvestSpace[1] = new HarvestSpace(1, true);
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player=family.getPlayer();
		int actionValue=family.getValue() + player.getBonusAcc().getBonus("IncrementHarvest").getQuantity();
		if (!(family.isPlaced()) && harvestSpace[actionSpace].isPlayable() && (harvestSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkAllSpace(player) && checkActionValue(numServant, harvestSpace[actionSpace], family, actionValue)) {
			harvestSpace[actionSpace].addFamily(family);
			actionValue=family.getValue();
			if (harvestSpace[actionSpace].getMulti()) {
				actionValue=actionValue-3;
			} 
			HarvestAction harvestAction = new HarvestAction(actionValue);
			harvestAction.applyAction(player);
			return true;
		}
		return false;
	}

	private boolean checkAllSpace(Player player) {
		boolean control = true;
		ArrayList<Family> allFamily = new ArrayList<Family>();
		for (int i = 0; i < NUM_SPACES; i++) {
			allFamily.addAll(harvestSpace[i].getFamilies());
		}
		for (Color el : Color.values()) {
			if (el != Color.NEUTRAL)
				control = control && allFamily.contains(player.getFamily(el));
		}
		return control;
	}
	
	@Override
	public void reset() {
		for (int i = 0; i < NUM_SPACES; i++) {
			harvestSpace[i].resetFamily();
		}
	}
	
	@Override
	public void setZone(int num){
		if(num<3){
			harvestSpace[1].setNotPlayable();
		}
		
	}

}
