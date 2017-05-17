package it.polimi.ingsw.ps22.player;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.card.DevelopmentCard;
import it.polimi.ingsw.ps22.model.Color;
import it.polimi.ingsw.ps22.resource.Point;
import it.polimi.ingsw.ps22.resource.Resource;

public class Player {
	private HashMap<String,Resource> resources;
	private HashMap<String,Point> points;
	private HashMap<String, ArrayList<DevelopmentCard>> cards; //Meglio casting o metodi vuoti?
	private BonusAcc bonusAcc;
	private SpecBonus specBonus;
	private HashMap<Color,Family> family;
	private PersonalBoard personalBoard;
	private String username;
	//private Connection connection;
	
	public Player(){
	}

	public HashMap<String,Resource> getResources(){
		return this.resources;
	}
	
	public HashMap<String,Point> getPoints(){
		return this.points;
	}
	
	public ArrayList<DevelopmentCard> getDevelopmentCard(String type){
		return this.cards.get(type);
	}
	
	public BonusAcc getBonusAcc(){
		return bonusAcc;
	}
	
	public SpecBonus getSpecBonus(){
		return specBonus;
	}
	
	public Family getFamily(Color color){
		return this.family.get(color);
	}
	
	public String getUsername(){
		return username;
	}
	
	public PersonalBoard getPersonalBoard(){
		return personalBoard;
	}
	
	
	

}
