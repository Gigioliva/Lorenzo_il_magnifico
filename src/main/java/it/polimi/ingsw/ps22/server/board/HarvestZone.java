package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.action.HarvestAction;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

/**
 * 
 * The harvest zone is the zone containing the {@link HarvestSpace}s. It is responsible
 * for applying the {@link HarvestAction}
 *
 */
public class HarvestZone extends Zone {

	private static final long serialVersionUID = 1L;
	private static final int NUM_SPACES = 2;
	private HarvestSpace[] harvestSpace;

	/**
	 * It instantiates a new HarvestZone with two spaces
	 * @param model
	 */
	public HarvestZone(Model model) {
		super(model);
		harvestSpace = new HarvestSpace[NUM_SPACES];
		harvestSpace[0] = new HarvestSpace(1, false, 1);
		harvestSpace[1] = new HarvestSpace(1, true, 0);
	}
	
	public HarvestZone clone(ArrayList<Family> family) {
		HarvestZone temp = new HarvestZone(null);
		temp.harvestSpace[0] = this.harvestSpace[0].clone(family);
		temp.harvestSpace[1] = this.harvestSpace[1].clone(family);
		return temp;
	}
	
	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player=family.getPlayer();
		int actionValue=family.getValue() + player.getBonusAcc().getBonus("IncrementHarvest").getQuantity();
		if (0<=actionSpace && actionSpace<=NUM_SPACES && !(family.isPlaced()) && harvestSpace[actionSpace].isPlayable() && (harvestSpace[actionSpace].controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& (family.getColor()==Color.NEUTRAL || checkAllSpace(player)) && checkActionValue(numServant, harvestSpace[actionSpace], family, actionValue)) {
			return true;
		}
		return false;
	}
	
	/**
	 * It applies the move to the player by decrementing the used {@link Servant}s, adding the family to the space,
	 * applying eventual specific effects and performing a {@link HarvestAction}
	 * @param numServant the {@link Servant}s to increase the action value
	 * @param actionSpace the specific {@link ActionSpace}
	 * @param family the {@link Family} to be placed
	 */
	public void applyMove(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		harvestSpace[actionSpace].addFamily(family);
		int actionValue=family.getValue();
		if (harvestSpace[actionSpace].getMulti()) {
			actionValue=actionValue-3;
		} 
		HarvestAction harvestAction = new HarvestAction(actionValue);
		harvestAction.applyAction(player, 0, model);
	}

	private boolean checkAllSpace(Player player) {
		boolean control = true;
		ArrayList<Family> allFamily = new ArrayList<Family>();
		for (int i = 0; i < NUM_SPACES; i++) {
			allFamily.addAll(harvestSpace[i].getFamilies());
		}
		for (Color el : Color.values()) {
			if (el != Color.NEUTRAL)
				control = control && !allFamily.contains(player.getFamily(el));
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
		if(num==5){
			harvestSpace[0].setFivePlayer();
		}
		
	}
	
	public HarvestSpace[] getHarvestSpaces(){
		return this.harvestSpace;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Harvest Zone \n");
		for(HarvestSpace harv: harvestSpace){
			str.append(harv.toString());
		}
		
		return str.toString();
	}

}
