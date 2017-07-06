package it.polimi.ingsw.ps22.server.move;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

/**
 * Abstraction of all the move which include the placement of a {@link Family}
 */
public abstract class FamilyMove extends Move {

	private static final long serialVersionUID = 1L;
	protected Color color;
	protected int space;
	protected int numServant;

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
	public FamilyMove(String username, Color color, int space, int numServant) {
		super(username);
		this.color = color;
		this.space = space - 1;
		this.numServant = numServant;
	}

	protected boolean canFamilyMove(Model model) {
		return model.getCanFamilyMove();
	}

}
