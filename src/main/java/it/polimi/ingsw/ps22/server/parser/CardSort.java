package it.polimi.ingsw.ps22.server.parser;

import it.polimi.ingsw.ps22.server.card.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CardSort {

	public static HashMap<Integer, ArrayList<CardCharacter>> characterSortByEra() {
		HashMap<Integer, ArrayList<CardCharacter>> card = new HashMap<Integer, ArrayList<CardCharacter>>();
		ArrayList<CardCharacter> allCard = new ArrayList<CardCharacter>();
		CharacterCardSaxParser.CharacterRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Character.xml", allCard);
		for (CardCharacter el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardCharacter> temp = new ArrayList<CardCharacter>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		for (Integer el : card.keySet()) {
			ArrayList<CardCharacter> temp = new ArrayList<CardCharacter>();
			Random random = new Random();
			while (!card.get(el).isEmpty()) {
				temp.add(card.get(el).remove(random.nextInt(card.get(el).size())));
			}
			card.put(el, temp);
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardVenture>> ventureSortByEra() {
		HashMap<Integer, ArrayList<CardVenture>> card = new HashMap<Integer, ArrayList<CardVenture>>();
		ArrayList<CardVenture> allCard = new ArrayList<CardVenture>();
		VentureCardSaxParser.VentureRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Venture.xml", allCard);
		for (CardVenture el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardVenture> temp = new ArrayList<CardVenture>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		for (Integer el : card.keySet()) {
			ArrayList<CardVenture> temp = new ArrayList<CardVenture>();
			Random random = new Random();
			while (!card.get(el).isEmpty()) {
				temp.add(card.get(el).remove(random.nextInt(card.get(el).size())));
			}
			card.put(el, temp);
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardBuilding>> buildingSortByEra() {
		HashMap<Integer, ArrayList<CardBuilding>> card = new HashMap<Integer, ArrayList<CardBuilding>>();
		ArrayList<CardBuilding> allCard = new ArrayList<CardBuilding>();
		BuildingCardSaxParser.BuildingRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Building.xml", allCard);
		for (CardBuilding el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardBuilding> temp = new ArrayList<CardBuilding>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		for (Integer el : card.keySet()) {
			ArrayList<CardBuilding> temp = new ArrayList<CardBuilding>();
			Random random = new Random();
			while (!card.get(el).isEmpty()) {
				temp.add(card.get(el).remove(random.nextInt(card.get(el).size())));
			}
			card.put(el, temp);
		}
		return card;
	}

	public static HashMap<Integer, ArrayList<CardTerritory>> territorySortByEra() {
		HashMap<Integer, ArrayList<CardTerritory>> card = new HashMap<Integer, ArrayList<CardTerritory>>();
		ArrayList<CardTerritory> allCard = new ArrayList<CardTerritory>();
		TerritoryCardSaxParser.TerritoryRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Territory.xml", allCard);
		for (CardTerritory el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardTerritory> temp = new ArrayList<CardTerritory>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		for (Integer el : card.keySet()) {
			ArrayList<CardTerritory> temp = new ArrayList<CardTerritory>();
			Random random = new Random();
			while (!card.get(el).isEmpty()) {
				temp.add(card.get(el).remove(random.nextInt(card.get(el).size())));
			}
			card.put(el, temp);
		}
		return card;
	}
	
	public static HashMap<Integer, ArrayList<CardExcomm>> excommSortByEra() {
		HashMap<Integer, ArrayList<CardExcomm>> card = new HashMap<Integer, ArrayList<CardExcomm>>();
		ArrayList<CardExcomm> allCard = new ArrayList<CardExcomm>();
		ExcommCardSaxParser.ExcommRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Excomm.xml", allCard);
		for (CardExcomm el : allCard) {
			if (card.containsKey(el.getEra())) {
				card.get(el.getEra()).add(el);
			} else {
				ArrayList<CardExcomm> temp = new ArrayList<CardExcomm>();
				temp.add(el);
				card.put(el.getEra(), temp);
			}
		}
		for (Integer el : card.keySet()) {
			ArrayList<CardExcomm> temp = new ArrayList<CardExcomm>();
			Random random = new Random();
			while (!card.get(el).isEmpty()) {
				temp.add(card.get(el).remove(random.nextInt(card.get(el).size())));
			}
			card.put(el, temp);
		}
		return card;
	}
	
	public static ArrayList<CardLeader> leaderSort() {
		ArrayList<CardLeader> temp=new ArrayList<CardLeader>();
		ArrayList<CardLeader> allCard=new ArrayList<CardLeader>();
		LeaderCardSaxParser.LeaderRead("src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Leader.xml", temp);
		Random random = new Random();
		while(!temp.isEmpty()){
			allCard.add(temp.remove(random.nextInt(temp.size())));
		}
		return allCard;
	}

}