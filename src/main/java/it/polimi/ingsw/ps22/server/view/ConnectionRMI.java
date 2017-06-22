package it.polimi.ingsw.ps22.server.view;

import java.rmi.RemoteException;
import it.polimi.ingsw.ps22.client.main.ClientInterface;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;
import it.polimi.ingsw.ps22.server.message.AskUsername;
import it.polimi.ingsw.ps22.server.message.CloseGame;
import it.polimi.ingsw.ps22.server.message.GenericMessage;

public class ConnectionRMI extends Connection implements ConnectionRMIinterface {

	private Server server;
	private boolean active=false;
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

	public void join(String pass) {
		if(server.login(name, pass)){
			server.rednezvous(this, name);
			active=true;
		} else{
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
				String pass=((AnswerUsername) obj).getPassword();
				join(pass);
			}
		}
	}

	@Override
	public void setActive() {
		active = true;
	}
	
	public synchronized void closeConnection() {	
		GenericMessage mex=new GenericMessage();
		mex.setString("Connessione terminata!");
		send(mex);
		connection=null;
		active = false;
	}
	
	public void close() {
		send(new CloseGame());
		closeConnection();		
		System.out.println("Deregistro il client!");
	}

}
