package it.polimi.ingsw.ps22.board;

import java.util.HashMap;
import it.polimi.ingsw.ps22.model.Color;

public class Board {
	private HashMap<String,TowerZone> towers;
	private MarketZone market;
	private ProductionZone prodZone;
	private HarvestZone harvestZone;
	private CouncilPalaceSpace councilPalace;
	private HashMap<Integer,ChurchSpace> church;
	private Dice dice;
	
	public Board(FaithPointTrack faithTrack){
		market=new MarketZone();
		prodZone=new ProductionZone();
		harvestZone=new HarvestZone();
		councilPalace=new CouncilPalaceSpace();
		church=new HashMap<Integer,ChurchSpace>();
		church.put(1, new ChurchSpace(1));
		church.put(2, new ChurchSpace(2));
		church.put(3, new ChurchSpace(3));
		dice=new Dice();
		towers=new HashMap<String,TowerZone>();
		towers.put("Building", new TowerBuildingZone(this));
		towers.put("Character", new TowerCharacterZone(this));
		towers.put("Territory", new TowerTerritoryZone(this));
		towers.put("Venture", new TowerVentureZone(this));
	}
	
	public TowerZone getTower(String type){
		return towers.get(type);
	}
	public MarketZone getMarket(){
		return market;
	}
	public HarvestZone getHarvestZone(){
		return harvestZone;
	}
	public ProductionZone getProdZone(){
		return prodZone;
	}
	public CouncilPalaceSpace getCouncilPalace(){
		return councilPalace;
	}
	public ChurchSpace getChurchSpace(int era){
		return church.get(era);
	}
	
	public int getDice(Color color){
		return dice.getDice(color);
	}
}
