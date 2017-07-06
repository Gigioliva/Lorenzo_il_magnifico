package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.effect.Effect;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.parser.FaithPointSaxParser;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

/**
 * 
 * The church space is a space that the players have to take into account in order not to be excommunicated.
 * Every ChurchSpace corresponds to an era, it has some requisite to be satisfied ( in {@link VictoryPoint}).
 * If a player cannot or doesn't want to satisfy the church, he will be affected by the {@link Effect} indicated by
 * the {@link CardExcomm}
 *
 */
public class ChurchSpace implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int era;
	private CardExcomm cardExcomm;
	private FaithPointTrack faithPointTrack;
	private HashMap<Integer, Integer> requisite;
	private ArrayList<java.awt.Color> playerExcomm;
	private Model model;

	public ChurchSpace(int era, Model model) {
		this.model=model;
		this.era = era;
		requisite = new HashMap<Integer, Integer>();
		playerExcomm=new ArrayList<java.awt.Color>();
		requisite.put(1, 3);
		requisite.put(2, 4);
		requisite.put(3, 5);
		HashMap<Integer,VictoryPoint> track=new HashMap<Integer,VictoryPoint>();
		FaithPointSaxParser.FaithRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/FaithPointTrack.xml", track);
		this.faithPointTrack=new FaithPointTrack(track);
		this.cardExcomm=CardSort.excommSortByEra().get(era).get(0);
	}
	
	public ChurchSpace(int era, FaithPointTrack track, CardExcomm card) {
		this.era = era;
		requisite = new HashMap<Integer, Integer>();
		playerExcomm=new ArrayList<java.awt.Color>();
		requisite.put(1, 3);
		requisite.put(2, 4);
		requisite.put(3, 5);
		this.faithPointTrack = track;
		this.cardExcomm = card;
	}
	
	@Override
	public ChurchSpace clone() {
		ChurchSpace temp=new ChurchSpace(this.era,this.faithPointTrack.clone(),this.cardExcomm.clone());
		temp.playerExcomm.addAll(this.playerExcomm);
		return temp;
		
	}
	
	/**
	 * 
	 * @return an {@link ArrayList} containing the colors of the excommunicated players
	 */
	public ArrayList<java.awt.Color> getExcomm(){
		return playerExcomm;
	}
	
	/**
	 * It applies the excommunication to the {@link Player} that haven't satisfied the church requirements.
	 * If a player can choose whether being excommunicated or not, he will be asked now.
	 * @param players the players participating to the game
	 */
	public void applyExcomm(ArrayList<Player> players) {
		for (Player el : players) {
			if (el.getSpecificResource("FaithPoint").getQuantity() < requisite.get(era) || !el.getConnected()) {
				excommunication(el);
			} else {
				AskExcomm ask = new AskExcomm(el);
				model.notifyAsk(ask);
			}
		}
	}
	
	/**
	 * 
	 * @return the updated {@link FaithPointTrack}
	 */
	public FaithPointTrack getFaithTrack(){
		return faithPointTrack;
	}


	/**
	 * this method applies the effect of the excommunication to a given {@link Player}
	 * @param player that has been excommunicated
	 */
	public void excommunication(Player player) {
		cardExcomm.applyPermanentEffects(player, model);
		cardExcomm.loadEndEffects(player);
		playerExcomm.add(player.getColor().getColor());
	}

	/**
	 * If a {@link Player} has satisfied the church requirements, all of his {@link FaithPoint} will be detracted 
	 * and converted into {@link VictoryPoint}
	 * @param player that has satisfied the church requirements
	 */
	public void notExcommunication(Player player) {
		player.getSpecificResource("VictoryPoint")
				.addResource(faithPointTrack.getVictoryBonus(player.getSpecificResource("FaithPoint").getQuantity()));
		player.getSpecificResource("FaithPoint").subResource(player.getSpecificResource("FaithPoint"));
		if(player.getSpecBonus().returnBool("PointVicChurch+5")){
			player.getSpecificResource("VictoryPoint").addResource(new VictoryPoint(5));
		}
	}
	
	private String requisiteString(){
		StringBuilder str = new StringBuilder();
		
		str.append("Excommunication requisite: ");
		
		str.append(requisite.get(era) + " faith points\n");
		
		return str.toString();
	}
	
	/**
	 * 
	 * @return the {@link CardExcomm} related to this {@link ChurchSpace}
	 */
	public CardExcomm getCardExcomm() {
		return cardExcomm;
	}
	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		str.append("Era: " + era + "\n");
		
		str.append("Card: \n" + "  " + cardExcomm.toString() + "\n");
		
		
		str.append(requisiteString());
		
		
		return str.toString();
		
	}

}
