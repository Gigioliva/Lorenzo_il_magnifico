package it.polimi.ingsw.ps22.server.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.player.Family;
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
		towers.put("Building", new TowerBuildingZone());
		towers.put("Character", new TowerCharacterZone());
		towers.put("Territory", new TowerTerritoryZone());
		towers.put("Venture", new TowerVentureZone());
	}
	
/*
	public Board clone(ArrayList<Player> player) {
		Board temp = new Board();
		temp.market = this.market.clone(player);
		temp.prodZone = this.prodZone.clone(player);
		temp.harvestZone = this.harvestZone.clone(player);
		temp.councilPalace = this.councilPalace.clone(player);
		for (Integer i : church.keySet()) {
			temp.church.put(i, this.church.get(i).clone());
		}
		for (String el : this.towers.keySet()) {
			temp.towers.put(el, this.towers.get(el).clone(player));
		}
		return temp;
	}
*/
	
	public Board clone(ArrayList<Family> family) {
		Board temp = new Board();
		temp.market = this.market.clone(family);
		temp.prodZone = this.prodZone.clone(family);
		temp.harvestZone = this.harvestZone.clone(family);
		temp.councilPalace = this.councilPalace.clone(family);
		for (Integer i : church.keySet()) {
			temp.church.put(i, this.church.get(i).clone());
		}
		for (String el : this.towers.keySet()) {
			temp.towers.put(el, this.towers.get(el).clone(family));
		}
		return temp;
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

	public Dice getDice() {
		return dice;
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
		if (church.containsKey(turn - 1)) {
			church.get(turn - 1).applyExcomm(players);
		}
	}

	// per ogni torre, il familiare non può piazzare alcun familiare
	private boolean cantPlaceTowerZones(Player player) {

		for (String type : towers.keySet()) {
			if (!towers.get(type).cantPlaceZone(player)) {
				return false;
			}
		}
		return true;
	}

	public boolean cantPlaceAllZones(Player player) {
		return harvestZone.cantPlaceZone(player) && cantPlaceTowerZones(player) && prodZone.cantPlaceZone(player)
				&& market.cantPlaceZone(player) && councilPalace.cantPlaceCouncilPalace(player);
	}

	private String towersString() {
		StringBuilder str = new StringBuilder();

		str.append("Towers: \n");

		for (String type : towers.keySet()) {
			str.append(towers.get(type).toString() + "\n");
		}

		return str.toString();
	}

	private String churchString() {
		StringBuilder str = new StringBuilder();

		str.append("Church: \n");

		for (Integer turn : church.keySet()) {
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
		if (church.containsKey(id)) {
			return church.get(id);
		}
		return null;
	}

}
