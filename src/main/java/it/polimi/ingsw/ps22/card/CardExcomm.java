package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.EndEffect;
import it.polimi.ingsw.ps22.effect.PermanentEffect;
import it.polimi.ingsw.ps22.player.Player;

public class CardExcomm extends Card {
	private String name;
	private int era;
	private ArrayList<PermanentEffect> permanentEffects;
	private ArrayList<EndEffect> endEffects;

	public void addPermanentEffect(PermanentEffect effect) {
		this.permanentEffects.add(effect);
	}

	public void applyPermanentEffects(Player player, Board board) {
		for (PermanentEffect el : permanentEffects) {
			el.performEffect(player, board);
		}
	}
	
	public void loadEndEffects(Player player, Board board) {
		try{
			player.getEndEffects().addAll(endEffects);
		}
		catch (NullPointerException e){ //lancia eccezione se endEffect è vuota, in tal caso semplicemente ritorno al chiamante
			return;
		}
	}

	public void setEra(int era) {
		this.era = era;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEra() {
		return this.era;
	}

	public String getName() {
		return this.name;
	}
	

}
