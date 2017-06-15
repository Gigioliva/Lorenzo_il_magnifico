package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public abstract class Zone implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public boolean Control(int numServant, int actionSpace, Family family) {
		return false;
	} // Controlla che in quella zona il giocatore non abbia
		// piazzato altri famigliari colorati o che abbia
		// sufficienti risorse per prende la carta nella torre (Override)

	public void reset() {

	}

	public void reset(int turn) {

	}
	
	//return true if a specific family member can't be placed in a space of a zone
	public boolean cantPlaceSpace(int numServants, int actionSpace, Family family){
		return !Control(numServants, actionSpace, family);
	}
	
	public boolean cantPlaceZone(Player player){
		return false;
	}

	protected boolean checkActionValue(int numServant, ActionSpace actionSpace, Family family, int actionValue) {
		Player player = family.getPlayer();
		if (numServant >= 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant / 2) >= actionSpace.getActionCost())) {
				System.out.println("prova2 zone");
				return true;
			}
			if (!(player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant) >= actionSpace.getActionCost())) {
				return true;
			}
		}
		return false;
	}

	protected void applyServant(Family family, int numServant) {
		Player player = family.getPlayer();
		if (player.getSpecBonus().returnBool("DoubleServant")) {
			player.getResources().get("Servant").subResource(new Servant((numServant / 2) * 2));
			family.incrementValue(numServant / 2);
		} else {
			player.getResources().get("Servant").subResource(new Servant(numServant));
			family.incrementValue(numServant);
		}
	}

	public void setZone(int num) {

	}
	
	@Override 
	public abstract Zone clone();
}
