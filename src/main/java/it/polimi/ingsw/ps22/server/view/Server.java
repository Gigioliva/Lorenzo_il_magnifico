package it.polimi.ingsw.ps22.server.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import it.polimi.ingsw.ps22.client.main.ClientInterface;
import it.polimi.ingsw.ps22.server.controller.Controller;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;
import it.polimi.ingsw.ps22.server.parser.InputPlayerDataSaxParser;
import it.polimi.ingsw.ps22.server.parser.OutputPlayerDataDomParser;
import it.polimi.ingsw.ps22.server.parser.TimerSaxParser;

/**
 * This class represents the Server that creates and manages all player
 * connections and keeps all current and finished matches in memory, as well as
 * player statistics and their login data.
 *
 */
public class Server extends UnicastRemoteObject implements ServerRMI {

	private static final long serialVersionUID = 1L;
	private static final int PORT = 12345;
	private static int TIMER;
	private static final int TIMER_SAVE = 180000;
	private transient ServerSocket serverSocket;
	private transient ExecutorService executor = Executors.newFixedThreadPool(128);
	private transient HashMap<String, Connection> waitingFour = new HashMap<>();
	private transient HashMap<String, Connection> waitingFive = new HashMap<>();
	private HashMap<Model, ArrayList<RemoteView>> playingConnection = new HashMap<Model, ArrayList<RemoteView>>();
	private HashMap<Model, ArrayList<RemoteView>> savePlaying = new HashMap<Model, ArrayList<RemoteView>>();
	private transient Timer timerFour;
	private transient Timer timerFive;
	private transient Timer timerSave;
	private HashMap<String, UserData> login;

	/**
	 * This class represents a Task that will begin after the expiration of time
	 * by launching games with at least 2 players and paused for a certain time.
	 */
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

	private class TaskSave extends TimerTask {
		@Override
		public void run() {
			removeEndGame();
			saveGame();

		}
	}

	/**
	 * This method allows you to log in by checking the entered data or
	 * registering a new player by verifying the uniqueness of the username
	 * 
	 * @param username
	 *            of the player logging in
	 * @param pass
	 *            of the player logging in
	 * @param reg
	 *            flag indicating if the player wants to register or make a
	 *            simple login
	 * @return boolean that indicates whether or not the procedure was
	 *         successful
	 */

	public synchronized boolean login(String username, String pass, boolean reg) {
		if (reg == false) {
			if (login.containsKey(username) && pass.equals(login.get(username).getPassword())) {
				System.out.println("Trovato utente");
				return true;
			} else {
				return false;
			}
		} else {
			if (!login.containsKey(username)) {
				login.put(username, new UserData(pass));
				OutputPlayerDataDomParser.PlayerDataWrite(
						"src/main/java/it/polimi/ingsw/ps22/server/parser/resources/UserData.xml", login);
				System.out.println("Trovato utente");
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * This method allows the player to enter the lobby for 4-player games by
	 * starting the timer if the number of players is 2 or the game if the
	 * maximum number of players (4) in the lobby is reached
	 * 
	 * @param c
	 *            player connection entered the lobby
	 * @param name
	 *            player name entered lobby
	 */

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
			if (players.contains(name)) {
				c.send(name);
				reconnected(c, name);
			}
		}
	}

	/**
	 * This method allows the player to enter the lobby for 5-player games by
	 * starting the timer if the number of players is 2 or the game if the
	 * maximum number of players (5) in the lobby is reached
	 * 
	 * @param c
	 *            player connection entered the lobby
	 * @param name
	 *            player name entered lobby
	 */

	public synchronized void lobbyFive(Connection c, String name) {
		ArrayList<String> players = getCurrentPlayer();
		if (!players.contains(name) && !waitingFour.containsKey(name) && !waitingFive.containsKey(name)) {
			waitingFive.put(name, c);
			c.send(name);
			if (waitingFive.size() == 2) {
				timerFive.schedule(new TaskFive(), TIMER);
			}
			if (waitingFive.size() == 5) {
				startGameFive();
			}
		} else {
			if (players.contains(name)) {
				c.send(name);
				reconnected(c, name);
			}
		}
	}

	/**
	 * This method reconnects a player disconnected to a match in progress or to
	 * a saved match
	 * 
	 * @param c
	 *            player connection entered the lobby
	 * @param name
	 *            player name entered lobby
	 */
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
		Model temp = searchGameForPlayer(name);
		if (temp != null) {
			ModelView modelView = new ModelView();
			Controller controller = new Controller(temp);
			temp.addObserver(modelView);
			ArrayList<RemoteView> players = savePlaying.remove(temp);
			for (RemoteView user : players) {
				user.setInactive();
				modelView.addObserver(user);
				user.addObserver(controller);
				if (user.getUsername().equals(name)) {
					user.setConnection(c);
				}
			}
			playingConnection.put(temp, players);
		}
	}

	/**
	 * This method searches for a saved game based on the name of a player who
	 * is part of it
	 * 
	 * @param name
	 *            of the player to search the game
	 * @return a {@link Model} corresponding to the match searched
	 */

	private Model searchGameForPlayer(String name) {
		for (Model el : savePlaying.keySet()) {
			if (el.getIsActive()) {
				for (RemoteView client : savePlaying.get(el)) {
					if (client.getUsername().equals(name)) {
						return el;
					}
				}
			}
		}
		return null;
	}

	/**
	 * This method removes completed matches by updating the player rankings
	 */

	private void removeEndGame() {
		for (Model el : playingConnection.keySet()) {
			if (!el.getIsActive()) {
				ArrayList<RemoteView> temp = playingConnection.remove(el);
				for (RemoteView user : temp) {
					login.get(user.username).setNumPlayedGame(1 + login.get(user.username).getNumPlayedGame());
					if (el.getPlayerGame().equals(user.username)) {
						login.get(user.username).setNumVictory(1 + login.get(user.username).getNumVictory());
					}
				}
			}
		}
		for (Model el : savePlaying.keySet()) {
			if (!el.getIsActive()) {
				ArrayList<RemoteView> temp = savePlaying.remove(el);
				for (RemoteView user : temp) {
					login.get(user.username).setNumPlayedGame(1 + login.get(user.username).getNumPlayedGame());
					if (el.getPlayerGame().equals(user.username)) {
						login.get(user.username).setNumVictory(1 + login.get(user.username).getNumVictory());
					}
				}
			}
		}
	}

	/**
	 * This method searches for all present players in saved and not finished
	 * matches
	 * 
	 * @return a {@link ArrayList} containing players username
	 */
	private ArrayList<String> getCurrentPlayer() {
		ArrayList<String> players = new ArrayList<String>();
		for (Model el : playingConnection.keySet()) {
			if (el.getIsActive()) {
				for (RemoteView client : playingConnection.get(el)) {
					players.add(client.getUsername());
				}
			}
		}
		for (Model el : savePlaying.keySet()) {
			if (el.getIsActive()) {
				for (RemoteView client : savePlaying.get(el)) {
					players.add(client.getUsername());
				}
			}
		}
		return players;
	}

	/**
	 * This method starts a game with a maximum number of 4 players by stopping
	 * the timer and putting all lobbyFour players into a new game
	 */

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
		}
	}

	/**
	 * This method starts a game with a maximum number of 5 players by stopping
	 * the timer and putting all lobbyFive players into a new game
	 */

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
		}
	}

	public HashMap<String, UserData> getRank() {
		return login;
	}

	/**
	 * This method saves the current status of matches in a local file so that
	 * it can be reloaded to the next server startup.
	 */

	private void saveGame() {
		OutputPlayerDataDomParser
				.PlayerDataWrite("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/UserData.xml", login);
		FileOutputStream out;
		ObjectOutputStream output;
		try {
			HashMap<Model, ArrayList<RemoteView>> temp = new HashMap<>();
			for (Model el : playingConnection.keySet()) {
				if (el.getPlayerGame() != null || el.getIsActive() == false) {
					temp.put(el, playingConnection.get(el));
				}
			}
			temp.putAll(savePlaying);
			out = new FileOutputStream("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/SavePlaying.ser");
			output = new ObjectOutputStream(out);
			output.writeObject(temp);
			output.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Errore salvataggio");
		}
	}

	/**
	 * Create an instance of {@link Server} by reading from the file the waiting
	 * time to start the games and any saved games; Then creates a
	 * {@link ServerSocket} and initializes a Registry on port 1099 to create
	 * RMI connections
	 */

	public Server() throws IOException {
		super(0);
		TIMER = TimerSaxParser.ServerTimer();
		timerFour = new Timer();
		timerFive = new Timer();
		timerSave = new Timer();
		login = new HashMap<String, UserData>();
		InputPlayerDataSaxParser.PlayerRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/UserData.xml",
				login);
		try {
			FileInputStream in = new FileInputStream(
					"src/main/java/it/polimi/ingsw/ps22/server/parser/resources/SavePlaying.ser");
			ObjectInputStream input = new ObjectInputStream(in);
			@SuppressWarnings("unchecked")
			HashMap<Model, ArrayList<RemoteView>> readObject = (HashMap<Model, ArrayList<RemoteView>>) input
					.readObject();
			savePlaying = readObject;
			input.close();
			in.close();
		} catch (FileNotFoundException | ClassNotFoundException e) {
			System.out.println("Nessuna partita salvata");
		}
		this.serverSocket = new ServerSocket(PORT);
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Registry già presente!");
		}
		timerSave.schedule(new TaskSave(),0, TIMER_SAVE);
	}

	/**
	 * Register the server in the Registry to accept RMI connections and put the
	 * ServerSocket in contact whit new Socket connections
	 */

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

	@Override
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
