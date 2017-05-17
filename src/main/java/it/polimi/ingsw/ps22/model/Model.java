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
	
	public Model(){
		
	}
	
	public Board getBoard(){
		return this.board;
	}
	//get & aggiorna turno
	

}
