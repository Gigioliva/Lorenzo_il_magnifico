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
	private ArrayList<Player> waitPlayer; //se è vuoto chiamo la notifyModel

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
		AskExcomm ask = new AskExcomm();
		waitPlayer=new ArrayList<Player>();
		for (Player el : players) {
			if (el.getSpecificResource("FaithPoint").getQuantity() < requisite.get(era)) {
				excommunication(el);
			} else {
				waitPlayer.add(el);
				ask.addPlayer(el);
			}
		}
		ask.applyAsk();
	}

	public void excommunication(Player player) {
		cardExcomm.applyPermanentEffects(player, null);
	}

	public void notExcommunication(Player player) {
		player.getSpecificResource("VictoryPoint")
				.addResource(faithPointTrack.getVictoryBonus(player.getSpecificResource("FaithPoint").getQuantity()));
		player.getSpecificResource("FaithPoint").subResource(player.getSpecificResource("FaithPoint"));
	}
	
	public ArrayList<Player> getWaitPlayer(){ //usato nel messaggio di risposta di askExcomm
		return waitPlayer;
	}

}
