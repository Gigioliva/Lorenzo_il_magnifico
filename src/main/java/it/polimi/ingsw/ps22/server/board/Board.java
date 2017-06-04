package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.player.Player;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, TowerZone> towers;
	private MarketZone market;
	private ProductionZone prodZone;
	private HarvestZone harvestZone;
	private CouncilPalaceSpace councilPalace;
	private HashMap<Integer, ChurchSpace> church;
	private Dice dice;

	public Board() {
		market = new MarketZone();
		prodZone = new ProductionZone();
		harvestZone = new HarvestZone();
		councilPalace = new CouncilPalaceSpace();
		church = new HashMap<Integer, ChurchSpace>();
		church.put(2, new ChurchSpace(1));
		church.put(4, new ChurchSpace(2));
		church.put(6, new ChurchSpace(3));
		dice = new Dice();
		towers = new HashMap<String, TowerZone>();
		towers.put("Building", new TowerBuildingZone(this));
		towers.put("Character", new TowerCharacterZone(this));
		towers.put("Territory", new TowerTerritoryZone(this));
		towers.put("Venture", new TowerVentureZone(this));
	}

	public TowerZone getTower(String type) {
		return towers.get(type);
	}
	
	public MarketZone getMarket() {
		return market;
	}

	public HarvestZone getHarvestZone() {
		return harvestZone;
	}

	public ProductionZone getProdZone() {
		return prodZone;
	}

	public CouncilPalaceSpace getCouncilPalace() {
		return councilPalace;
	}

	public int getDice(Color color) {
		return dice.getDice(color);
	}

	public void setZone(int num) {
		market.setZone(num);
		prodZone.setZone(num);
		harvestZone.setZone(num);
	}

	public void reset(int turn, ArrayList<Player> players) {
		if (turn < 7) {
			dice.setDice();
			for (Player el : players) {
				el.setFamily(dice);
				el.resetLeader();
			}
			market.reset();
			prodZone.reset();
			harvestZone.reset();
			councilPalace.resetFamily();
			for (String el : towers.keySet()) {
				towers.get(el).reset(turn);
			}
		}
		if(church.containsKey(turn-1)){
			church.get(turn-1).applyExcomm(players);
		}
	}
	
	//per ogni torre, il familiare non può piazzare alcun familiare
	private boolean cantPlaceTowerZones(Player player){
		
		for(String type: towers.keySet()){
			if(!towers.get(type).cantPlaceZone(player)){
				return false;
			}
		}
		return true;
	}
	
	public boolean cantPlaceAllZones(Player player){
		return harvestZone.cantPlaceZone(player) && cantPlaceTowerZones(player) 
				&& prodZone.cantPlaceZone(player) && market.cantPlaceZone(player) && councilPalace.cantPlaceCouncilPalace(player);
	}
	
	private String towersString(){
		StringBuilder str = new StringBuilder();
		
		str.append("Towers: \n");
		
		for(String type: towers.keySet()){
			str.append(towers.get(type).toString() + "\n");
		}
		
		return str.toString();
	}
	
	private String churchString(){
		StringBuilder str = new StringBuilder();
		
		str.append("Church: \n");
		
		for(Integer turn: church.keySet()){
			str.append(church.get(turn).toString() + "\n");
		}
		
		return str.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append("BOARD: \n");
		
		str.append(towersString());
		
		str.append(churchString());
		
		str.append(church.get(2).getFaithTrack().toString());
		
		str.append(councilPalace.toString());
		
		str.append(harvestZone.toString());
		 
		str.append(prodZone.toString());
		
		str.append(dice.toString());
		
		return str.toString();
	}

	public ChurchSpace getChurch(int id) {
		if(church.containsKey(id)){
			return church.get(id);
		}
		return null;
	}
}
