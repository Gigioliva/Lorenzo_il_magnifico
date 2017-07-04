package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;

public class TowerZone extends Zone {

	private static final long serialVersionUID = 1L;
	protected static final int NUM_SPACES = 4;
	protected TowerSpace[] towerSpaces;
	protected boolean occupied;
	protected HashMap<Integer, ArrayList<DevelopmentCard>> cards;

	/**
	 * It instantiates a new TowerZone with his {@link TowerSpace}s
	 * @param model
	 */
	public TowerZone(Model model) {
		super(model);
		towerSpaces = new TowerSpace[NUM_SPACES];
		towerSpaces[0] = new TowerSpace(1, false, 1);
		towerSpaces[1] = new TowerSpace(3, false, 2);
		towerSpaces[2] = new TowerSpace(5, false, 3);
		towerSpaces[3] = new TowerSpace(7, false, 4);
		this.occupied = false;
		cards = new HashMap<Integer, ArrayList<DevelopmentCard>>();
		this.model=model;
	}

	@Override
	public TowerZone clone(ArrayList<Family> family) {
		TowerZone temp = new TowerZone(null);
		ArrayList<DevelopmentCard> arr;
		for (int i=0;i<NUM_SPACES;i++) {
			temp.towerSpaces[i] = this.towerSpaces[i].clone(family);
		}
		temp.occupied = this.occupied;
		for (Integer key: cards.keySet()) {
			arr = new ArrayList<DevelopmentCard>();
			for (DevelopmentCard devCard: cards.get(key)) {
				arr.add(devCard.clone());
			}
			temp.cards.put(key,arr);
		}
		return temp;
	}
	
	protected void setOccupied() {
		this.occupied = true;
	}
	
	/**
	 * 
	 * @return an array containing the {@link TowerSpace}s of this Zone
	 */
	public TowerSpace[] getTowerSpaces(){
		return this.towerSpaces;
	}

	/**
	 * It performs all the controls necessary to place the familiar
	 * in the specific {@link ActionSpace} of this tower,
	 * considering the {@link Servant}s added. It will be overridden in
	 * each specific tower
	 * @param numServant the number of {@link Servant}
	 * @param actionSpace the specific action space
	 * @param family the {@link Family} to be placed
	 */
	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		return false;
	}

	/**
	 * It checks whether a {@link Player} has already placed a {@link Family}
	 * in the {@link TowerZone}.
	 * @param player the target {@link Player}
	 * @return true if the player didn't place any {@link Family}, false otherwise
	 */
	protected boolean checkAllSpace(Player player) {
		boolean control = true;
		ArrayList<Family> allFamily = new ArrayList<Family>();
		for (int i = 0; i < NUM_SPACES; i++) {
			allFamily.addAll(towerSpaces[i].getFamilies());
		}
		for (Color el : Color.values()) {
			if (el != Color.NEUTRAL)
				control = control && !allFamily.contains(player.getFamily(el));
		}
		return control;
	}

	/**
	 * It checks whether a {@link Player} is allowed to place the {@link Family} in the {@link TowerSpace} 
	 * and if he can afford to take the corresponding {@link DevelopmentCard}
	 * @param player the target {@link Player}
	 * @param towerSpace the specific {@link TowerSpace} of the zone
	 * @return true if the {@link Player} can afford to to place a familiar in the space and can afford
	 * to take the {@link DevelopmentCard}
	 */
	protected boolean checkResources(Player player, TowerSpace towerSpace) {
		boolean applyBonus = false;
		boolean payCoin = false;
		boolean result = false;
		if (occupied && !(player.getSpecBonus().returnBool("NoCostTower"))) {
			if (player.getSpecificResource("Coin").getQuantity() < 3)
				return false;
			player.getSpecificResource("Coin").subResource(new Coin(3));
			payCoin = true;
		}
		if (!(player.getSpecBonus().returnBool("GainTowers")
				&& (towerSpace.getPlan() == 3 || towerSpace.getPlan() == 4))) {
			towerSpace.applyBonus(player, model);
			applyBonus = true;
		}
		if(towerSpace.getCard()!=null){
			result = towerSpace.getCard().takeCardControl(player);
		}else{
			result=true;
		}
		if (payCoin)
			player.getSpecificResource("Coin").addResource(new Coin(3));
		if (applyBonus)
			towerSpace.deapplyBonus(player);
		return result;
	}

	
	/**
	 * this method performs the actual placement of the {@link Family} in the 
	 * specific {@link ActionSpace} of this tower
	 * @param numServant {@link Servant}s used to increase the action value
	 * @param actionSpace the specific {@link ActionSpace}
	 * @param family the {@link Family} to be placed
	 */
	public void placeFamily(int numServant, int actionSpace, Family family) {
	}
	
	/**
	 * This method assigns the card to the {@link Player}. Before doing it, it may ask to the {@link Player}
	 * which of the possible costs of the card he wants to pay and then applies all the effects of the card
	 * @param actionSpace the specific {@link ActionSpace}.
	 * @param player the target {@link Player}
	 */
	public void takeCard(int actionSpace, Player player){
		
	}
	
	/**
	 * This method assigns the card to the {@link Player}. Before doing it, it may ask to the {@link Player}
	 * which of the possible costs of the card he wants to pay and then applies all the effects of the card
	 * @param actionSpace the specific {@link ActionSpace}.
	 * @param player the target {@link Player}
	 * @param discount a possible discount on resources to take the card
	 */
	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount){
		if(discount==null || discount.isEmpty()){
			takeCard(actionSpace,player);
		}
	}

	/**
	 * this method resets all the {@link ActionSpace}s of this tower, and adds new cards 
	 * to those spaces
	 * @param turn an int representing the current turn
	 */
	@Override
	public void reset(int turn) {
		this.occupied=false;
		ArrayList<DevelopmentCard> temp = cards.get(turn);
		for (int i = 0; i < NUM_SPACES; i++) {
			towerSpaces[i].resetFamily();
			towerSpaces[i].addCard(temp.get(i));
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		for(TowerSpace tower: towerSpaces){
			str.append(tower.toString());
			str.append("\n\n");
		}
		
		return str.toString();
		
	}
	
	protected void deApplyDiscount(Player player, String cardType, HashMap<String, ResourceAbstract> discount){
		player.getBonusAcc().subSales(discount, cardType);
	}
	
	
	protected void applyDiscount(Player player, String cardType, HashMap<String, ResourceAbstract> discount){
		player.getBonusAcc().addSales(discount, cardType);
	}

}
