package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

/**
 * This class represents abstract graphics technology that is implemented by
 * {@link GraphicCLI} and {@link GraphicGUI}
 *
 */
public abstract class Graphic {

	protected VisitorB visitor;
	protected RequestMove requestMove;

	public Graphic(VisitorB visitor, RequestMove requestMove) {
		this.visitor = visitor;
		this.requestMove = requestMove;
	}

	public Graphic() {

	}

	/**
	 * This method manages the requests made by the server by going to request
	 * information from the client
	 * 
	 */
	public void getAnswer(GenericMessage arg) {
		arg.accept(visitor);
	}

	/**
	 * This method asks the player what action they want to perform
	 */

	public void getMove() {
		requestMove.requestMove();
	}

	/**
	 * This method draws the current status of the game
	 * 
	 * @param model
	 *            to draw
	 */
	public abstract void printModel(Model model);
}
