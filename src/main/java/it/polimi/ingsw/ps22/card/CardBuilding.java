package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.resource.Resource;
import it.polimi.ingsw.ps22.board.Board;

public class CardBuilding extends DevelopmentCard {
	private final int actionValue;
	private HashMap<String, Resource> cost;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<ActionEffect> actionEffects;

	public CardBuilding(int other) {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.actionEffects = new ArrayList<ActionEffect>();
		this.cost = new HashMap<String, Resource>();
		this.actionValue = other;
	}

	public void addCost(String type, Resource resource) {
		this.cost.put(type, resource);
	}

	public HashMap<String, Resource> getCost() {
		return this.cost;
	}

	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	public void addActionEffect(ActionEffect effect) {
		this.actionEffects.add(effect);
	}

	public void applyImmediateEffects(Player player, Board board) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, board);
		}
	}

	public ArrayList<ActionEffect> getActionEffects() {
		return this.actionEffects;
	}

	public void applyActionEffect(Player player, Board board, int number) {
		this.actionEffects.get(number).performEffect(player, board);
	}

	public int getActionValue() {
		return this.actionValue;
	}
	
	@Override 
	public boolean takeCardControl(Player player){
		for(String type: cost.keySet()){
			int costRequired = cost.get(type).getQuantity();
			int playerResource = player.getResources().get(type).getQuantity();
			if (costRequired > playerResource)
				return false;
		}
		return true;
	}

}
