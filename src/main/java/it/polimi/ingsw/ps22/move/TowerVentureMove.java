package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class TowerVentureMove extends TowerMove {

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
				model.notifyModel();
			} else {
				ErrorMove error = new ErrorMove();
				model.notifyMessage(error);
			}
		} else{
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
		}
	}

}
