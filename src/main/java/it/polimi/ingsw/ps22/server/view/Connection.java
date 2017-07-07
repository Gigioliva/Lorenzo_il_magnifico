package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;

/**
 * This class represents an abstract Connection that is implemented by both
 * {@link ConnectionSocket} and {@link ConnectionRMI}
 *
 */
public abstract class Connection extends Observable implements Runnable {

	/**
	 * This method receives an object to be sent to the player
	 * 
	 * @param obj
	 *            the {@link Object} to be sent to the player
	 */

	public abstract void send(Object obj);

	/**
	 * This method sets the connection as active
	 */

	public abstract void setActive();

	/**
	 * This method closes the Connection and setting the flag isActive
	 * to false.
	 */

	public abstract void close();

}
