package it.polimi.ingsw.ps22.server.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import it.polimi.ingsw.ps22.client.main.ClientInterface;
import it.polimi.ingsw.ps22.server.controller.Controller;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;
import it.polimi.ingsw.ps22.server.parser.InputPlayerDataSaxParser;

public class Server extends UnicastRemoteObject implements ServerRMI {

	private static final long serialVersionUID = 1L;
	private static final int PORT = 12345;
	private static final int TIMER = 5000;
	private ServerSocket serverSocket;
	private ExecutorService executor = Executors.newFixedThreadPool(128);
	private Map<String, Connection> waitingConnection = new HashMap<>();
	private Map<Model, ArrayList<RemoteView>> playingConnection = new HashMap<Model, ArrayList<RemoteView>>();
	private Timer timer;
	private HashMap<String, UserData> login;

	private class Task extends TimerTask {
		@Override
		public void run() {
			startGame();
		}
	}
	
	public boolean login(String username, String pass){
		if(pass.equals(login.get(username).getPassword())){
			return true;
		}else{
			return false;
		}
	}

	public synchronized void rednezvous(Connection c, String name) {
		ArrayList<String> players = getCurrentPlayer();
		if (!players.contains(name)) {
			waitingConnection.put(name, c);
			c.send(name);
			if (waitingConnection.size() == 2) {
				timer.schedule(new Task(), TIMER);
			}
			if (waitingConnection.size() == 4) {
				startGame();
			}
		} else {
			c.send(name);
			reconnected(c, name);
		}
	}

	private void reconnected(Connection c, String name) {
		for (Model el : playingConnection.keySet()) {
			if (el.getIsActive()) {
				for (RemoteView client : playingConnection.get(el)) {
					if (client.getUsername().equals(name)) {
						client.setConnection(c);
					}
				}
			}
		}
	}

	private void removeEndGame() {
		for (Model el : playingConnection.keySet()) {
			if (!el.getIsActive()) {
				/*ArrayList<RemoteView> temp = playingConnection.remove(el);
				for (RemoteView user : temp) {
					// salva le classifiche
				}*/
			}
		}
	}

	private ArrayList<String> getCurrentPlayer() {
		ArrayList<String> players = new ArrayList<String>();
		for (Model el : playingConnection.keySet()) {
			if (el.getIsActive()) {
				for (RemoteView client : playingConnection.get(el)) {
					players.add(client.getUsername());
				}
			}
		}
		return players;
	}

	public void startGame() {
		if (waitingConnection.size() > 1) {
			timer.cancel();
			timer = new Timer();
			ArrayList<RemoteView> temp = new ArrayList<RemoteView>();
			Model model = new Model();
			ModelView modelView = new ModelView();
			Controller controller = new Controller(model);
			model.addObserver(modelView);
			for (String el : waitingConnection.keySet()) {
				Connection con = waitingConnection.get(el);
				RemoteView player = new RemoteView(el, con);
				model.addPlayers(el);
				modelView.addObserver(player);
				player.addObserver(controller);
				temp.add(player);
			}
			playingConnection.put(model, temp);
			waitingConnection.clear();
			model.startGame();
			removeEndGame();
		}
	}

	public Server() throws IOException {
		super(0);
		timer = new Timer();
		login = new HashMap<String, UserData>();
		InputPlayerDataSaxParser.PlayerRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/UserData.xml",
				login);
		this.serverSocket = new ServerSocket(PORT);
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Registry già presente!");
		}
	}

	public void run() {
		try {
			Naming.rebind("Server", this);
		} catch (RemoteException | MalformedURLException e1) {
			System.out.println("Errore di connessione!");
		}
		while (true) {
			try {
				Socket newSocket = serverSocket.accept();
				ConnectionSocket connection = new ConnectionSocket(newSocket, this);
				executor.submit(connection);
			} catch (IOException e) {
				System.out.println("Errore di connessione!");
			}
		}
	}

	public ConnectionRMIinterface createRMI(ClientInterface connection) {
		ConnectionRMI con = new ConnectionRMI(connection, this);
		executor.submit(con);
		try {
			ConnectionRMIinterface temp = (ConnectionRMIinterface) UnicastRemoteObject.exportObject(con, 0);
			return temp;
		} catch (RemoteException e) {
			System.out.println("erroe creazione");
		}
		return null;
	}

	public static void main(String[] args) {
		Server server;
		try {
			server = new Server();
			server.run();
		} catch (IOException e) {
			System.err.println("Impossibile inizializzare il server: " + e.getMessage() + "!");
		}
	}

}
