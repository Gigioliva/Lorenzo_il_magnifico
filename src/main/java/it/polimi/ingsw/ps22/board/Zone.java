package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Servant;

public abstract class Zone {

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
		if (numServant > 0 && numServant <= player.getSpecificResource("Servant").getQuantity()) {
			if ((player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant / 2) > actionSpace.getActionCost())) {
				player.getResources().get("Servant").subResource(new Servant((numServant / 2) * 2));
				family.incrementValue(numServant / 2);
				return true;
			}
			if (!(player.getSpecBonus().returnBool("DoubleServant"))
					&& ((actionValue + numServant) > actionSpace.getActionCost())) {
				player.getResources().get("Servant").subResource(new Servant(numServant));
				family.incrementValue(numServant);
				return true;
			}
			return false;
		} else{
			return false;
		}
	}

	public void setZone(int num) {

	}
}
