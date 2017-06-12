package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class CouncilMove extends FamilyMove {

	private static final long serialVersionUID = 1L;

	public CouncilMove(String username, Color color, int numServant) {
		super(username, color, 1, numServant);
	}

	@Override
	public void applyMove(Model model) {
		if(canFamilyMove(model)){
			Player player = model.getPlayers().get(username);
			Family family = player.getFamily(color);
			if (model.getBoard().getCouncilPalace().Control(numServant, family)) {
				model.getBoard().getCouncilPalace().applyMove(numServant, family);
				model.setCantFamilyMove();
				model.notifyModel();
				return;
			}
		}
		ErrorMove error = new ErrorMove();
		model.notifyMessage(error);
		model.notifyModel();
	}
}
