package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.message.ErrorMove;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * Move that permit to place a {@link Family} to the {@link ProductionZone}
 *
 */
public class ProductionMove extends FamilyMove {

	private static final long serialVersionUID = 1L;

	/**
	 * @param username
	 *            is the username of the player which is creating the move
	 * @param color
	 *            is the color of the {@link Family}
	 * @param space
	 *            is the space in which the player identified by username which
	 *            want to place the familiar
	 * @param numServant
	 *            is the number of servant to add to improve the action value
	 */
	public ProductionMove(String username, Color color, int space, int numServant) {
		super(username, color, space, numServant);
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
			if (model.getBoard().getProdZone().Control(numServant, space, family)) {
				model.getBoard().getProdZone().applyMove(numServant, space, family);
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
