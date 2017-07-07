package it.polimi.ingsw.ps22.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import it.polimi.ingsw.ps22.client.main.ClientInterface;

public interface ServerRMI extends Remote {

	/**
	 * Create a new {@link ConnectionRMI} exporting it with
	 * {@link UnicastRemoteObject}
	 * 
	 * @param connection
	 *            that represents the client
	 * 
	 * @return a {@link ConnectionRMIinterface} that allows server communication
	 */

	public ConnectionRMIinterface createRMI(ClientInterface connection) throws RemoteException;

}
