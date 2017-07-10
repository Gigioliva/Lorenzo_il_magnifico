package it.polimi.ingsw.ps22.server.message;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerCosts;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * Some cards have different costs that can be paid to take it. 
 * This message ask the player which cost he wants to pay
 *
 */
public class AskCosts extends MessageAsk {
	private static final long serialVersionUID = 1L;
	private int numChoice;
	private ArrayList<RequisiteCost> possibleCost;
	private HashMap<String, ResourceAbstract> discount;
	private Player player;
	private TowerSpace towerSpace;

	/**
	 * It creates a new {@link AskCosts}
	 * @param possibleCost a list of {@link RequisiteCost} among which the player has to choose
	 * @param player the target player
	 * @param towerSpace the target {@link TowerSpace} to which the card belongs
	 */
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
	
	/**
	 * 
	 * @param str the text of the message
	 * @param id the id of the message
	 * @param possibleCost a list of {@link RequisiteCost} among which the player has to choose
	 */
	public AskCosts(String str, int id, ArrayList<RequisiteCost> possibleCost){
		super(str,id);
		this.possibleCost=new ArrayList<RequisiteCost>();
		for(RequisiteCost el: possibleCost){
			this.possibleCost.add(el.clone());
		}
	}

	/**
	 * 
	 * @param possibleCost a list of {@link RequisiteCost} among which the player has to choose
	 * @param player the target player
	 * @param towerSpace the target {@link TowerSpace} to which the card belongs
	 * @param discount eventual discount for the card
	 */
	public AskCosts(ArrayList<RequisiteCost> possibleCost, Player player, TowerSpace towerSpace,
			HashMap<String, ResourceAbstract> discount) {
		this(possibleCost, player, towerSpace);
		this.discount = discount;
	}

	/**
	 * 
	 * @return the id of the message
	 */
	public int getIdAsk() {
		return id_ask;
	}

	/**
	 * 
	 * @return a list of {@link RequisiteCost} among which the player has to choose
	 */
	public ArrayList<RequisiteCost> getPossibleCost() {
		return possibleCost;
	}

	/**
	 * 
	 * @return the target player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * 
	 * @return the target {@link TowerSpace} to which the card belongs
	 */
	public TowerSpace getTowerSpace() {
		return towerSpace;
	}

	/**
	 * 
	 * @return the number of choices that the player has to make
	 */
	public int getNumChoice() {
		return numChoice;
	}

	/**
	 * 
	 * @return the discount for the card
	 */
	public HashMap<String, ResourceAbstract> getDiscount() {
		if (discount != null)
			return discount;
		else
			return new HashMap<String, ResourceAbstract>();
	}
	
	@Override
	public void applyDefault(Model model) {
		AnswerCosts ans = new AnswerCosts(this.getId(), 1);
		ans.applyAnswer(model);
	}
	
	public AskCosts accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
