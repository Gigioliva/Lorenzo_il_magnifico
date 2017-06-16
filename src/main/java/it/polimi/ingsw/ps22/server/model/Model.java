package it.polimi.ingsw.ps22.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskLeader;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

//per ora è serializable poi quando si fa il ModelView no

public class Model extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Board board;
	private int turn;
	private int giro;
	private HashMap<String, Player> players;
	private ArrayList<String> orderedPlayers;
	private String playerGame;
	private transient boolean canFamilyMove;
	private transient ArrayList<MessageAsk> waitAnswer;
	private transient HashMap<Player, ArrayList<CardLeader>> cardLeaderStart;

	public Model() {
		board = new Board();
		this.players = new HashMap<String, Player>();
		MessageAsk.setModel(this);
		this.waitAnswer = new ArrayList<MessageAsk>();
		cardLeaderStart = new HashMap<Player, ArrayList<CardLeader>>();
		playerGame = null;
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
		Player player = new Player(username, ColorPlayer.getColor(players.size()+1));
		this.players.put(username, player);
	}

	public String getPlayerGame() {
		return playerGame;
	}

	public Player getCurrentPlayer() {
		return players.get(playerGame);
	}

	public void startGame() {
		board.setZone(players.size());
		orderedPlayers = new ArrayList<String>(players.keySet());
		for (int i = 0; i < orderedPlayers.size(); i++) {
			players.get(orderedPlayers.get(i)).addSpecificResource("Coin", new Coin(5 + i));
		}
		for(String el: players.keySet()){
			cardLeaderStart.put(players.get(el), new ArrayList<CardLeader>());
		}
		ArrayList<CardLeader> temp=CardSort.leaderSort();
		for(Player el: cardLeaderStart.keySet()){
			for(int i=0; i<4;i++){
				cardLeaderStart.get(el).add(temp.remove(0));
			}
		}
		canFamilyMove = true;
		turn = 1;
		giro = 1;
		board.reset(turn, new ArrayList<Player>(players.values()));
		playerGame = orderedPlayers.get(0); //eliminalo dopo
		notifyModel();
		//draftStart();
	}

	public void notifyModel() {
		if (waitAnswer.isEmpty()) {
			setChanged();
			notifyObservers();
			notifyMessage(new ChoiceMove());
		}
	}

	public void nextPlayer() {
		canFamilyMove = true;
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
			return;
		}
		if (giro == 4) {
			giro++;
			int i;
			for (i = 0; i < orderedPlayers.size(); i++) {
				if (players.get(orderedPlayers.get(i)).getSpecBonus().returnBool("SkipFirstMove")) {
					playerGame = orderedPlayers.get(i);
					return;
				}
			}
			if (i == orderedPlayers.size()) {
				newTurn();
				return;
			}
		}
		if (giro == 5) {
			newTurn();
			return;
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
		giro=1;
		board.reset(turn, new ArrayList<Player>(players.values()));
		if (turn > 6) {
			EndGame();
		}
	}

	private void EndGame() {
		for (String el : players.keySet()) { 
			players.get(el).applyEndEffects();
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

	public void notifyAsk(MessageAsk ask) {
		if(!waitAnswer.contains(ask)){
			waitAnswer.add(ask);
		}
		setChanged();
		notifyObservers(ask);
	}

	public void notifyMessage(GenericMessage ask) {
		setChanged();
		notifyObservers(ask);
	}

	public boolean getCanFamilyMove() {
		return canFamilyMove;
	}

	public ArrayList<MessageAsk> getWaitAnswer() {
		return waitAnswer;
	}

	public void setCantFamilyMove() {
		this.canFamilyMove = false;
	}

	public void draftStart() {
		boolean end = true;
		for (Player el : cardLeaderStart.keySet()) {
			if (cardLeaderStart.get(el).size() != 0) {
				end = false;
			}
		}
		if (end == true) {
			cardLeaderStart = null;
			playerGame = orderedPlayers.get(0);
			notifyModel();
			notifyMessage(new ChoiceMove());
		} else {
			if(nextDraft()){
				draftLeader();
				for (Player el : cardLeaderStart.keySet()) {
					AskLeader mex = new AskLeader(cardLeaderStart.get(el), el);
					mex.applyAsk();
				}
			}
		}
	}
	
	private void draftLeader(){
		ArrayList<Player> players=new ArrayList<Player>(cardLeaderStart.keySet());
		ArrayList<ArrayList<CardLeader>> cards=new ArrayList<ArrayList<CardLeader>>(cardLeaderStart.values());
		HashMap<Player, ArrayList<CardLeader>> temp=new HashMap<Player, ArrayList<CardLeader>>();
		for(int i=0;i<players.size();i++){
			temp.put(players.get(i), cards.get((i+1) % cards.size()));
		}
		cardLeaderStart=temp;
	}
	
	private boolean nextDraft(){
		boolean next=true;
		int x=0;
		for(ArrayList<CardLeader> el: cardLeaderStart.values()){
			if(x==0){
				x=el.size();
			}else{
				if(x!=el.size()){
					next=false;
				}
			}
		}
		return next;
	}
	
	public ArrayList<CardLeader> getCardLeaderStart(Player player){
		if(cardLeaderStart.containsKey(player)){
			return cardLeaderStart.get(player);
		}else{
			return null;
		}
	}
}
