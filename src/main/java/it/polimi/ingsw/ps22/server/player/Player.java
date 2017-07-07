package it.polimi.ingsw.ps22.server.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import it.polimi.ingsw.ps22.server.board.Dice;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.model.Model;
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
/**
 * Class that contains all player's data
 */
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
	private boolean connected = true;

	/**
	 * It creates an instance of Player, with 2 {@link Stone}, 2 {@link Wood}, 3
	 * {@link Servant} and random personal bonuses.
	 * 
	 * @param username
	 *            the name of the player
	 * @param color
	 *            the color assigned to the player
	 */
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
		leaders = new ArrayList<CardLeader>();
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
		PersonalBoardSaxParser.PersonalBoardRead(
				"src/main/java/it/polimi/ingsw/ps22/server/parser/resources/PersonalBoard.xml", temp);
		Random random = new Random();
		personalBoard = temp.get(random.nextInt(temp.size()));
		this.color = color;
	}

	public Player clone(String username) {
		Player temp = new Player(this.username, this.color);
		temp.bonusAcc = this.bonusAcc.clone();
		temp.specBonus = this.specBonus.clone();
		for (EndEffect el : endEffects) {
			temp.endEffects.add(el.clone());
		}
		for (String el : resources.keySet()) {
			temp.resources.put(el, this.resources.get(el).clone());
		}
		for (String el : points.keySet()) {
			temp.points.put(el, this.points.get(el).clone());
		}
		for (Color el : family.keySet()) {
			temp.family.put(el, this.family.get(el).clone(temp));
		}
		for (String el : cards.keySet()) {
			ArrayList<DevelopmentCard> x = new ArrayList<DevelopmentCard>();
			for (DevelopmentCard el2 : this.cards.get(el)) {
				x.add(el2.clone());
			}
			temp.cards.put(el, x);
		}
		if (this.username.equalsIgnoreCase(username)) {
			for (CardLeader el : leaders) {
				temp.leaders.add(el.clone());
			}
		} else {
			for (CardLeader el : leaders) {
				if (el.isPlay()) {
					temp.leaders.add(el.clone());
				}
			}
		}
		temp.personalBoard = this.personalBoard.clone();
		temp.connected = this.connected;
		return temp;
	}

	/**
	 * 
	 * @return all the familiars of the player
	 */
	public HashMap<Color, Family> getAllFamily() {
		return family;
	}

	/**
	 * 
	 * @return An HashMap<String, Resource> containing the resources of the
	 *         player
	 */
	public HashMap<String, Resource> getResources() {
		return this.resources;
	}

	/**
	 * It adds the leader to the leader cards of the player
	 * 
	 * @param leader
	 *            the card to be added to the player
	 */
	public void addLeader(CardLeader leader) {
		leaders.add(leader);
	}

	/**
	 * 
	 * @return An {@link ArrayList} containing the {@link CardLeader} of the
	 *         player
	 */
	public ArrayList<CardLeader> getLeaders() {
		return leaders;
	}

	/**
	 * 
	 * @return an HashMap<String, Point> containing the {@link Point} of the
	 *         player
	 */
	public HashMap<String, Point> getPoints() {
		return this.points;
	}

	/**
	 * 
	 * @param type
	 *            the type of the cards to retrieve
	 * @return an {@link ArrayList} containing the {@link DevelopmentCard} of
	 *         the type chosen by input
	 */
	public ArrayList<DevelopmentCard> getDevelopmentCard(String type) {
		return this.cards.get(type);
	}

	/**
	 * 
	 * @return the {@link BonusAcc} of the player containing all the permanent
	 *         bonus/malus by which he is affected
	 */
	public BonusAcc getBonusAcc() {
		return bonusAcc;
	}

	/**
	 * 
	 * @return the {@link SpecBonus} containing some constraints by which the
	 *         player is affected
	 */
	public SpecBonus getSpecBonus() {
		return specBonus;
	}

	/**
	 * 
	 * @param color
	 *            the color of the familiar to be retrieved
	 * @return the familiar of the selected {@link Color}
	 */
	public Family getFamily(Color color) {
		return this.family.get(color);
	}

	/**
	 * 
	 * @return a {@link String} containing the username of the player
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return the {@link PersonalBoard} of the player
	 */
	public PersonalBoard getPersonalBoard() {
		return personalBoard;
	}

	// sottrae risorse o punti al giocatore se esso ha qualche effetto
	// permanente che glielo impone
	/**
	 * It applies the permanent effects contained in the {@link BonusAcc}
	 * indicated by the ArrayList of String passes as parameter
	 * 
	 * @param resources
	 *            the types of resources for which you want to apply the
	 *            malus/bonus of the {@link BonusAcc}
	 */
	public void applyMalusResource(ArrayList<String> resources) {
		for (String type : resources) {
			BonusAbstract temp = this.bonusAcc.getBonus(type);
			if (this.isPoint(type)) {
				this.points.get(type).addResource(temp);
			} else {
				this.resources.get(type).addResource(temp);
			}
		}
	}

	/**
	 * 
	 * @param type
	 *            the type of {@link Resource} to sub to the player
	 * @param other
	 *            the {@link Resource} you want to sub
	 */
	public void subResources(String type, Resource other) {
		this.resources.get(type).subResource(other);
	}

	/**
	 * 
	 * @param type
	 *            the type of {@link Resource} to add to the player
	 * @param other
	 *            the {@link Resource} you want to add
	 */
	public void addPoints(String type, Point other) {
		this.points.get(type).addResource(other);
	}

	/**
	 * 
	 * @param type
	 *            the type of the card to add to the player
	 * @param other
	 *            the {@link DevelopmentCard} to add
	 */
	public void addDevelopmentCard(String type, DevelopmentCard other) {
		this.cards.get(type).add(other);
	}

	/**
	 * 
	 * @param bonus
	 *            an HashMap<String, BonusAbstract> containing the bonuses to
	 *            add to the {@link BonusAcc} of the player
	 */
	public void addBonusAcc(HashMap<String, BonusAbstract> bonus) {
		this.bonusAcc.addBonus(bonus);
	}

	/**
	 * 
	 * @param type
	 * @return the number of cards of the type selected
	 */
	public int getSizeCard(String type) {
		return this.cards.get(type).size();
	}

	/**
	 * 
	 * @param type
	 *            the type of bonus of the {@link SpecBonus} you want to set
	 */
	public void addSpecBonus(String type) {
		this.specBonus.setSpecBonus(type);
	}

	/**
	 * It applies all the {@link EndEffect} accumulated by the player during the
	 * game
	 * 
	 * @param model
	 */
	public void applyEndEffects(Model model) {
		for (EndEffect effect : endEffects) {
			effect.performEffect(this, model);
		}
	}

	/**
	 * 
	 * @return all the {@link EndEffect} of the player in an {@link ArrayList}
	 */
	public ArrayList<EndEffect> getEndEffects() {
		return endEffects;
	}

	/**
	 * 
	 * @param type
	 * @return it returns true if the type passed as parameter is a resource
	 */
	public boolean isResource(String type) {
		return this.resources.containsKey(type);
	}

	/**
	 * 
	 * @param type
	 * @return it returns true if the type passed as parameter is a point
	 */
	public boolean isPoint(String type) {
		return this.points.containsKey(type);
	}

	/**
	 * 
	 * @param type
	 * @return the {@link ResourceAbstract} of the type passed as parameter
	 */
	public ResourceAbstract getSpecificResource(String type) {
		if (this.isResource(type)) {
			return this.resources.get(type);
		}
		return this.points.get(type);
	}

	/**
	 * 
	 * @param type
	 * @return true if the type passed as parameter is a valid name for a card
	 */
	public boolean isCard(String type) {
		return (cards.containsKey(type));
	}

	/**
	 * It add a resource to the player
	 * 
	 * @param type
	 *            of the {@link ResourceAbstract}
	 * @param other
	 *            the {@link ResourceAbstract} to be added
	 */
	public void addSpecificResource(String type, ResourceAbstract other) {
		this.getSpecificResource(type).addResource(other);
	}

	public LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>> cloneCardswithActionEffect(
			String cardType) {
		LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>> clonedCards = new LinkedHashMap<DevelopmentCard, LinkedHashMap<ActionEffect, Integer>>();
		ArrayList<DevelopmentCard> cards = this.getDevelopmentCard(cardType);
		for (DevelopmentCard card : cards) {
			clonedCards.put(card, new LinkedHashMap<ActionEffect, Integer>());
			ArrayList<ActionEffect> effects = card.getActionEffects();
			for (int i = 0; i < effects.size(); i++) {
				clonedCards.get(card).put(effects.get(i), i);
			}
		}
		return clonedCards;
	}

	/**
	 * It sets the value of the familiars according to the values of the dices
	 * 
	 * @param dice
	 *            the dices of that are currently in the game
	 */
	public void setFamily(Dice dice) {
		for (Color el : family.keySet()) {
			if (el != Color.NEUTRAL)
				family.get(el).setValue(dice.getDice(el));
			else
				family.get(el).setValue(0);
		}
	}

	public void calcVicPoint() {
		VictoryPoint temp = personalBoard.getBonusHarvest().get(cards.get("Territory").size());
		points.get("VictoryPoint").addResource(temp);
		temp = personalBoard.getBonusCharacter().get(cards.get("Character").size());
		points.get("VictoryPoint").addResource(temp);
		int res = 0;
		for (String el : resources.keySet()) {
			res = res + resources.get(el).getQuantity();
		}
		points.get("VictoryPoint").addResource(new VictoryPoint(res / 5));
	}

	private String resourcesString() {

		StringBuilder str = new StringBuilder();

		str.append("Resources: \n");

		for (String type : resources.keySet()) {
			str.append("  " + resources.get(type).getQuantity() + " " + type + "\n");
		}

		return str.toString();
	}

	private String pointsString() {

		StringBuilder str = new StringBuilder();

		str.append("Points: \n");

		for (String type : points.keySet()) {
			str.append("  " + points.get(type).getQuantity() + " " + type + "\n");
		}

		return str.toString();
	}

	private String cardsString() {

		StringBuilder str = new StringBuilder();

		str.append("Cards: \n");

		for (String type : cards.keySet()) {
			ArrayList<DevelopmentCard> cardsArray = cards.get(type);
			str.append("  " + type + "\n");
			for (DevelopmentCard card : cardsArray)
				str.append("    " + card.toString() + "\n");
		}

		return str.toString();
	}

	private String endEffectsString() {

		StringBuilder str = new StringBuilder();

		if (endEffects.size() > 0) {
			str.append("You have the following end effects \n");
			for (EndEffect effect : endEffects) {
				str.append(effect.toString() + "\n");
			}
		}

		return str.toString();
	}

	private String familyString() {

		StringBuilder str = new StringBuilder();

		if (family.size() == 0)
			str.append("no more family members \n");

		else {
			str.append("You still have the following family memebers: \n");
			for (Color col : family.keySet()) {
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

	public int getGenericValue(String type) {
		if (isResource(type)) {
			return resources.get(type).getQuantity();
		}
		if (isPoint(type)) {
			return points.get(type).getQuantity();
		}
		if (isCard(type)) {
			return getSizeCard(type);
		}
		return 0;
	}

	public void resetLeader() {
		for (CardLeader el : leaders) {
			el.resetLeader();
		}
	}

	public ColorPlayer getColor() {
		return color;
	}

	/**
	 * 
	 * @param conn
	 *            if this boolean is true, the player will be considered a
	 *            connected.
	 */
	public void setConnected(boolean conn) {
		connected = conn;
	}

	/**
	 * 
	 * @return a boolean that is true if and only if the player is connected
	 */
	public boolean getConnected() {
		return connected;
	}

}
