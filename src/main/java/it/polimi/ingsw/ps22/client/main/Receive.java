package it.polimi.ingsw.ps22.client.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Observable;

public class Receive extends Observable implements Runnable {
	private Socket client;
	private ObjectInputStream input;

	public Receive(Socket client) {
		this.client = client;
		try {
			input = new ObjectInputStream(this.client.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Object temp = input.readObject();
				setChanged();
				notifyObservers(temp);
			} catch (ClassNotFoundException | IOException e) {
			}
		}
	}

}
