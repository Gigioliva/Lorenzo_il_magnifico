package it.polimi.ingsw.ps22.server.parser;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.card.CardBuilding;
import it.polimi.ingsw.ps22.server.card.CardCharacter;
import it.polimi.ingsw.ps22.server.card.CardExcomm;
import it.polimi.ingsw.ps22.server.card.CardTerritory;
import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class MainParser {

	public static void mainParser(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<CardBuilding> building = new ArrayList<CardBuilding>();
		String pathnameBuilding = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Building.xml";
		BuildingCardSaxParser.BuildingRead(pathnameBuilding, building);
		System.out.println("CARD BUILDING");
		for (CardBuilding card : building)
			System.out.println("\n" + card.toString());

		ArrayList<CardTerritory> territory = new ArrayList<CardTerritory>();
		String pathnameTerritory = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Territory.xml";
		TerritoryCardSaxParser.TerritoryRead(pathnameTerritory, territory);
		System.out.println("\n\n\nCARD TERRITORY");
		for (CardTerritory card : territory)
			System.out.println("\n" + card.toString());

		ArrayList<CardVenture> venture = new ArrayList<CardVenture>();
		String pathnameVenture = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Venture.xml";
		VentureCardSaxParser.VentureRead(pathnameVenture, venture);
		System.out.println("\n\n\nCARD VENTURE");
		for (CardVenture card : venture)
			System.out.println("\n" + card.toString());

		ArrayList<CardCharacter> character = new ArrayList<CardCharacter>();
		String pathnameCharacter = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Character.xml";
		CharacterCardSaxParser.CharacterRead(pathnameCharacter, character);
		System.out.println("\n\n\nCARD CHARACTER");
		for (CardCharacter card : character)
			System.out.println("\n" + card.toString());

		ArrayList<CardExcomm> excomm = new ArrayList<CardExcomm>();
		String pathnameExcomm = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/Excomm.xml";
		ExcommCardSaxParser.ExcommRead(pathnameExcomm, excomm);
		System.out.println("\n\n\nCARD EXCOMM");
		for (CardExcomm card : excomm)
			System.out.println("\n" + card.toString());

		ArrayList<HashMap<String, ResourceAbstract>> spaces = new ArrayList<HashMap<String, ResourceAbstract>>();
		String pathnameSpace = "src/main/java/it/polimi/ingsw/ps22/server/parser/resources/MarketSpace.xml";
		ZoneBonusSaxParser.BonusRead(pathnameSpace, spaces);
		System.out.println("\n\n\nSPACE BONUS");
		StringBuilder temp = new StringBuilder();
		int i = 1;
		for (HashMap<String, ResourceAbstract> x : spaces) {
			temp.append("\nSPACE "+i+"\n");
			i++;
			for (String el : x.keySet()) {
				
				temp.append(el + " " + x.get(el).getQuantity() + "\n");
			}
			temp.append("\n");
		}
		System.out.println(temp);

	}

}
