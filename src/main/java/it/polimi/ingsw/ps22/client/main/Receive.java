package it.polimi.ingsw.ps22.client.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * This class represents a client Connection, used to receive objects, implemented using Socket technology
 */

public class Receive extends Observable implements Runnable {
	private Socket client;
	private ObjectInputStream input;

	public Receive(Socket client) {
		this.client = client;
		try {
			input = new ObjectInputStream(this.client.getInputStream());
		} catch (IOException e) {
			Logger logger = Logger.getLogger(Receive.class.getName());
			logger.info(e.getMessage());
		}
	}

	@Override
	public void run() {
		boolean flag=true;
		while (flag) {
			try {
				Object temp = input.readObject();
				setChanged();
				notifyObservers(temp);
			} catch (ClassNotFoundException e) {
				Logger logger = Logger.getLogger(Receive.class.getName());
				logger.info(e.getMessage());
			} catch(IOException e){
				Logger logger = Logger.getLogger(Receive.class.getName());
				logger.info(e.getMessage());
				flag=false;
			}
		}
	}

}
