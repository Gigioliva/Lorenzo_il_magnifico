package it.polimi.ingsw.ps22.server.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import it.polimi.ingsw.ps22.server.controller.Controller;
import it.polimi.ingsw.ps22.server.model.Model;

public class Server {
	private static final int PORT = 12345;
	
	private ServerSocket serverSocket;
	
	private ExecutorService executor = Executors.newFixedThreadPool(128);
	
	private List<Connection> connections = new ArrayList<Connection>();
	
	private Map<String, Connection> waitingConnection = new HashMap<>();
	
	private Map<Integer, ArrayList<Connection>> playingConnection = new HashMap<>();
	
	
	/*
	 * Registro una connessione attiva
	 */
	private synchronized void registerConnection(Connection c){
		connections.add(c);
	}
	
	/*
	 * Deregistro una connessione
	 */
	public synchronized void deregisterConnection(Connection c){
		connections.remove(c);
	}
	
	/*
	 * Mi metto in attesa di altri giocatori
	 */
	public synchronized void rednezvous(Connection c, String name){
		if(!waitingConnection.containsKey(name)){
			c.setActive();
			waitingConnection.put(name, c);
			if(waitingConnection.size() == 4){								//impostare un timer se non si arriva a 4
				ArrayList<Connection> temp=new ArrayList<Connection>();
				Model model = new Model();
				Controller controller = new Controller(model);
				for(String el: waitingConnection.keySet()){
					Connection con =waitingConnection.get(el);
					RemoteView player=new RemoteView(el, con);
					model.addPlayers(el);
					model.addObserver(player);
					player.addObserver(controller);
					temp.add(con);	
				}
				playingConnection.put(playingConnection.size(), temp);
				waitingConnection.clear();
				model.startGame();
			}	
		}
	}
	
	public Server() throws IOException {
		this.serverSocket = new ServerSocket(PORT);
	}
	
	public void run(){
		while(true){
			try {
				Socket newSocket = serverSocket.accept();
				Connection connection = new ConnectionSocket(newSocket, this);
				registerConnection(connection);
				executor.submit(connection);				
			} catch (IOException e) {
				System.out.println("Errore di connessione!");
			}
		}
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
