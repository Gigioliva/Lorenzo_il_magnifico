package it.polimi.ingsw.ps22.server.view;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * This class represents the user. it is used to display data to the user
 * and to keep an interaction between this one and the underlying
 * infrastructure.
 */

public class View extends Observable implements Observer, Serializable {

	private static final long serialVersionUID = 1L;
	protected String username;

	/**
	 * It creates an instance of View by setting player username as a
	 * parameter
	 * 
	 * @param username
	 *            of the player
	 */

	public View(String username) {
		this.username = username;
	}

	/**
	 * This method returns the {@link Player}'s username associated with the
	 * current view instance
	 * 
	 * @return a String containing player username 
	 */

	public String getUsername() {
		return username;
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
