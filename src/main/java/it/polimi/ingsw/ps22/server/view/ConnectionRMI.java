package it.polimi.ingsw.ps22.server.view;

import java.rmi.RemoteException;

import it.polimi.ingsw.ps22.client.main.ClientInterface;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;
import it.polimi.ingsw.ps22.server.message.AskUsername;

public class ConnectionRMI extends Connection implements ConnectionRMIinterface {

	private Server server;
	private boolean active;
	private String name;
	private ClientInterface connection;

	public ConnectionRMI(ClientInterface connection, Server server) {
		this.server = server;
		this.connection = connection;
	}

	@Override
	public void run() {
		AskUsername mex = new AskUsername();
		send(mex);
	}

	@Override
	public void send(Object obj) {
		try {
			connection.sendToClient(obj);
		} catch (RemoteException e) {
			System.out.println("Errore invio messaggio");
		}
	}

	public void join() {
		server.rednezvous(this, name);
		if (!active) {
			run();
		} else{
			send(name);
		}
	}

	public void receive(Object obj) {
		if (!(obj instanceof AnswerUsername)) {
			setChanged();
			notifyObservers(obj);
		} else {
			name = ((AnswerUsername) obj).getAnswer();
			join();
		}
	}

	@Override
	public void setActive() {
		active = true;
	}

}
