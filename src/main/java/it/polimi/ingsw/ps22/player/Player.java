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

	//Tutti i getters
	

}
