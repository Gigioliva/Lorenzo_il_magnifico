package it.polimi.ingsw.ps22.move;

import it.polimi.ingsw.ps22.message.ErrorMove;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class TowerCharacterMove extends TowerMove {

	public TowerCharacterMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}

	@Override
	public void applyMove(Model model) {
		if (canFamilyMove(model)) {
			Player player = model.getPlayers().get(username);
			Family family = player.getFamily(color);
			if (model.getBoard().getTower("Character").Control(numServant, space, family)) {
				model.getBoard().getTower("Character").placeFamily(numServant, space, family);
				model.getBoard().getTower("Character").takeCard(space, family);
				model.notifyModel();
			} else {
				ErrorMove error = new ErrorMove();
				model.notifyMessage(error);
			}
		} else {
			ErrorMove error = new ErrorMove();
			model.notifyMessage(error);
		}
	}
}
