package it.polimi.ingsw.ps22.board;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.player.Player;

public class Board {
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

	public ChurchSpace getChurchSpace(int era) {
		return church.get(era);
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
}
