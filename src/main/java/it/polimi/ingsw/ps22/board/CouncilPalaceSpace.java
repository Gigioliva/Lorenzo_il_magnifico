package it.polimi.ingsw.ps22.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.action.ProductionAction;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;

public class CouncilPalaceSpace extends ActionSpace {
	private final static int ACTIONCOST = 1;

	public CouncilPalaceSpace() {
		super(ACTIONCOST, true);
		HashMap<String, ResourceAbstract> bonus = new HashMap<String, ResourceAbstract>();
		bonus.put("Coin", new Coin(1));
		bonus.put("CouncilPrivilege", new CouncilPrivilege(1));
		super.addBonus(bonus);
	}

	public boolean Control(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		int actionValue = family.getValue();
		if (!(family.isPlaced()) && this.isPlayable()
				&& (this.controlPlacement() || player.getSpecBonus().returnBool("OccupiedSpace"))
				&& checkActionValue(numServant, family, actionValue)) {
			return true;
		}
		return false;
	}
	
	private boolean checkActionValue(int numServant, Family family,int  actionValue){
		Player player = family.getPlayer();
		if (numServant > 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant / 2) > ACTIONCOST)) {
				return true;
			}
			if (!(player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant) > ACTIONCOST)) {
				return true;
			}
		}
		return false;
	}
	
	public void applyMove(int numServant, int actionSpace, Family family) {
		Player player = family.getPlayer();
		applyServant(family, numServant);
		this.addFamily(family);
		this.applyBonus(player);
	}
	
	private void applyServant(Family family, int numServant) {
		Player player = family.getPlayer();
		if (player.getSpecBonus().returnBool("DoubleServant")) {
			player.getResources().get("Servant").subResource(new Servant((numServant / 2) * 2));
			family.incrementValue(numServant / 2);
		} else {
			player.getResources().get("Servant").subResource(new Servant(numServant));
			family.incrementValue(numServant);
		}
	}
}
