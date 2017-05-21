package it.polimi.ingsw.ps22.board;

import it.polimi.ingsw.ps22.model.Ask;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Servant;

public abstract class Zone {

	public boolean Control(Player player, int actionSpace, Family family) {
		return false;
	} // Controlla che in quella zona il giocatore non abbia
		// piazzato altri famigliari colorati o che abbia
		// sufficienti risorse per prende la carta nella torre (Override)

	public void reset() {

	}
	
	public void reset(int turn) {

	}

	protected boolean checkActionValue(ActionSpace actionSpace, Family family, int actionValue) {
		Ask ask=Model.getProva();
		int servant = ask.askServant();
		Player player=family.getPlayer();
		if ((player.getSpecBonus().returnBool("DoubleServant"))
				&& ((actionValue + servant / 2) > actionSpace.getActionCost())) {
			player.getResources().get("Servant").subResource(new Servant((servant / 2) * 2));
			family.incrementValue(servant / 2);
			return true;
		}
		if (!(player.getSpecBonus().returnBool("DoubleServant"))
				&& ((actionValue + servant) > actionSpace.getActionCost())) {
			player.getResources().get("Servant").subResource(new Servant(servant));
			family.incrementValue(servant);
			return true;
		}
		return false;
	}
}
