package it.polimi.ingsw.ps22.server.board;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TowerZone extends Zone {

	private static final long serialVersionUID = 1L;
	protected transient static final int NUM_SPACES = 4;
	protected TowerSpace[] towerSpaces;
	protected transient boolean occupied;
	protected HashMap<Integer, ArrayList<DevelopmentCard>> cards;

	public TowerZone() {
		towerSpaces = new TowerSpace[NUM_SPACES];
		towerSpaces[0] = new TowerSpace(1, false, 1);
		towerSpaces[1] = new TowerSpace(3, false, 2);
		towerSpaces[2] = new TowerSpace(5, false, 3);
		towerSpaces[3] = new TowerSpace(7, false, 4);
		this.occupied = false;
		cards = new HashMap<Integer, ArrayList<DevelopmentCard>>();
		// leggere da file le carte e bonus azione
	}

	@Override
	public TowerZone clone(ArrayList<Family> family) {
		TowerZone temp = new TowerZone();
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
	
	public TowerSpace[] getTowerSpaces(){
		return this.towerSpaces;
	}

	@Override
	public boolean Control(int numServant, int actionSpace, Family family) {
		return false;
	}

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
			towerSpace.applyBonus(player);
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
	
	//it returns false if for all flats of the tower, it exists a family member (that is not already placed) that is placeable
	//so it returns true if the player can't place any family member in the tower
	@Override
	public boolean cantPlaceZone(Player player){
		 HashMap<Color, Family> family = player.getAllFamily();
		 for(int i=0; i<NUM_SPACES; i++){
			 for(Color color: family.keySet()){
				 if(!family.get(color).isPlaced())
					 if(cantPlaceSpace(player.getSpecificResource("Servant").getQuantity(), i, family.get(color)))
						 return false;
			 }
		 }
		 return true;
	}
	
	
	public void placeFamily(int numServant, int actionSpace, Family family) {
	}
	
	public void takeCard(int actionSpace, Player player){
		
	}
	
	public void takeCard(int actionSpace, Player player, HashMap<String, ResourceAbstract> discount){
		if(discount==null || discount.isEmpty()){
			takeCard(actionSpace,player);
		}
	}

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
