package it.polimi.ingsw.ps22.client.main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {

	public void sendToClient(Object object) throws RemoteException;
	
	//public Object receiveSyn() throws RemoteException;
}
