package it.polimi.ingsw.ps22.server.move;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.model.Model;

/**
 * Abstraction of all the moves that is possible to create in this game
 */
public abstract class Move implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String username;

	/**
	 * Abstract constructor of all the moves
	 * 
	 * @param username
	 *            is the username of the player which is creating the move
	 */
	public Move(String username) {
		this.username = username;
	}

	public void applyMove(Model model) {

	}

}
