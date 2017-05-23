package it.polimi.ingsw.ps22.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.player.Family;
import it.polimi.ingsw.ps22.player.Player;

public class Model extends Observable {
	private Board board;
	private int turn;
	private int giro;
	private HashMap<String, Player> players;
	private ArrayList<String> orderedPlayers;
	private String playerGame;
	private static Ask ask;

	public Model() {
		board = new Board();
		this.players = new HashMap<String, Player>();
		ask = new Ask();
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
		turn = 1;
		board.reset(turn, new ArrayList<Player>(players.values()));
		giro = 1;
		setChanged();
		notifyObservers();
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
			// termina partita
			// per ogni giocatore devo applicare gli endeffect, confertire le
			// carte in punti, convertire le risorse in punti, controllare chi ha più punti militari
		}

	}

	public static Ask getAsk() {
		return ask;
	}

}
