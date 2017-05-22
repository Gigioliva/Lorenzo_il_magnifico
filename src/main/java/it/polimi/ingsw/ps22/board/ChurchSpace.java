package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.card.CardExcomm;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.model.Model;
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
				cardExcomm.applyPermanentEffects(el, null);
			} else {
				Ask ask=Model.getAsk();
				if (ask.askExcomm()) {
					el.getSpecificResource("VictoryPoint").addResource(
							faithPointTrack.getVictoryBonus(el.getSpecificResource("FaithPoint").getQuantity()));
					el.getSpecificResource("FaithPoint").subResource(el.getSpecificResource("FaithPoint"));
				} else {
					cardExcomm.applyPermanentEffects(el, null);
				}
			}
		}

	}
}
