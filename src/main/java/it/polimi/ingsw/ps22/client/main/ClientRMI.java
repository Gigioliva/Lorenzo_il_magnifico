package it.polimi.ingsw.ps22.client.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.server.view.ConnectionRMIinterface;
import it.polimi.ingsw.ps22.server.view.ServerRMI;

public class ClientRMI extends Observable implements ClientInterface, Observer {
	
	private ConnectionRMIinterface server;
	
	public void addServer(ConnectionRMIinterface server){
		this.server=server;
	}

	@Override
	public void sendToClient(Object object) throws RemoteException {
		setChanged();
		notifyObservers(object);
	}
	
	public void send(Object obj){
		try {
			server.receive(obj);
		} catch (RemoteException e) {
			System.out.println("Errore invio client");
		}
	}
	
	public static void mainRMI() {
		ClientRMI cl = new ClientRMI();
		ViewClient view=new ViewClient();
		view.addObserver(cl);
		cl.addObserver(view);
		(new Thread(view)).start();
		ServerRMI temp=null;
		ClientInterface remoteRef=null;
		try {
			temp=(ServerRMI)Naming.lookup("//localhost/Server");
			remoteRef = (ClientInterface) UnicastRemoteObject.exportObject(cl, 0);
			cl.addServer(temp.createRMI(remoteRef));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println("Errore apertura");
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ViewClient && arg !=null){
			send(arg);
		}
	}

}
