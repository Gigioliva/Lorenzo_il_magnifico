package it.polimi.ingsw.ps22.server.view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import it.polimi.ingsw.ps22.server.message.AskUsername;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;;

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
				AskUsername mex=new AskUsername();
				send(mex);
				name = ((AnswerUsername)in.readObject()).getAnswer();
				System.out.println(name);
				server.rednezvous(this, name);
			}while(!active);
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
	public void send(Object obj) {
		try {
			out.writeObject(obj);
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
