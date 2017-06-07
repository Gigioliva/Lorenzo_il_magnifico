package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.player.Player;

public class CardExcomm extends Card {
	
	private static final long serialVersionUID = 1L;
	private int era;
	private ArrayList<PermanentEffect> permanentEffects;
	private ArrayList<EndEffect> endEffects;

	public CardExcomm() {
		era = 0;
		endEffects = new ArrayList<EndEffect>();
		permanentEffects = new ArrayList<PermanentEffect>();
	}

	public void addPermanentEffect(PermanentEffect effect) {
		this.permanentEffects.add(effect);
	}

	public void addEndEffect(EndEffect effect) {
		this.endEffects.add(effect);
	}

	public void applyPermanentEffects(Player player) {
		for (PermanentEffect el : permanentEffects) {
			el.performEffect(player);
		}
	}

	public void loadEndEffects(Player player, Board board) {
		for (EndEffect effect : endEffects) {
			player.getEndEffects().add(0, effect);
		}
	}

	public void setEra(int era) {
		this.era = era;
	}

	public int getEra() {
		return this.era;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();

		str.append("era: " + era + "\n");

		if (permanentEffects.size() > 0) {
			str.append("permanent effects: \n");
			int i = 1;
			for (PermanentEffect effect : permanentEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
				i++;
			}
		}

		if (endEffects.size() > 0) {
			str.append("end effects: \n");
			int i = 1;
			for (EndEffect effect : endEffects) {
				str.append("  " + "[" + i + "] " + effect.toString());
			}
			i++;
		}

		return str.toString();
	}

}