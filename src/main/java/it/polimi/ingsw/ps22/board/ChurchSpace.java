package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.card.CardExcomm;
import it.polimi.ingsw.ps22.message.AskExcomm;
import it.polimi.ingsw.ps22.player.Player;

public class ChurchSpace {
	private int era;
	private CardExcomm cardExcomm;
	private static FaithPointTrack faithPointTrack;
	private HashMap<Integer, Integer> requisite;

	public ChurchSpace(int era) {
		this.era = era;
		requisite = new HashMap<Integer, Integer>();
		requisite.put(1, 3);
		requisite.put(2, 4);
		requisite.put(3, 5);
		// faithPointTrack letto da file
		// cardExcomm letto da file
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
		cardExcomm.applyPermanentEffects(player, null);
	}

	public void notExcommunication(Player player) {
		player.getSpecificResource("VictoryPoint")
				.addResource(faithPointTrack.getVictoryBonus(player.getSpecificResource("FaithPoint").getQuantity()));
		player.getSpecificResource("FaithPoint").subResource(player.getSpecificResource("FaithPoint"));
	}
	
	private String requisiteString(){
		StringBuilder str = new StringBuilder();
		
		str.append("Excommunication requisite: ");
		
		str.append(requisite.get(era) + " faith points\n");
		
		return str.toString();
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
