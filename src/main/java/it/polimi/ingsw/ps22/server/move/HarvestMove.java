package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class HarvestMove extends FamilyMove {

	private static final long serialVersionUID = 1L;

	public HarvestMove(String username, Color color, int space, int numServant) {
		super(username, color, space, numServant);
	}

	@Override
	public void applyMove(Model model) {
		if (canFamilyMove(model)) {
			Player player = model.getPlayers().get(username);
			Family family = player.getFamily(color);
			if (model.getBoard().getHarvestZone().Control(numServant, space, family)) {
				model.getBoard().getHarvestZone().applyMove(numServant, space, family);
				model.notifyModel();
				return;
			}
		}
		ErrorMove error = new ErrorMove();
		model.notifyMessage(error);
		model.notifyModel();
	}

}
