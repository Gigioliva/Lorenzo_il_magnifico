package it.polimi.ingsw.ps22.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps22.client.main.ClientInterface;

public interface ServerRMI extends Remote {
	
	public ConnectionRMIinterface createRMI(ClientInterface connection) throws RemoteException;

}
