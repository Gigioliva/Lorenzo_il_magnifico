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
	private FaithPointTrack faithTrack;
	private Dice dice;
	
	public Board(FaithPointTrack faithTrack){
		market=new MarketZone();
		prodZone=new ProductionZone();
		harvestZone=new HarvestZone();
		councilPalace=new CouncilPalaceSpace();
		church=new HashMap<Integer,ChurchSpace>();
		church.put(1, new ChurchSpace());
		church.put(2, new ChurchSpace());
		church.put(3, new ChurchSpace());
		this.faithTrack=faithTrack;
		dice=new Dice();
		towers=new HashMap<String,TowerZone>();
		towers.put("Building", new TowerZone());
		towers.put("Character", new TowerZone());
		towers.put("Territory", new TowerZone());
		towers.put("Venture", new TowerZone());
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
	public FaithPointTrack getFaithTrack (){
		return faithTrack;
	}
	public int getDice(Color color){
		return dice.getDice(color);
	}
	public void setDice(){
		this.dice=new Dice();
	}
}
