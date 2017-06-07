package it.polimi.ingsw.ps22.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class ConnectionSocket extends Connection {
	
	private Socket socket;

	private ObjectInputStream in;

	private ObjectOutputStream out;

	private Server server;

	private String name;

	private boolean active = false;

	public ConnectionSocket(Socket socket, Server server) {
		this.socket=socket;
		this.server=server;

	}

	@Override
	public void run() {
		try{
			in = new ObjectInputStream(socket.getInputStream());  //ricordarsi di aprire prima l'output nel client
			out = new ObjectOutputStream(socket.getOutputStream());
			do{
				GenericMessage mex=new GenericMessage();
				mex.setString("Who are you?");
				send(mex);
				name = ((GenericMessage)in.readObject()).getString();
				server.rednezvous(this, name);
			}while(!active);
			out.writeObject(name);
			out.flush();
			while(active){				
				setChanged();
				notifyObservers(in.readObject());
			}			
		} catch (IOException | NoSuchElementException | ClassNotFoundException e) {
			System.err.println("Errore!");
		}finally{
			close();
		}

	}

	@Override
	public void send(GenericMessage message) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			System.out.println("Errore nell'invio messaggio");
		}

	}

	@Override
	public void send(Model model) {
		try {
			out.writeObject(model);
			out.flush();
		} catch (IOException e) {
			System.out.println("Errore nell'invio messaggio");
		}
	}
	
	public synchronized void closeConnection() {	
		GenericMessage mex=new GenericMessage();
		mex.setString("Connessione terminata!");
		send(mex);
		try {
			socket.close();
		} catch (IOException e) {
		}
		active = false;
	}
	
	private void close() {
		closeConnection();		
		System.out.println("Deregistro il client!");
		server.deregisterConnection(this);
	}
	
	public void setActive(){
		active=true;
	}

}
