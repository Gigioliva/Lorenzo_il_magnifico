package it.polimi.ingsw.ps22.client.main;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Client implements Observer {
	private Socket client;
	private ObjectOutputStream output;

	public Client() {
		try {
			client = new Socket("localhost", 12345);
			output = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receive(ViewClient view) {
		Receive e=new Receive(client);
		e.addObserver(view);
		(new Thread(e)).start();
	}

	public void send(Object ask) {
		try {
			output.writeObject(ask);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof ViewClient && arg !=null){
			send(arg);
		}
	}
	
	public static void main(String[] args) {
		Client cl = new Client();
		ViewClient view=new ViewClient();
		view.addObserver(cl);
		cl.receive(view);
		(new Thread(view)).start();
	}

}
