package it.polimi.ingsw.ps22.player;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.effect.EndEffect;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.resource.BonusAbstract;
import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.FaithPoint;
import it.polimi.ingsw.ps22.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.resource.Point;
import it.polimi.ingsw.ps22.resource.Resource;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.resource.Servant;
import it.polimi.ingsw.ps22.resource.Stone;
import it.polimi.ingsw.ps22.resource.VictoryPoint;
import it.polimi.ingsw.ps22.resource.Wood;

public class Player {
	private HashMap<String, Resource> resources; 
	private HashMap<String, Point> points;
	private HashMap<String, ArrayList<DevelopmentCard>> cards; 
	private BonusAcc bonusAcc;
	private SpecBonus specBonus;
	private ArrayList<EndEffect> endEffects;
	private HashMap<Color, Family> family;
	private PersonalBoard personalBoard;
	private String username;
	

	public Player(String username) {
		resources = new HashMap<String, Resource>();
		points = new HashMap<String, Point>();
		resources.put("Coin", new Coin(0));
		resources.put("Stone", new Stone(2));
		resources.put("Wood", new Wood(2));
		resources.put("Servant", new Servant(3));
		points.put("VictoryPoint", new VictoryPoint(0));
		points.put("MilitaryPoint", new MilitaryPoint(0));
		points.put("FaithPoint", new FaithPoint(0));
		cards = new HashMap<String, ArrayList<DevelopmentCard>>();
		cards.put("Building", new ArrayList<DevelopmentCard>());
		cards.put("Character", new ArrayList<DevelopmentCard>());
		cards.put("Territory", new ArrayList<DevelopmentCard>());
		cards.put("Venture", new ArrayList<DevelopmentCard>());
		bonusAcc = new BonusAcc();
		specBonus = new SpecBonus();
		family = new HashMap<Color, Family>();
		family.put(Color.BLACK, new Family(Color.BLACK, this));
		family.put(Color.WHITE, new Family(Color.WHITE, this));
		family.put(Color.ORANGE, new Family(Color.ORANGE, this));
		family.put(Color.NEUTRAL, new Family(Color.NEUTRAL, this));
		this.username = username;
		personalBoard = new PersonalBoard();
		endEffects = new ArrayList<EndEffect>();
	}

	public HashMap<String, Resource> getResources() {
		return this.resources;
	}
	
	public HashMap<String, Point> getPoints(){
		return this.points;
	}


	public ArrayList<DevelopmentCard> getDevelopmentCard(String type) {
		return this.cards.get(type);
	}

	public BonusAcc getBonusAcc() {
		return bonusAcc;
	}

	public SpecBonus getSpecBonus() {
		return specBonus;
	}

	public Family getFamily(Color color) {
		return this.family.get(color);
	}

	public String getUsername() {
		return username;
	}

	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	public void addResources(ArrayList<String> resources) {
		for(String type: resources){
			BonusAbstract temp = this.bonusAcc.getBonus(type);
			if(this.isPoint(type)){
				this.points.get(type).addResource(temp);
				//this.points.get(type).addResource(resources);
			}
			else{
				this.resources.get(type).addResource(temp);
				//this.resources.get(type).addResource(resources.get(type));
			}
		}
	}
	
	public void subResources(String type, Resource other) {
		this.resources.get(type).subResource(other);
	}

	public void addPoints(String type, Point other) {
		this.resources.get(type).addResource(other);
	}
	
	public void addDevelopmentCard(String type, DevelopmentCard other) {
		this.cards.get(type).add(other);
	}

	public void addBonusAcc(HashMap<String, BonusAbstract> bonus) {
		this.bonusAcc.addBonus(bonus);
	}

	public int getSizeCard(String type) {
		return this.cards.get(type).size();
	}

	public void addSpecBonus(String type) {
		this.specBonus.setSpecBonus(type);
	}
	
	public void applyEndEffects(Board board){
		for(EndEffect effect: endEffects){
			effect.performEffect(this, board);
		}
	}
	
	public ArrayList<EndEffect> getEndEffects(){
		return endEffects;
	}
	
	private boolean isResource(String type){
		return this.resources.containsKey(type);
	}
	
	private boolean isPoint(String type){
		return this.points.containsKey(type);
	}

	public ResourceAbstract getSpecificResource(String type){
		if(this.isResource(type)){
			return this.resources.get(type);
		}
		return this.points.get(type);
	}
	
	public boolean isCard(String type){
		return (cards.containsKey(type));
	}
	
	public void addSpecificResource(String type, ResourceAbstract other){
		this.getSpecificResource(type).addResource(other);
	}
}
