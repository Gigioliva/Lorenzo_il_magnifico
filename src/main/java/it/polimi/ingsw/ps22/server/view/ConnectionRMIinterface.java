package it.polimi.ingsw.ps22.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionRMIinterface extends Remote {

	/**
	 * This method allows you to receive objects from the client
	 * 
	 * @param obj
	 *            To receive
	 */
	public void receive(Object obj) throws RemoteException;

}
