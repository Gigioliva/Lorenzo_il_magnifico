package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class CouncilMove extends FamilyMove {

	public CouncilMove(String username, Color color, int numServant) {
		super(username, color, 1, numServant);
	}

	@Override
	public void applyMove(Model model) {
		Player player = model.getPlayers().get(username);
		Family family = player.getFamily(color);
		if (model.getBoard().getCouncilPalace().Control(numServant, family)) {
			model.getBoard().getCouncilPalace().applyMove(numServant, family);
			model.notifyModel();
		} else {
			ErrorMove error = new ErrorMove();
			model.notifyError(error);
		}
	}
}
