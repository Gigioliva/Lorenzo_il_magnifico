package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskCosts extends MessageAsk {
	private static final long serialVersionUID = 1L;
	private int numChoice;
	private ArrayList<RequisiteCost> possibleCost;
	private HashMap<String, ResourceAbstract> discount;
	private Player player;
	private TowerSpace towerSpace;

	public AskCosts(ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace) {
		super(player.getUsername());
		this.possibleCost = possibleCost;
		this.player = player;
		this.towerSpace = towerSpace;
		ArrayList<HashMap<String, ResourceAbstract>> cost = new ArrayList<HashMap<String, ResourceAbstract>>();
		for (RequisiteCost el : possibleCost) {
			cost.add(el.getCost());
		}
		numChoice = cost.size();
		StringBuilder str = new StringBuilder();
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
	
	public AskCosts(String str, int id, ArrayList<RequisiteCost> possibleCost){
		super(str,id);
		this.possibleCost=new ArrayList<RequisiteCost>();
		for(RequisiteCost el: possibleCost){
			this.possibleCost.add(el.clone());
		}
	}

	public AskCosts(ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace,
			HashMap<String, ResourceAbstract> discount) {
		this(possibleCost, player, towerSpace);
		this.discount = discount;
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

	public int getNumChoice() {
		return numChoice;
	}

	public HashMap<String, ResourceAbstract> getDiscount() {
		if (discount != null)
			return discount;
		else
			return new HashMap<String, ResourceAbstract>();
	}
	
	public AskCosts accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
