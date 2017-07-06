package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * Create a move to take a {@link CardVenture} from the {@link TowerVenture}
 */
public class TowerVentureMove extends TowerMove {

	private static final long serialVersionUID = 1L;

	/**
	 * @param username
	 *            is the username of the player which is creating the move
	 * @param color
	 *            is the color of the {@link Family}
	 * @param floor
	 *            is the floor in which the player identified by username which
	 *            want to place the familiar
	 * @param numServant
	 *            is the number of servant to add to improve the action value
	 */
	public TowerVentureMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}

	/**
	 * Apply the move which call this method to the model, if is possible
	 * 
	 * @param model
	 *            is the model to which apply the move
	 */
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
