package it.polimi.ingsw.ps22.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.stream.Collectors;
import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.message.AskLeader;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.EndGame;
import it.polimi.ingsw.ps22.server.message.EndDraft;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class Model extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Board board;
	private int turn;
	private int giro;
	private LinkedHashMap<String, Player> players;
	private ArrayList<String> orderedPlayers;
	private String playerGame;
	private boolean canFamilyMove;
	private ArrayList<MessageAsk> waitAnswer;
	private LinkedHashMap<Player, ArrayList<CardLeader>> cardLeaderStart;
	private boolean isActive=true;

	/**
	 * 
	 */
	public Model() {
		board = new Board(this);
		this.players = new LinkedHashMap<String, Player>();
		this.waitAnswer = new ArrayList<MessageAsk>();
		cardLeaderStart = new LinkedHashMap<Player, ArrayList<CardLeader>>();
		playerGame = null;
	}
	
	public Model clone(String username){
		Model temp=new Model();
		temp.turn=this.turn;
		temp.giro=this.giro;
		temp.canFamilyMove=this.canFamilyMove;
		for(String el: players.keySet()){
			temp.players.put(el, players.get(el).clone(username));
		}
		temp.orderedPlayers=new ArrayList<String>();
		for(String name: this.orderedPlayers){
			temp.orderedPlayers.add(name);
		}
		temp.playerGame=this.playerGame;
		ArrayList<Player> player=new ArrayList<Player>(temp.players.values());
		ArrayList<Family> families=new ArrayList<Family>();
		for(Player play:player){
			for(Color col: Color.values()){
				families.add(play.getFamily(col));
			}
		}
		temp.board=this.board.clone(families);
		return temp;
	}

	/**
	 * 
	 * @return the updated {@link Board}
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * 
	 * @return the current turn of the game
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * 
	 * @return the {@link Player} of the game. The key is a String representing the name of the Player
	 */
	public HashMap<String, Player> getPlayers() {
		return players;
	}

	/**
	 * It adds to the the players of the game a new {@link Player}
	 * @param username the name of the new player
	 */
	public void addPlayers(String username) {
		Player player = new Player(username, ColorPlayer.getColor(players.size()+1));
		this.players.put(username, player);
	}

	/**
	 * 
	 * @return the username of the player that is currently playing
	 */
	public String getPlayerGame() {
		return playerGame;
	}

	/**
	 * 
	 * @return the {@link Player} that is currently playing
	 */
	public Player getCurrentPlayer() {
		return players.get(playerGame);
	}

	/**
	 * This method initializes the game. It sets the playable and not playable zones of the 
	 * board, it gives to the player the right amount of {@link Coin} according to order, 
	 * it starts the procedure of assigning the {@link CardLeader} to the players. 
	 * It notifies the {@link ModelView}
	 */
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
		setChanged();
		notifyObservers();
		draftStart();
	}

	/**
	 * It notifies the {@link ModelView} that there are no messages
	 * in waiting state, and then it notify the {@link ModelView}
	 * with new {@link ChoiceMove} message
	 */
	public void notifyModel() {
		if (waitAnswer.isEmpty()) {
			setChanged();
			notifyObservers();
			notifyMessage(new ChoiceMove());
		}
	}

	/**
	 * 
	 */
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
			players.get(el).applyEndEffects(this);
		}
		winMilitaryPoint();
		for (String el : players.keySet()) {
			players.get(el).calcVicPoint();
		}
		String winner=winGame().getUsername();
		EndGame mex=new EndGame(winner);
		setChanged();
		notifyObservers();
		notifyMessage(mex);
		for(Player el: players.values()){
			el.setConnected(false);
		}
		setChanged();
		notifyObservers();
		playerGame=winner;
		isActive=false;
	}

	private void winMilitaryPoint() {
		LinkedHashMap<Player, Integer> temp = new LinkedHashMap<Player, Integer>();
		for(String el: players.keySet()){
			temp.put(players.get(el),players.get(el).getSpecificResource("MilitaryPoint").getQuantity());
		}
		ArrayList<Integer> point=new ArrayList<Integer>(temp.values());
		point=(ArrayList<Integer>) point.stream().sorted((a,b)->b.compareTo(a)).collect(Collectors.toList());
		int i=point.get(0);
		Player player=searchPlayMil(temp,i);
		player.addPoints("VictoryPoint", new VictoryPoint(5));
		if(i==point.get(1)){
			player=searchPlayMil(temp,i);
			player.addPoints("VictoryPoint", new VictoryPoint(5));
		}else{
			i=point.get(1);
			player=searchPlayMil(temp,i);
			player.addPoints("VictoryPoint", new VictoryPoint(2));
			if(point.size()>2 && point.get(1)==point.get(2)){
				player=searchPlayMil(temp,i);
				player.addPoints("VictoryPoint", new VictoryPoint(2));
			}
		}
	}
	
	private Player searchPlayMil(LinkedHashMap<Player, Integer> temp, int i){
		for(Player el: temp.keySet()){
			if(temp.get(el)==i){
				 temp.remove(el);
				 return el;
			}
		}
		return null;
	}

	private Player winGame() {
		Player player = null;
		int i = -1;
		for (String el : players.keySet()) {
			if (players.get(el).getSpecificResource("VictoryPoint").getQuantity() > i) {
				player = players.get(el);
				i = players.get(el).getSpecificResource("VictoryPoint").getQuantity();
			}
		}
		return player;
	}

	/**
	 * It notifies the {@link ModelView} sending a {@link MessageAsk} message
	 * @param ask the message to send
	 */
	public void notifyAsk(MessageAsk ask) {
		if(!waitAnswer.contains(ask)){
			waitAnswer.add(ask);
		}
		setChanged();
		notifyObservers(ask);
	}

	/**
	 * It notifies the {@link ModelView} sending a {@link GenericMessage}
	 * @param ask the message to send
	 */
	public void notifyMessage(GenericMessage ask) {
		setChanged();
		notifyObservers(ask);
	}
	
	/**
	 * It sends notify the {@link ModelView} that it has to
	 * do something with the whole {@link Model}
	 */
	public void sendModel(){
		setChanged();
		notifyObservers();
	}

	/**
	 * 
	 * @return
	 */
	public boolean getCanFamilyMove() {
		return canFamilyMove;
	}

	/**
	 * 
	 * @return all the messages that are in wainting state
	 */
	public ArrayList<MessageAsk> getWaitAnswer() {
		return waitAnswer;
	}

	public void setCantFamilyMove() {
		this.canFamilyMove = false;
	}

	/**
	 * This method actually performs the procedure of assigning 
	 * to the {@link Player}s the {@link CardLeader}s. First it 
	 *	sending to each player 4 Leader card, then each player chooses
	 * one card and send the three more to the other player. The procedures
	 * ends when 4 cards are assigned to each player 
	 */
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
			notifyMessage(new EndDraft());
			notifyModel();
		} else {
			if(nextDraft()){
				draftLeader();
				for (Player el : cardLeaderStart.keySet()) {
					AskLeader mex = new AskLeader(cardLeaderStart.get(el), el);
					notifyAsk(mex);
				}
			}
		}
	}
	
	private void draftLeader(){
		ArrayList<Player> players=new ArrayList<Player>(cardLeaderStart.keySet());
		ArrayList<ArrayList<CardLeader>> cards=new ArrayList<ArrayList<CardLeader>>(cardLeaderStart.values());
		LinkedHashMap<Player, ArrayList<CardLeader>> temp=new LinkedHashMap<Player, ArrayList<CardLeader>>();
		for(int i=0;i<players.size();i++){
			temp.put(players.get(i), cards.get((i+1) % cards.size()));
		}
		cardLeaderStart=temp;
	}
	
	private boolean nextDraft(){
		boolean next=true;
		int x=-1;
		for(ArrayList<CardLeader> el: cardLeaderStart.values()){
			if(x==-1){
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
	
	public String getOrderedPlayers(int pos){
		if(0<=pos && pos<players.size()){
			return orderedPlayers.get(pos);
		}
		return null;
	}
	
	public boolean getIsActive(){
		return isActive;
	}
	
	public void setNullPlayerGame(){
		this.playerGame=null;
	}
	
	public void setPlayerGame(){
		this.playerGame=orderedPlayers.get(0);
	}
}
