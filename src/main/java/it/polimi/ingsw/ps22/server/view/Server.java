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
	private Map<String, Connection> waitingFour = new HashMap<>();
	private Map<String, Connection> waitingFive = new HashMap<>();
	private Map<Model, ArrayList<RemoteView>> playingConnection = new HashMap<Model, ArrayList<RemoteView>>();
	private Timer timerFour;
	private Timer timerFive;
	private HashMap<String, UserData> login;

	private class TaskFour extends TimerTask {
		@Override
		public void run() {
			startGameFour();
		}
	}
	
	private class TaskFive extends TimerTask {
		@Override
		public void run() {
			startGameFive();
		}
	}
	
	public boolean login(String username, String pass){
		if(pass.equals(login.get(username).getPassword())){
			return true;
		}else{
			return false;
		}
	}

	public synchronized void lobbyFour(Connection c, String name) {
		ArrayList<String> players = getCurrentPlayer();
		if (!players.contains(name) && !waitingFour.containsKey(name) && !waitingFive.containsKey(name)) {
			waitingFour.put(name, c);
			c.send(name);
			if (waitingFour.size() == 2) {
				timerFour.schedule(new TaskFour(), TIMER);
			}
			if (waitingFour.size() == 4) {
				startGameFour();
			}
		} else {
			c.send(name);
			reconnected(c, name);
		}
	}
	
	public synchronized void lobbyFive(Connection c, String name) {
		ArrayList<String> players = getCurrentPlayer();
		if (!players.contains(name) && !waitingFour.containsKey(name) && !waitingFive.containsKey(name)) {
			waitingFive.put(name, c);
			c.send(name);
			if (waitingFive.size() == 2) {
				timerFive.schedule(new TaskFive(), TIMER);
			}
			if (waitingFive.size() == 4) {
				startGameFive();
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

	private void startGameFour() {
		if (waitingFour.size() > 1) {
			timerFour.cancel();
			timerFour = new Timer();
			ArrayList<RemoteView> temp = new ArrayList<RemoteView>();
			Model model = new Model();
			ModelView modelView = new ModelView();
			Controller controller = new Controller(model);
			model.addObserver(modelView);
			for (String el : waitingFour.keySet()) {
				Connection con = waitingFour.get(el);
				RemoteView player = new RemoteView(el, con);
				model.addPlayers(el);
				modelView.addObserver(player);
				player.addObserver(controller);
				temp.add(player);
			}
			playingConnection.put(model, temp);
			waitingFour.clear();
			model.startGame();
			removeEndGame();
		}
	}
	
	private void startGameFive() {
		if (waitingFive.size() > 1) {
			timerFive.cancel();
			timerFive = new Timer();
			ArrayList<RemoteView> temp = new ArrayList<RemoteView>();
			Model model = new Model();
			ModelView modelView = new ModelView();
			Controller controller = new Controller(model);
			model.addObserver(modelView);
			for (String el : waitingFive.keySet()) {
				Connection con = waitingFive.get(el);
				RemoteView player = new RemoteView(el, con);
				model.addPlayers(el);
				modelView.addObserver(player);
				player.addObserver(controller);
				temp.add(player);
			}
			playingConnection.put(model, temp);
			waitingFive.clear();
			model.startGame();
			removeEndGame();
		}
	}

	public Server() throws IOException {
		super(0);
		timerFour = new Timer();
		timerFive = new Timer();
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
