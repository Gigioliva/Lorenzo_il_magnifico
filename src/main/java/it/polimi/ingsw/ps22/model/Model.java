package it.polimi.ingsw.ps22.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.message.MessageAsk;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.VictoryPoint;

public class Model extends Observable {
	private Board board;
	private int turn;
	private int giro;
	private HashMap<String, Player> players;
	private ArrayList<String> orderedPlayers;
	private String playerGame;
	private ArrayList<MessageAsk> waitAnswer; //quando arriva la risposta la risetto a false
	private static Ask ask;

	public Model() {
		board = new Board();
		this.players = new HashMap<String, Player>();
		ask = new Ask();
		MessageAsk.setModel(this);
		this.waitAnswer=new ArrayList<MessageAsk>();
	}

	public Board getBoard() {
		return this.board;
	}

	public int getTurn() {
		return turn;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void addPlayers(String username) {
		Player player = new Player(username);
		this.players.put(username, player);
	}

	public String getPlayerGame() {
		return playerGame;
	}

	public void startGame() {
		board.setZone(players.size());
		orderedPlayers = new ArrayList<String>(players.keySet());
		playerGame = orderedPlayers.get(0);
		for (int i = 0; i < orderedPlayers.size(); i++) {
			players.get(orderedPlayers.get(i)).addSpecificResource("Coin", new Coin(5 + i));
		}
		turn = 1;
		board.reset(turn, new ArrayList<Player>(players.values()));
		giro = 1;
		notifyModel();
	}
	
	public void notifyModel(){
		if(waitAnswer.isEmpty()){
			setChanged();
			notifyObservers();
		}
	}

	public void nextPlayer() {
		int i;
		if (giro != 5) {
			for (i = 0; i < orderedPlayers.size(); i++) {
				if (playerGame == orderedPlayers.get(i)) {
					if (i != orderedPlayers.size() - 1) {
						playerGame = orderedPlayers.get(i + 1);
						if (giro == 1 && players.get(playerGame).getSpecBonus().returnBool("SkipFirstMove")) {
							nextPlayer();
						}
						return;
					}
				}
			}
			if (i == orderedPlayers.size()) {
				newGiro();
			}
		} else {
			for (i = 0; i < orderedPlayers.size(); i++) {
				if (playerGame == orderedPlayers.get(i) && i < orderedPlayers.size() - 1
						&& players.get(orderedPlayers.get(i + 1)).getSpecBonus().returnBool("SkipFirstMove")) {
					playerGame = orderedPlayers.get(i + 1);
					return;
				}
			}
			if (i == orderedPlayers.size()) {
				newGiro();
				return;
			}
		}
	}

	private void newGiro() {
		if (giro < 4) {
			giro++;
			playerGame = orderedPlayers.get(0);
		}
		if (giro == 4) {
			giro++;
			int i;
			boolean find = false;
			for (i = 0; i < orderedPlayers.size() && !find; i++) {
				if (players.get(orderedPlayers.get(i)).getSpecBonus().returnBool("SkipFirstMove")) {
					playerGame = orderedPlayers.get(i);
					return;
				}
			}
			if (i == orderedPlayers.size()) {
				newTurn();
			}
		}
		if (giro == 5) {
			newTurn();
		}
	}

	private void newTurn() {
		ArrayList<Family> council = board.getCouncilPalace().getFamilies();
		ArrayList<String> newOrder = new ArrayList<String>();
		for (Family el : council) {
			String player = el.getPlayer().getUsername();
			if (!newOrder.contains(player)) {
				newOrder.add(player);
			}
		}
		for (String el : this.orderedPlayers) {
			if (!newOrder.contains(el)) {
				newOrder.add(el);
			}
		}
		this.orderedPlayers = newOrder;
		playerGame = newOrder.get(0);
		turn++;
		board.reset(turn, new ArrayList<Player>(players.values()));
		if (turn > 6) {
			EndGame();
		}

	}

	private void EndGame() {
		for (String el : players.keySet()) { // occhio a quelli che devono
												// essere eseguiti per primi
			players.get(el).applyEndEffects(board);
		}
		winMilitaryPoint();
		for (String el : players.keySet()) {
			players.get(el).calcVicPoint();
		}
		winGame();
	}

	private void winMilitaryPoint() {
		HashMap<Integer, Player> temp = new HashMap<Integer, Player>();
		temp.put(1, null);
		temp.put(2, null);
		temp.put(3, null);
		int i = 0;
		for (String el : players.keySet()) {
			if (players.get(el).getSpecificResource("MilitaryPoint").getQuantity() >= i) {
				temp.put(3, temp.get(2));
				temp.put(2, temp.get(1));
				temp.put(1, players.get(el));
				i = players.get(el).getSpecificResource("MilitaryPoint").getQuantity();
			}
		}
		if (temp.get(1).getSpecificResource("MilitaryPoint").getQuantity() == temp.get(2)
				.getSpecificResource("MilitaryPoint").getQuantity()) {
			temp.get(1).addPoints("VictoryPoint", new VictoryPoint(5));
			temp.get(2).addPoints("VictoryPoint", new VictoryPoint(5));
		} else {
			temp.get(1).addPoints("VictoryPoint", new VictoryPoint(5));
			if (temp.get(2).getSpecificResource("MilitaryPoint").getQuantity() == temp.get(3)
					.getSpecificResource("MilitaryPoint").getQuantity()) {
				temp.get(2).addPoints("VictoryPoint", new VictoryPoint(2));
				temp.get(3).addPoints("VictoryPoint", new VictoryPoint(2));
			} else {
				temp.get(2).addPoints("VictoryPoint", new VictoryPoint(2));
			}
		}
	}

	private Player winGame() { // scegliere se usarlo nel model per salvarlo o
								// nelle vare view
		Player player = null;
		int i = 0;
		for (String el : orderedPlayers) {
			if (players.get(el).getSpecificResource("VictoryPoint").getQuantity() > i) {
				player = players.get(el);
				i = players.get(el).getSpecificResource("VictoryPoint").getQuantity();
			}
		}
		return player;
	}

	public static Ask getAsk() {
		return ask;
	}

	public void notifyAsk(MessageAsk ask) {
		waitAnswer.add(ask);
		setChanged();
		notifyObservers(ask);
	}

}
