package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.effect.EndEffect;
import it.polimi.ingsw.ps22.resource.Resource;

public class CardVenture extends DevelopmentCard {

	private ArrayList<RequisiteCost> requisiteCost;
	private ArrayList<EndEffect> endEffects;
	private ArrayList<ImmediateEffect> immediateEffects;
	
	public CardVenture(){
		this.requisiteCost=new ArrayList<RequisiteCost>();
		this.endEffects=new ArrayList<EndEffect>();
		this.immediateEffects=new ArrayList<ImmediateEffect>();
	}
	
	public void addRequisiteCost(HashMap<String, Resource> cost, HashMap<String, Resource> requisite){
		RequisiteCost temp=new RequisiteCost();
		temp.addCost(cost);
		temp.addRequisite(requisite);
		requisiteCost.add(temp);
	}
	
	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}
	
	public void addEndEffect(EndEffect effect) {
		this.endEffects.add(effect);
	}
	
	public void applyImmediateEffects(Player player, Board board) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, board);
		}
	}
	
	public void loadEndEffects(Player player, Board board) {
		//Accedi a player e caricali nel EndEffect Arraylist
	}
	
	/*
	public void applyAllEffects(Player player, Board board){
		loadEndEffects(player,board);
		applyImmediateEffects(player, board);
	}*/

}