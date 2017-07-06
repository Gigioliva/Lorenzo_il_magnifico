package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.player.Family;

/**
 * Abstraction of all moves to take a {@link CardDevelopment} from
 * {@link TowerDevelopment}
 */
public abstract class TowerMove extends FamilyMove {

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
	public TowerMove(String username, Color color, int floor, int numServant) {
		super(username, color, floor, numServant);
	}

}
