package it.polimi.ingsw.ps22.player;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.resource.*;
import it.polimi.ingsw.ps22.card.*;
import it.polimi.ingsw.ps22.model.Color;

public class Player {
	private HashMap<String, Resource> resources;
	private HashMap<String, Point> points;
	private HashMap<String, ArrayList<DevelopmentCard>> cards; 
	private BonusAcc bonusAcc;
	private SpecBonus specBonus;
	private HashMap<Color, Family> family;
	private PersonalBoard personalBoard;
	private String username;

	public Player(String username) {
		resources = new HashMap<String, Resource>();
		resources.put("Coin", new Coin(0));
		resources.put("Stone", new Stone(2));
		resources.put("Wood", new Wood(2));
		resources.put("Servant", new Servant(3));
		points = new HashMap<String, Point>();
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
	}

	public HashMap<String, Resource> getResources() {
		return this.resources;
	}

	public HashMap<String, Point> getPoints() {
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

	public void addResource(String type, Resource other) {
		this.resources.get(type).addResource(other);
	}

	public void addPoint(String type, Point other) {
		this.points.get(type).addResource(other);
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

}
