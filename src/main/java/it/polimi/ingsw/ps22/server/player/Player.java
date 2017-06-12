package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import it.polimi.ingsw.ps22.server.board.Dice;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.parser.PersonalBoardSaxParser;
import it.polimi.ingsw.ps22.server.resource.BonusAbstract;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.FaithPoint;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.Point;
import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Servant;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.VictoryPoint;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class Player implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, Resource> resources; 
	private HashMap<String, Point> points;
	private HashMap<String, ArrayList<DevelopmentCard>> cards;
	private ArrayList<CardLeader> leaders;
	private BonusAcc bonusAcc;
	private SpecBonus specBonus;
	private ArrayList<EndEffect> endEffects;
	private HashMap<Color, Family> family;
	private PersonalBoard personalBoard;
	private String username;
	private ColorPlayer color;
	
	public Player(String username, ColorPlayer color) {
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
		leaders=new ArrayList<CardLeader>();
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
		ArrayList<PersonalBoard> temp = new ArrayList<PersonalBoard>();
		PersonalBoardSaxParser.PersonalBoardRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/PersonalBoard.xml", temp);
		Random random=new Random();
		personalBoard=temp.get(random.nextInt(temp.size()));
		this.color=color;
	}
	
	public  HashMap<Color, Family> getAllFamily(){
		return family;
	}
	
	public Player(Player player){
		this.resources = player.cloneResources();
		this.points = player.clonePoints();
	}

	public HashMap<String, Resource> getResources() {
		return this.resources;
	}
	
	public void addLeader(CardLeader leader){
		leaders.add(leader);
	}
	
	public ArrayList<CardLeader> getLeaders(){
		return leaders;
	}
	
	private HashMap<String, Resource> cloneResources(){
		
		HashMap<String, Resource> res = new HashMap<String, Resource>();
		
		for(String string: this.resources.keySet()){
			String newstring = new String(string);
			Resource clonedResource = new Resource(this.resources.get(string).getQuantity());
			res.put(newstring, clonedResource);
		}
		
		return res;
	}
	
	private HashMap<String, Point> clonePoints(){
		
		HashMap<String, Point> res = new HashMap<String, Point>();
		
		for(String string: this.points.keySet()){
			String newstring = new String(string);
			Point clonedResource = new Point(this.points.get(string).getQuantity());
			res.put(newstring, clonedResource);
		}
		
		return res;
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

	//sottrae risorse o punti al giocatore se esso ha qualche effetto permanente che glielo impone
	public void applyMalusResource(ArrayList<String> resources) {
		for(String type: resources){
			BonusAbstract temp = this.bonusAcc.getBonus(type);
			if(this.isPoint(type)){
				this.points.get(type).addResource(temp);
			}
			else{
				this.resources.get(type).addResource(temp);
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
	
	public void applyEndEffects(){
		for(EndEffect effect: endEffects){
			effect.performEffect(this);
		}
	}
	
	public ArrayList<EndEffect> getEndEffects(){
		return endEffects;
	}
	
	public boolean isResource(String type){
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
	
	public HashMap<DevelopmentCard, HashMap<ActionEffect,Integer>> cloneCardswithActionEffect(String cardType){
		HashMap<DevelopmentCard, HashMap<ActionEffect,Integer>> clonedCards = new HashMap<DevelopmentCard, HashMap<ActionEffect,Integer>>();
		ArrayList<DevelopmentCard> cards = this.getDevelopmentCard(cardType);
		for(DevelopmentCard card: cards){
			clonedCards.put(card, new HashMap<ActionEffect,Integer>());
			ArrayList<ActionEffect> effects = card.getActionEffects(); 
			for(int i = 0; i< effects.size(); i++){
				clonedCards.get(card).put(effects.get(i),i);
			}
		}
		return clonedCards;
	}
	
	public void setFamily(Dice dice) {
		for (Color el : family.keySet()) {
			if (el != Color.NEUTRAL)
				family.get(el).setValue(dice.getDice(el));
			else family.get(el).setValue(0);
		}
	}
	
	public void calcVicPoint(){
		VictoryPoint temp=personalBoard.getBonusHarvest().get(cards.get("Harvest").size());
		points.get("VictoryPoint").addResource(temp);
		temp=personalBoard.getBonusCharacter().get(cards.get("Character").size());
		points.get("VictoryPoint").addResource(temp);
		int res=0;
		for(String el: resources.keySet()){
			res=res+resources.get(el).getQuantity();
		}
		points.get("VictoryPoint").addResource(new VictoryPoint(res/5));
	}
	
	public String resourcesString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("Resources: \n");
		
		for(String type: resources.keySet()){
			str.append("  " + resources.get(type).getQuantity() + " " + type + "\n");
		}
		
		return str.toString();
	}
	
	private String pointsString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("Points: \n");
		
		for(String type: points.keySet()){
			str.append("  " + points.get(type).getQuantity() + " " + type + "\n");
		}
		
		return str.toString();
	}
	
	private String cardsString(){
		
		StringBuilder str = new StringBuilder();
		
		str.append("Cards: \n");
		
		for(String type: cards.keySet()){
			ArrayList<DevelopmentCard> cardsArray = cards.get(type); 
			str.append("  " + type + "\n");
			for(DevelopmentCard card: cardsArray)
				str.append("    " + card.toString() + "\n");
		}
		
		return str.toString();
	}
	
	private String endEffectsString(){
		
		StringBuilder str = new StringBuilder();
		
		if (endEffects.size() > 0){
			str.append("You have the following end effects \n");
			for(EndEffect effect: endEffects){
				str.append(effect.toString() + "\n");
			}
		}
		
		return str.toString();
	}
	
	private String familyString() {
		
		StringBuilder str = new StringBuilder();
		
		if(family.size() == 0)
			str.append("no more family members \n");
		
		else{
			str.append("You still have the following family memebers: \n");
			for(Color col: family.keySet()){
				if (!family.get(col).isPlaced())
					str.append("  " + family.get(col).toString() + "\n");
			}
				
		}
			
		return str.toString();
	}
	
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Player " + username + "\n");
		str.append(resourcesString() + pointsString() + cardsString() + bonusAcc.toString() + specBonus.toString()
			+ endEffectsString() + familyString() + personalBoard.toString());
		
		return str.toString();
	}
	
	public int getGenericValue(String type){
		if(isResource(type)){
			return resources.get(type).getQuantity();
		}
		if(isPoint(type)){
			return points.get(type).getQuantity();
		}
		if(isCard(type)){
			return getSizeCard(type);
		}
		return 0;
	}
	
	public void resetLeader(){
		for(CardLeader el: leaders){
			el.resetLeader();
		}
	}
	
	public ColorPlayer getColor(){
		return color;
	}
	
}
