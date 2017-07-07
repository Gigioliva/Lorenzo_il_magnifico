package it.polimi.ingsw.ps22.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import it.polimi.ingsw.ps22.server.message.AskUsername;
import it.polimi.ingsw.ps22.server.message.CloseGame;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.RankingMessage;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;;

/**
 * This class represents a concrete Connection implemented using Socket technology
 */

public class ConnectionSocket extends Connection {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Server server;
	private String name;
	private boolean active = false;

	public ConnectionSocket(Socket socket, Server server) {
		this.socket = socket;
		this.server = server;

	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			do {
				send(new AskUsername());
				send(new RankingMessage(server.getRank()));
				AnswerUsername answer=(AnswerUsername) in.readObject();
				name = answer.getUsername();
				String pass=answer.getPassword();
				boolean reg=answer.getReg();
				if(server.login(name, pass, reg)){
					if(answer.getNumPlayer()==4){
						server.lobbyFour(this, name);
						active=true;
					}
					if(answer.getNumPlayer()==5){
						server.lobbyFive(this, name);
						active=true;	
					}
				}
			} while (!active);
			while (active) {
				setChanged();
				notifyObservers(in.readObject());
			}
		} catch (IOException | NoSuchElementException | ClassNotFoundException e) {
			System.err.println("Errore!");
		}
	}

	@Override
	public void send(Object obj) {
		try {
			out.reset();
			out.writeObject(obj);
			out.flush();
		} catch (IOException e) {
			System.out.println("Errore nell'invio messaggio");
		}

	}

	public synchronized void closeConnection() {
		GenericMessage mex = new GenericMessage();
		mex.setString("Connessione terminata!");
		send(mex);
		try {
			socket.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	public void close() {
		active = false;
		send(new CloseGame());
		closeConnection();
		System.out.println("Deregistro il client!");
	}

	public void setActive() {
		active = true;
	}

}
