package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.parser.CardSort;
import it.polimi.ingsw.ps22.server.parser.FaithPointSaxParser;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class ChurchSpace implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int era;
	private CardExcomm cardExcomm;
	private FaithPointTrack faithPointTrack;
	private HashMap<Integer, Integer> requisite;

	public ChurchSpace(int era) {
		this.era = era;
		requisite = new HashMap<Integer, Integer>();
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
		requisite.put(1, 3);
		requisite.put(2, 4);
		requisite.put(3, 5);
		this.faithPointTrack = track; // passo gli oggetti già clpnati
		this.cardExcomm = card;  // passo gli oggetti già clpnati
	}
	
	@Override
	public ChurchSpace clone() {
		return new ChurchSpace(this.era,this.faithPointTrack.clone(),this.cardExcomm.clone());
	}
	
	public void applyExcomm(ArrayList<Player> players) {
		for (Player el : players) {
			if (el.getSpecificResource("FaithPoint").getQuantity() < requisite.get(era)) {
				excommunication(el);
			} else {
				AskExcomm ask = new AskExcomm();
				ask.addPlayer(el);
				ask.applyAsk();
			}
		}
	}
	
	public FaithPointTrack getFaithTrack(){
		return faithPointTrack;
	}


	public void excommunication(Player player) {
		cardExcomm.applyPermanentEffects(player);
	}

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
	
	public CardExcomm getCardExcomm() {
		System.out.println("prova");
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
