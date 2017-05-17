package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.PermanentEffect;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.Resource;

public class CardCharacter extends DevelopmentCard {
	private Coin cost;										//provare a usare tutto nel costruttore se XML permette
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<PermanentEffect> permanentEffects;
	
	public CardCharacter() {
		this.immediateEffects=new ArrayList<ImmediateEffect>();
		this.permanentEffects=new ArrayList<PermanentEffect>();
	}
	
	public void addCost(Coin coin){
		this.cost=coin;
	}
	
	public HashMap<String, Resource> getCost(){
		HashMap<String, Resource> temp=new HashMap<String, Resource>();
		temp.put("Coin", this.cost);
		return temp;
	}
	
	public void addImmediateEffect(ImmediateEffect effect){
		this.immediateEffects.add(effect);
	}
	
	public void addPermanentEffect(PermanentEffect effect){
		this.permanentEffects.add(effect);
	}
	
	public void applyImmediateEffects(Player player, Board board){
		for(ImmediateEffect el: immediateEffects){
			el.performEffect(player,board);
		}
	}
	
	public void applyPermanentEffects(Player player, Board board){
		for(PermanentEffect el: permanentEffects){
			el.performEffect(player,board);
		}
	}
	
	/*
	public void applyAllEffects(Player player, Board board){
		applyPermanentEffects(player,board);
		applyImmediateEffects(player, board);
	}*/
}
