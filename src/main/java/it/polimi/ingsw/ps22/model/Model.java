package it.polimi.ingsw.ps22.model;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;

public class Model {
	private Board board;
	private int turn;
	private HashMap<String, Player> players;
	private ArrayList<Player> orderedPlayers;
	private static Ask ask;
	
	public Model(){
		// lettura da file del tracciato punti fede
		// FaithPointTrack temp = new FaithPointTrack();
		// this.board = new Board(temp);
		int turn=1;
		this.players = new HashMap<>();
		this.orderedPlayers = new ArrayList<>();
		ask=new Ask();
	}
	
	public Board getBoard(){
		return this.board;
	}

	public int getTurn() {
		return turn;
	}

	public HashMap<String, Player> getPlayers() {
		return players;
	}

	public void setPlayers(HashMap<String, Player> players) {
		this.players = players;
	}

	public ArrayList<Player> getOrderedPlayers() {
		return orderedPlayers;
	}
	
	public Player getNextPlayer() {
		return orderedPlayers.get(0);
	}

	public void setOrderedPlayers(ArrayList<Player> orderedPlayers) {
		this.orderedPlayers = orderedPlayers;
	}
	
	public static Ask getProva(){
		return ask;
	}

}
