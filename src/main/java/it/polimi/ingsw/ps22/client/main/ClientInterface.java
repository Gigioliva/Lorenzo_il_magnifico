package it.polimi.ingsw.ps22.client.main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

	/**
	 * This method allows you to receive objects from the server
	 * 
	 * @param obj
	 *            to receive
	 */
	public void sendToClient(Object object) throws RemoteException;
}
