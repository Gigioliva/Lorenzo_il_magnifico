package it.polimi.ingsw.ps22.server.view;

import java.rmi.RemoteException;
import it.polimi.ingsw.ps22.client.main.ClientInterface;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;
import it.polimi.ingsw.ps22.server.message.AskUsername;
import it.polimi.ingsw.ps22.server.message.CloseGame;
import it.polimi.ingsw.ps22.server.message.GenericMessage;

public class ConnectionRMI extends Connection implements ConnectionRMIinterface {

	private Server server;
	private boolean active = false;
	private String name;
	private ClientInterface connection;

	public ConnectionRMI(ClientInterface connection, Server server) {
		this.server = server;
		this.connection = connection;
	}

	@Override
	public void run() {
		send(new AskUsername());
	}

	@Override
	public void send(Object obj) {
		try {
			connection.sendToClient(obj);
		} catch (RemoteException e) {
			System.out.println("Errore invio messaggio");
		}
	}

	public void join(String pass, int num) {
		if (server.login(name, pass) && (num==4 || num==5)) {
			if (num == 4) {
				server.lobbyFour(this, name);
			}
			if (num == 5) {
				server.lobbyFive(this, name);
			}
			active = true;
		} else {
			run();
		}
	}

	public void receive(Object obj) {
		if (active) {
			if (!(obj instanceof AnswerUsername)) {
				setChanged();
				notifyObservers(obj);
			}
		} else {
			if (obj instanceof AnswerUsername) {
				name = ((AnswerUsername) obj).getUsername();
				join(((AnswerUsername) obj).getPassword(), ((AnswerUsername) obj).getNumPlayer());
			}
		}
	}

	@Override
	public void setActive() {
		active = true;
	}

	public synchronized void closeConnection() {
		GenericMessage mex = new GenericMessage();
		mex.setString("Connessione terminata!");
		send(mex);
		connection = null;
		active = false;
	}

	public void close() {
		send(new CloseGame());
		closeConnection();
		System.out.println("Deregistro il client!");
	}

}
