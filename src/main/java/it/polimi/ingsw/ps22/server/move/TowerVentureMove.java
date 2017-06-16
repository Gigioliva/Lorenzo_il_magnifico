package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class TowerVentureMove extends TowerMove {

	private static final long serialVersionUID = 1L;

	public TowerVentureMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}

	@Override
	public void applyMove(Model model) {
		if (canFamilyMove(model)) {
			Player player = model.getPlayers().get(username);
			Family family = player.getFamily(color);
			if (model.getBoard().getTower("Venture").Control(numServant, space, family)) {
				model.getBoard().getTower("Venture").placeFamily(numServant, space, family);
				model.getBoard().getTower("Venture").takeCard(space, player);
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
