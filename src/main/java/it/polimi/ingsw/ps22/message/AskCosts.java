package it.polimi.ingsw.ps22.message;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.TowerSpace;
import it.polimi.ingsw.ps22.card.RequisiteCost;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class AskCosts extends MessageAsk {
	private int numChoice;
	//salvo i valori per sospendere il model finchè il giocatore non risponde
	private ArrayList<RequisiteCost> possibleCost;
	private Player player;
	private TowerSpace towerSpace;
	
	public AskCosts(ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace){
		super();
		this.possibleCost=possibleCost;
		this.player=player;
		this.towerSpace=towerSpace;
		ArrayList<HashMap<String, ResourceAbstract>> cost = new ArrayList<HashMap<String, ResourceAbstract>>();
		for (RequisiteCost el : possibleCost) {
			cost.add(el.getCost());
		}
		numChoice=cost.size();
		StringBuilder str=new StringBuilder();
		str.append("Quale costo scegli? \n");
		for (HashMap<String, ResourceAbstract> x : cost) {
			for (String el : x.keySet()) {
				str.append(el + ": ");
				str.append(x.get(el).getQuantity() + " ");
			}
			str.append("\n");
		}
		setString(str.toString());
	}

	public int getIdAsk() {
		return id_ask;
	}

	public ArrayList<RequisiteCost> getPossibleCost() {
		return possibleCost;
	}

	public Player getPlayer() {
		return player;
	}

	public TowerSpace getTowerSpace() {
		return towerSpace;
	}
	
	public int getNumChoice(){
		return numChoice;
	}

}
