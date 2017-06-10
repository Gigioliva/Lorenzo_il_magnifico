package it.polimi.ingsw.ps22.server.view;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionRMIinterface extends Remote {
	
	public void receive(Object obj) throws RemoteException;

}
