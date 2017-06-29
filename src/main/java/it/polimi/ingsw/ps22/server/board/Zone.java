package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Servant;

public abstract class Zone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected Model model;
	
	public Zone(Model model){
		this.model=model;
	}

	public boolean Control(int numServant, int actionSpace, Family family) {
		return false;
	} // Controlla che in quella zona il giocatore non abbia
		// piazzato altri famigliari colorati o che abbia
		// sufficienti risorse per prende la carta nella torre (Override)

	public void reset() {

	}

	public void reset(int turn) {

	}

	protected boolean checkActionValue(int numServant, ActionSpace actionSpace, Family family, int actionValue) {
		Player player = family.getPlayer();
		if (numServant >= 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant / 2) >= actionSpace.getActionCost())) {
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
	
	public abstract Zone clone(ArrayList<Family> family);
}
