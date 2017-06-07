package it.polimi.ingsw.ps22.server.parser;

import it.polimi.ingsw.ps22.server.card.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CardSortByEra {

	public static HashMap<Integer, ArrayList<CardCharacter>> characterSortByEra() {
		HashMap<Integer, ArrayList<CardCharacter>> card = new HashMap<Integer, ArrayList<CardCharacter>>();
		ArrayList<CardCharacter> allCard = new ArrayList<CardCharacter>();
		CharacterCardSaxParser.CharacterRead("character.xml", allCard);
		for (CardCharacter el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardCharacter> temp = new ArrayList<CardCharacter>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardVenture>> ventureSortByEra() {
		HashMap<Integer, ArrayList<CardVenture>> card = new HashMap<Integer, ArrayList<CardVenture>>();
		ArrayList<CardVenture> allCard = new ArrayList<CardVenture>();
		VentureCardSaxParser.VentureRead("venture.xml", allCard);
		for (CardVenture el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardVenture> temp = new ArrayList<CardVenture>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardBuilding>> buildingSortByEra() {
		HashMap<Integer, ArrayList<CardBuilding>> card = new HashMap<Integer, ArrayList<CardBuilding>>();
		ArrayList<CardBuilding> allCard = new ArrayList<CardBuilding>();
		BuildingCardSaxParser.BuildingRead("building.xml", allCard);
		for (CardBuilding el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardBuilding> temp = new ArrayList<CardBuilding>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardTerritory>> territorySortByEra() {
		HashMap<Integer, ArrayList<CardTerritory>> card = new HashMap<Integer, ArrayList<CardTerritory>>();
		ArrayList<CardTerritory> allCard = new ArrayList<CardTerritory>();
		TerritoryCardSaxParser.TerritoryRead("territory.xml", allCard);
		for (CardTerritory el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardTerritory> temp = new ArrayList<CardTerritory>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		return card;
	}

}