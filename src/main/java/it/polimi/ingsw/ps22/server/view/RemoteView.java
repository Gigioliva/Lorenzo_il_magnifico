package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.server.controller.Controller;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * RemoteView represents the player on the server side. This class observes the
 * {@link Connection} and is observed by the {@link Controller} (considering MVC
 * architecture)
 * 
 *
 */
public class RemoteView extends View implements Observer {

	private static final long serialVersionUID = 1L;
	private transient Connection connection;
	private boolean isActive = true;

	/**
	 * It creates an instance of RemoteView by setting player username as a
	 * parameter and the {@link Connection} to communicate with the player
	 * 
	 * @param username
	 *            of the player
	 * @param connection
	 *            of the player
	 */

	public RemoteView(String username, Connection connection) {
		super(username);
		this.connection = connection;
		connection.addObserver(this);
	}

	/**
	 * This method allows the player to reconnect by creating a new
	 * {@link Connection} and by setting the isActive flag to true
	 * 
	 * @param connection
	 *            the new {@link Connection} of the player
	 */

	public void setConnection(Connection connection) {
		this.connection = connection;
		connection.addObserver(this);
		reconnected();
	}

	private void reconnected() {
		isActive = true;
		setChanged();
		notifyObservers();
	}

	/**
	 * This method receives an object to be sent to the player
	 * 
	 * @param obj
	 *            the {@link Object} to be sent to the player
	 */

	public void send(Object obj) {
		if (isActive)
			connection.send(obj);
	}

	/**
	 * This method disconnects the {@link Player} by closing the
	 * {@link Connection} and setting the flag isActive to false and connection
	 * to null.
	 */

	public void close() {
		setInactive();
		connection.close();
		connection = null;
	}

	public void setInactive() {
		isActive = false;
	}

	/**
	 * This method is an overriding {@link Observer} update method that is used
	 * either to receive objects from the {@link Connection}, forwarding them to
	 * the {@link Controller}, or to receive objects from the {@link ModelView},
	 * forwarding them to the player-client
	 * 
	 * @param o
	 *            represents the observed object
	 * @param arg
	 *            represents the object notified by o
	 */

	@Override
	public void update(Observable o, Object arg) {
		if (isActive) {
			if (o instanceof ModelView && arg == null) {
				Model temp = ((ModelView) o).getModel(username);
				if (temp != null) {
					if (temp.getPlayers().get(username).getConnected()) {
						send(temp);
					} else {
						this.close();
					}
				}
			}
			if (o instanceof ModelView && arg instanceof GenericMessage) {
				GenericMessage mex = ((GenericMessage) arg)
						.accept(new VisitorA(username, ((ModelView) o).getCurrentPlayer()));
				if (mex != null) {
					send(mex);
				}
			}
			if (o instanceof Connection && arg != null) {
				setChanged();
				notifyObservers(arg);
			}
		}
	}

}
